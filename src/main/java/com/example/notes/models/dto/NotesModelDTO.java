package com.example.notes.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NotesModelDTO {
    private String name;
    private String author;
    private String notesFile;
    private String complexity;
}
