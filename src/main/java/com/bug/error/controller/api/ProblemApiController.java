package com.bug.error.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug.error.entity.Problem;
import com.bug.error.entity.dto.CMRespDto;
import com.bug.error.entity.dto.ProblemAddDto;
import com.bug.error.entity.dto.ProblemDeleteDto;
import com.bug.error.entity.dto.ProblemUpdateDto;
import com.bug.error.service.ProblemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProblemApiController {
	
	private final ProblemService problemService;
	
	@PostMapping("/problem/add")
	public ResponseEntity<?> add(@Valid @RequestBody ProblemAddDto dto, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			Map<String, String> errprMap = new HashMap<>();
			
			for (FieldError error : bindingResult.getFieldErrors()) {
				errprMap.put(error.getField(), error.getDefaultMessage());
			}
			
			throw new RuntimeException("유효성 검사 실패");
		} else {
			Problem problem = problemService.add(dto.toEntity());
			
			return new ResponseEntity<>(new CMRespDto<>(1, "문제 추가 완료", problem), HttpStatus.OK);
		}
		
	}
	
	@PutMapping("/problem/update")
	public ResponseEntity<?> update(@RequestBody ProblemUpdateDto dto) {
		
		Problem problem = problemService.update(dto.toEntity());
	
		return new ResponseEntity<>(new CMRespDto<>(1, "문제 수정 완료", problem), HttpStatus.OK);
	}
	
	@DeleteMapping("/problem/delete")
	public ResponseEntity<?> delete(@RequestBody ProblemDeleteDto dto) {
		
		problemService.delete(dto.getId());
		
		return new ResponseEntity<>(new CMRespDto<>(1, "문제 삭제 완료", null), HttpStatus.OK);
	}

}
