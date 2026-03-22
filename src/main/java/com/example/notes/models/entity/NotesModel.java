package com.example.notes.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NotesModel {
    private String name;
    private String author;
    private String notesFile;
    private String complexity;

}
