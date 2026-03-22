package com.example.notes.models.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;
    private String  username;

    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

}
