package com.bug.error.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bug.error.entity.Problem;
import com.bug.error.repository.ProblemRepository;

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

}
