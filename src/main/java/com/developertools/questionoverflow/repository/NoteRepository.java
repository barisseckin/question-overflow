package com.developertools.questionoverflow.repository;

import com.developertools.questionoverflow.model.Note;
import com.developertools.questionoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Optional<Note> findNoteByPublicId(String publicId);
    List<Note> findNoteByUser(User user);
}
