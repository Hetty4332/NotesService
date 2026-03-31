package com.example.notes.controlers;

import com.example.notes.services.NotesCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/notesCollections")
public class NotesCollectionRepository {
    private final NotesCollectionService service;

    @PostMapping(value = "/create/{name}")
    private void createNotesCollection(@PathVariable("name") String notesCollectionName) {
        service.createNotesCollection(notesCollectionName);
    }

    @PostMapping(value = "/addInNotesCollection/{collectionName}/{notesName}")
    private void addInNotesCollection(@PathVariable("collectionName") String notesCollectionName, @PathVariable("notesName") String notesName
    ) {
        service.addInNotesCollection(notesCollectionName, notesName);
    }
}
