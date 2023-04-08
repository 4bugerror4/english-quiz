package com.bug.error.entity.dto;

import com.bug.error.entity.Problem;
import com.sun.istack.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProblemAddDto {
	
	@NotNull
	private String problem;
	
	@NotNull
	private String answer;
	
	private String hint;
	
	private String category;
	
	public Problem toEntity() {
		return Problem.builder()
				.problem(problem)
				.answer(answer)
				.hint(hint)
				.category(category)
				.build();
	}
}
