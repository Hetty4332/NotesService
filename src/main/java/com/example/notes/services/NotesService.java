package com.example.notes.services;

import com.example.notes.models.entity.NotesModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class NotesService {
    private final FileService fileService;
    private List<NotesModel> notesModelList = new ArrayList<>();

    private List<NotesModel> getNotesByAuthor(String author) {
        return null;
    }

    public void addNotes(NotesModel notesModel, MultipartFile file) {
        try {
            String filePath = fileService.saveFile(file);
            notesModel.setNotesFile(filePath);
            notesModelList.add(notesModel);
            log.info("Save notes: {} ",notesModel);
            notesModelList.add(notesModel);

        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
    }
}
