package com.bug.error.controller.api;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bug.error.entity.Problem;
import com.bug.error.entity.dto.CMRespDto;
import com.bug.error.entity.dto.MultipleChoiceResultDto;
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
	
	// CSV 기본 파일 다운로드
	@GetMapping("/problem/csv/download")
	public ResponseEntity<?> csvDownload() {
		
		HttpHeaders header = problemService.getCsvHeader();
		String content = problemService.getCsvContent();
		
		return new ResponseEntity<String>(content, header, HttpStatus.CREATED);
	}
	
	// CSV 문제 업로드
	@PostMapping("/problem/csv/upload")
	public ResponseEntity<?> csvUpload(@RequestParam("file") MultipartFile file) throws IOException {
		
		List<Problem> problemList = problemService.csvUpload(file);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "CSV 문제 추가 완료", problemList), HttpStatus.OK);
	}
	
	// 사지선다 문제 제출
	@GetMapping("/problem/quiz/multipleChoice")
	public Set<Problem> multipleChoice() {
		
		return problemService.getMultipleChoice();
	}
	
	// 사지선다 문제 정답
	@PostMapping("/problem/quzi/mutipleChoiceResult")
	public ResponseEntity<?> mutipleChoiceResult(@RequestBody MultipleChoiceResultDto dto) {
		
		
		
		return new ResponseEntity<>(new CMRespDto<>(1, "정답 입니다.", null), HttpStatus.OK);
	}

}
