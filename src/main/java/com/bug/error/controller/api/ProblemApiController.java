package com.bug.error.controller.api;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
		
		Problem problem = problemService.add(dto.toEntity());
		
		return new ResponseEntity<>(new CMRespDto<>(1, "문제 추가 완료", problem), HttpStatus.OK);
		
	}
	
	@PutMapping("/problem/update")
	public ResponseEntity<?> update(@Valid @RequestBody ProblemUpdateDto dto, BindingResult bindingResult) {
		
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
		
		if (file.isEmpty()) {
			throw new RuntimeException("파일이 존재하지 않습니다.");
		} else if (!file.getContentType().equals("text/csv")) {
			throw new RuntimeException("파일이 형식이 틀렸습니다. CSV 파일을 등록해주세요");
		}
		
		List<Problem> problemList = problemService.csvUpload(file);
		
		return new ResponseEntity<>(new CMRespDto<>(1, "CSV 문제 추가 완료", problemList), HttpStatus.OK);
	}
	
	// 사지선다 문제 제출
	@GetMapping("/problem/quiz/multipleChoice")
	public Set<Problem> multipleChoice() {
		
		return problemService.getMultipleChoice();
	}

}
