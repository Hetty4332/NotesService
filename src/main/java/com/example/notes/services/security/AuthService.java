package com.example.notes.services.security;

import com.example.notes.exceptions.LoginAlreadyExistsException;
import com.example.notes.models.dto.AuthRequest;
import com.example.notes.models.dto.AuthResponse;
import com.example.notes.models.dto.RegisterRequest;
import com.example.notes.models.entity.Role;
import com.example.notes.models.entity.User;
import com.example.notes.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.postgresql.util.PSQLException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Log4j2
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
        try {

            userRepository.save(user);
        } catch (DataIntegrityViolationException ex) {
            if (isDuplicateLogin(ex)) {
                throw new LoginAlreadyExistsException("Такой логин уже существует");
            }
            throw ex;
        }

        String jwtToken = jwtService.generateToken(user);
        return new AuthResponse(jwtToken);
    }

    private boolean isDuplicateLogin(DataIntegrityViolationException ex) {
        Throwable root = getRootCause(ex);

        if (root instanceof PSQLException psqlEx) {
            // 23505 = unique_violation в PostgreSQL
            boolean uniqueViolation = "23505".equals(psqlEx.getSQLState());

            // дополнительно проверяем имя constraint, чтобы не ловить вообще все unique-ошибки
            String message = psqlEx.getMessage();
            boolean loginConstraint = message != null && message.contains("username");

            return uniqueViolation && loginConstraint;
        }

        return false;
    }

    private Throwable getRootCause(Throwable throwable) {
        Throwable result = throwable;
        while (result.getCause() != null && result.getCause() != result) {
            result = result.getCause();
        }
        return result;
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