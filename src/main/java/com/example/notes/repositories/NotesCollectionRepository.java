package com.example.notes.repositories;

import com.example.notes.models.entity.NotesCollection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesCollectionRepository extends JpaRepository<NotesCollection, Long> {
    NotesCollection findByName(String name);

}
