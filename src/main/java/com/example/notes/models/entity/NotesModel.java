package com.example.notes.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
@Setter
public class NotesModel {
    @Id
    private Long id;
    @Column(unique = true)
    private String name;
    private String author;
    private String notesFile;
    private String complexity;

}
