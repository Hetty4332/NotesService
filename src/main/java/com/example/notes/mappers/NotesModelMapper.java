package com.example.notes.mappers;

import com.example.notes.models.dto.NotesModelDTO;
import com.example.notes.models.entity.NotesModel;

public class NotesModelMapper {

    public static NotesModel toEntity(NotesModelDTO dto) {
        if (dto == null) {
            return null;
        }

        return NotesModel.builder()
                .name(dto.getName())
                .author(dto.getAuthor())
                .notesFile(dto.getNotesFile())
                .complexity(dto.getComplexity())
                .build();
    }

    public static NotesModelDTO toDTO(NotesModel entity) {
        if (entity == null) {
            return null;
        }

        return NotesModelDTO.builder()
                .name(entity.getName())
                .author(entity.getAuthor())
                .notesFile(entity.getNotesFile())
                .complexity(entity.getComplexity())
                .build();
    }
}
