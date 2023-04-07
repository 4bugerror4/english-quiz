package com.bug.error.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bug.error.entity.Problem;
import com.bug.error.service.ProblemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class QuizController {
	
	private final ProblemService problemService;
	
	@GetMapping("/quiz")
	public String quiz(Model model, @PageableDefault(sort="id", size=1, direction = Direction.ASC) Pageable pageable) {
		
		Page<Problem> problems = problemService.getProblems(pageable);
		
		model.addAttribute("currentPage", problems.getNumber() + 1);
		model.addAttribute("totalPages", problems.getTotalPages());
		model.addAttribute("problems", problems);
		
		return "quiz";
	}
	
	@GetMapping("/random/quiz")
	public String quiz(Model model) {
		
		List<Problem> problems = problemService.getProblems();
		
		final int RANDOM_NUMBER = (int) (Math.random() * problems.size());		
		model.addAttribute("problem", problems.get(RANDOM_NUMBER));
		
		return "random_quiz";
	}
	
	@GetMapping("/multiple/quiz")
	public String multiple() {
		
		return "multiple_quiz";
	}

}
