package com.bug.error.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug.error.entity.Note;
import com.bug.error.entity.dto.CMRespDto;
import com.bug.error.entity.dto.NoteAddDto;
import com.bug.error.service.NoteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoteApiController {
	
	private final NoteService noteService;
	
	@PostMapping("/note/add")
	public ResponseEntity<?> add(@RequestBody NoteAddDto dto) {
		
		Note note = noteService.add(dto.toEntity());
		
		return new ResponseEntity<>(new CMRespDto<>(1, "노트 작성 완료", note), HttpStatus.OK);
		
	}

}