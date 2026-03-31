package com.example.notes.services;

import com.example.notes.exceptions.NotesException;
import com.example.notes.models.entity.NotesCollection;
import com.example.notes.models.entity.NotesModel;
import com.example.notes.repositories.NotesCollectionRepository;
import com.example.notes.repositories.NotesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotesCollectionService {
    private final NotesCollectionRepository notesCollectionRepository;

    private final NotesRepository notesRepository;

    public void createNotesCollection(String notesCollectionName) {
        NotesCollection notesCollection = new NotesCollection(notesCollectionName);
        try {
            notesCollectionRepository.save(notesCollection);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NotesException("Ошибка при сохранении сборника нот");
        }
    }

    public void addInNotesCollection(String noteName, String collectionName) {
        NotesModel note = notesRepository.findByName(noteName);
        if (note == null) {
            throw new NotesException("Note not found: " + noteName);
        }

        NotesCollection collection = notesCollectionRepository.findByName(collectionName);
        if (collection == null) {
            throw new NotesException("Collection not found: " + collectionName);
        }

        List<Long> noteIds = collection.getNotesIds();
        if (noteIds == null) {
            noteIds = new ArrayList<>();
        }

        if (!noteIds.contains(note.getId())) {
            noteIds.add(note.getId());
            collection.setNotesIds(noteIds);
            notesCollectionRepository.save(collection);
        }
    }
}
