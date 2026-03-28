package com.example.notes.services;

import com.example.notes.models.entity.NotesModel;
import com.example.notes.repositories.NotesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotesService {
    private final FileService fileService;
    private final NotesRepository notesRepository;

    private List<NotesModel> getNotesByAuthor(String author) {
        return null;
    }

    public void saveNotes(NotesModel notesModel, MultipartFile file) {
        try {
            String filePath = fileService.saveFile(file);
            notesModel.setNotesFile(filePath);
            log.info("Save notes: {} ", notesModel);
            notesRepository.save(notesModel);
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
    }
}
