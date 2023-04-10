package com.bug.error.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bug.error.entity.Problem;
import com.bug.error.entity.dto.CMRespDto;
import com.bug.error.entity.dto.QuizResultDto;
import com.bug.error.service.ProblemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class QuizApiController {
	
	private final ProblemService problemService;
	
	@PostMapping("/quiz/result")
	public ResponseEntity<?> result(@RequestBody QuizResultDto dto) {
		
		Problem problem = problemService.getProblem(dto.getId());
		
		if (problem.getAnswer().equalsIgnoreCase(dto.getAnswer())) {
			return new ResponseEntity<>(new CMRespDto<>(1, "정답 입니다!", problem), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new CMRespDto<>(-1, "틀렸습니다.", problem), HttpStatus.OK);
		}
		
	}

}
