package com.example.notes.models.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotesCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection
    @CollectionTable(name = "entity_notes_ids", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "note_id")
    private List<Long> notesIds;

    public NotesCollection(String name) {
        this.name = name;
    }
}
