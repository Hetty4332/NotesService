package com.example.notes.services.security;

import com.example.notes.exceptions.UserAlreadyExistsException;
import com.example.notes.models.dto.AuthRequest;
import com.example.notes.models.dto.AuthResponse;
import com.example.notes.models.dto.RegisterRequest;
import com.example.notes.models.entity.Role;
import com.example.notes.models.entity.User;
import com.example.notes.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthResponse register(RegisterRequest request) {


        User user = new User();
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.USER);

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );

        User user = userRepository.findByUsername(request.username())
                .orElseThrow(() -> new IllegalStateException("Пользователь не найден"));

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }
}