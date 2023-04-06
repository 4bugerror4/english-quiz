package com.bug.error.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug.error.entity.Note;
import com.bug.error.entity.dto.CMRespDto;
import com.bug.error.entity.dto.NoteAddDto;
import com.bug.error.entity.dto.NoteDeleteDto;
import com.bug.error.entity.dto.NoteUpdateDto;
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
	
	@PutMapping("/note/update")
	public ResponseEntity<?> update(@RequestBody NoteUpdateDto dto) {
		
		Note note = noteService.update(dto.toEntity());
		
		return new ResponseEntity<>(new CMRespDto<>(1, "노트 수정 완료", note), HttpStatus.OK);
		
	}
	
	@DeleteMapping("/note/delete")
	public ResponseEntity<?> delete(@RequestBody NoteDeleteDto dto) {
		
		noteService.delete(dto.getId());
		
		return new ResponseEntity<>(new CMRespDto<>(1, "노트 삭제 완료", null), HttpStatus.OK);
	}

}
