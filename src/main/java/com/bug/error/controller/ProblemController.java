package com.bug.error.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bug.error.entity.Problem;
import com.bug.error.service.ProblemService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ProblemController {
	
	private final ProblemService problemService;
	
	@GetMapping({"", "/", "/index"})
	public String main() {
		return "index";
	}
	
	@GetMapping("/problems")
	public String problems(Model model, 
			@PageableDefault(sort="id", size=10, direction = Direction.DESC) Pageable pageable,
			@RequestParam(defaultValue = "", required = false) String searchText) {
		
		Page<Problem> problems = problemService.getProblems(pageable, searchText);
		
		int startPage = Math.max(1, problems.getPageable().getPageNumber() - 4);
		int endPage = Math.min(problems.getTotalPages(), problems.getPageable().getPageNumber() + 4);
		
		model.addAttribute("problems", problems);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		
		return "problems";
	}
	
}
