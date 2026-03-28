package com.example.notes;

import com.example.notes.models.entity.NotesModel;
import com.example.notes.services.FileService;
import com.example.notes.services.NotesService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NotesServiceTest {

    @Mock
    private FileService fileService;
    @Mock
    private MultipartFile multipartFile;

    @InjectMocks
    private NotesService notesService;


    @Test
    public void addNotesTest() throws IOException {
        NotesModel notesModel = new NotesModel();
        notesModel.setName("Пример нот");
        notesModel.setComplexity("EASY");
        notesModel.setAuthor("Пример автора");
        notesService.saveNotes(notesModel, multipartFile);
        verify(fileService, times(1)).saveFile(multipartFile);
    }
}
