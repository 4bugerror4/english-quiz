package com.bug.error.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.bug.error.entity.Problem;

public interface ProblemRepository extends JpaRepository<Problem, Long> {

	Page<Problem> findByProblemContainingOrAnswerContaining(Pageable pageable, String problem, String answer);
	
}
