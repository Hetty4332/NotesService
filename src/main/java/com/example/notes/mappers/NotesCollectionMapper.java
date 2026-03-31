package com.example.notes.mappers;

import com.example.notes.models.dto.NotesCollectionDTO;
import com.example.notes.models.entity.NotesCollection;

public class NotesCollectionMapper {
    public static NotesCollection toEntity(NotesCollectionDTO dto) {
        if (dto == null) {
            return null;
        }

        return NotesCollection.builder()
                .name(dto.getName())
                .notesIds(dto.getNotesIds())
                .build();
    }

    public static NotesCollectionDTO toDTO(NotesCollection entity) {
        if (entity == null) {
            return null;
        }

        return NotesCollectionDTO.builder()
                .name(entity.getName())
                .notesIds(entity.getNotesIds())
                .build();
    }
}
