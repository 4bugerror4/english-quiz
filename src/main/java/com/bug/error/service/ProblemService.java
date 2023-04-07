package com.bug.error.service;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.bug.error.entity.Problem;
import com.bug.error.repository.ProblemRepository;
import com.univocity.parsers.common.record.Record;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProblemService {
	
	private final ProblemRepository problemRepository;
	
	@Transactional(readOnly = true)
	public Problem getProblem(Long id) {
		
		Problem problem = problemRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 번호의 문제는 없습니다."));
		
		return problem;
	}
	
	@Transactional(readOnly = true)
	public Page<Problem> getProblems(Pageable pageable) {
		Page<Problem> problems = problemRepository.findAll(pageable);
		
		return problems;
	}
	
	@Transactional(readOnly = true)
	public Page<Problem> getProblems(Pageable pageable, String searchText) {
		Page<Problem> problems = problemRepository.findByProblemContainingOrAnswerContaining(pageable, searchText, searchText);
		
		return problems;
	}
	
	@Transactional(readOnly = true)
	public List<Problem> getProblems() {
		List<Problem> problems = problemRepository.findAll();
		
		return problems;
	}
	
	@Transactional
	public Problem add(Problem problem) {
		
		Problem problemEntity = problemRepository.save(problem);
		
		return problemEntity;
	}
	
	@Transactional
	public Problem update(Problem problem) {
		
		Problem problemEntity = problemRepository.findById(problem.getId()).orElseThrow(() -> new IllegalArgumentException("해당 번호의 문제는 존재하지 않습니다."));
		
		problemEntity.setProblem(problem.getProblem());
		problemEntity.setAnswer(problem.getAnswer());
		problemEntity.setHint(problem.getHint());
		
		return problemEntity;
		
	}
	
	@Transactional
	public void delete(Long id) {
		problemRepository.deleteById(id);
	}
	
	// CSV upload
	@Transactional
	public List<Problem> csvUpload(MultipartFile file) throws IOException {
		
		List<Problem> problemList = new ArrayList<Problem>();
		InputStream is =  new ByteArrayInputStream(file.getBytes());
		BufferedReader br = new BufferedReader(new InputStreamReader(is, "euc-kr"));
		
		CsvParserSettings setting = new CsvParserSettings();
		setting.setHeaderExtractionEnabled(true);
		CsvParser parser = new CsvParser(setting);
		List<Record> parseAllRecords = parser.parseAllRecords(br);
		
		parseAllRecords.forEach(record -> {
			
			Problem problem = new Problem();
			problem.setProblem(record.getString("문제"));
			problem.setAnswer(record.getString("정답"));
			problem.setHint(record.getString("힌트"));
			problemList.add(problem);
		});
		
		br.close();
		
		List<Problem> pList = problemRepository.saveAll(problemList);
		
		return pList;
	}
	
	// CSV header download
	public HttpHeaders getCsvHeader() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/csv; charset=MS949");
		headers.add("Content-Disposition", "attachment; filename=\""+"csvUploadFile.csv"+"\"");
		
		return headers;
		
	}
	// CSV content download
	public String getCsvContent() {
		
		String data = 
				"문제, 정답, 힌트\n"
				+ "문제를 입력해주세요1,정답을 입력해주세요1,힌트를 입력해주세요1\n"
				+ "문제를 입력해주세요2,정답을 입력해주세요2,힌트를 입력해주세요2";
		
		return data;
	}
	
	// 4지선다 퀴즈
	@Transactional(readOnly = true)
	public Set<Problem> getMultipleChoice() {
		
		List<Problem> problems = problemRepository.findAll();
		Set<Problem> multipleChoiceProblems = new HashSet<>();
		
		while (multipleChoiceProblems.size() != 4) {
			int randomNumber = (int) (Math.random() * problems.size());
			multipleChoiceProblems.add(problems.get(randomNumber));
		}
		
		return multipleChoiceProblems;
	}

}
