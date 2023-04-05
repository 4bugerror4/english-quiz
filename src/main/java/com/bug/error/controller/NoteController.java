package com.bug.error.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bug.error.entity.Note;
import com.bug.error.service.NoteService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class NoteController {
	
	private final NoteService noteService;
	
	@GetMapping("/notes")
	public String note(Model model,
			@PageableDefault(sort = "id", size=3, direction = Direction.DESC) Pageable pageable,
			@RequestParam(defaultValue = "", required = false) String searchText) {
		
		Page<Note> notes = noteService.getNotes(pageable, searchText);
		
		int startPage = Math.max(1, notes.getPageable().getPageNumber() - 4);
		int endPage = Math.min(notes.getTotalPages(), notes.getPageable().getPageNumber() + 4);
		
		model.addAttribute("notes", notes);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "notes";
	}

}
