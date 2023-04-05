package com.bug.error.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bug.error.entity.Note;

public interface NoteRepository extends JpaRepository<Note, Long> {

	Page<Note> findBySubjectContainingOrTitleContainingOrContentContaining(Pageable pageable, String subject, String title, String content);
	
}
