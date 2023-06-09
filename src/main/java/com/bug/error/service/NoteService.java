package com.bug.error.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bug.error.entity.Note;
import com.bug.error.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {
	
	private final NoteRepository noteRepository;
	
	@Transactional(readOnly = true)
	public Page<Note> getNotes(Pageable pageable, String searchText) {
		
		Page<Note> notes = noteRepository.findBySubjectContainingOrTitleContainingOrContentContaining(pageable, searchText, searchText, searchText);
		
		return notes;
	}
	
	@Transactional
	public Note add(Note note) {
		
		Note noteEntity = noteRepository.save(note);
		
		return noteEntity;
	}
	
	@Transactional
	public Note update(Note note) {
		
		Note noteEntity = noteRepository.findById(note.getId()).orElseThrow(() -> new IllegalArgumentException("해당 번호의 글은 존재하지 않습니다."));
		
		noteEntity.setSubject(note.getSubject());
		noteEntity.setTitle(note.getTitle());
		noteEntity.setContent(note.getContent());
		
		return noteEntity;
	}
	
	@Transactional
	public void delete(Long id) {
		
		noteRepository.deleteById(id);
	}

}
