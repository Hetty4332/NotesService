package com.example.notes.repositories;

import com.example.notes.models.entity.NotesModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends JpaRepository <NotesModel,Long> {
}
