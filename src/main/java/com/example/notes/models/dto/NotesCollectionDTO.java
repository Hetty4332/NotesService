package com.example.notes.models.dto;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NotesCollectionDTO {
    private String name;
    @ElementCollection
    @CollectionTable(name = "entity_notes_ids", joinColumns = @JoinColumn(name = "entity_id"))
    @Column(name = "note_id")
    private List<Long> notesIds;

    public NotesCollectionDTO(String name) {
        this.name = name;
    }
}
