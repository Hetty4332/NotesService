package com.example.notes.controlers;

import com.example.notes.models.entity.NotesModel;
import com.example.notes.services.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notes")
public class NotesController {
    private final NotesService service;

    @GetMapping
    private ResponseEntity<String> getNotesByAuthor() {
        return null;
    }

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    private void addNotes(@RequestPart("notesModel") NotesModel notesModel,
                          @RequestPart("file") MultipartFile file) {
        service.saveNotes(notesModel, file);
    }

    @GetMapping(value = "/getByAuthor")
    private List<NotesModel> getByAuthor(@RequestParam String author
    ) {
        return service.getNotesByAuthor(author);
    }
}
