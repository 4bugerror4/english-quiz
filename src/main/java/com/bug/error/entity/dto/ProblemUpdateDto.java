package com.bug.error.entity.dto;

import com.bug.error.entity.Problem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProblemUpdateDto {
	
	private Long id;
	private String problem;
	private String answer;
	private String hint;
	private String category;
	
	public Problem toEntity() {
		return Problem.builder()
				.id(id)
				.problem(problem)
				.answer(answer)
				.hint(hint)
				.category(category)
				.build();
	}

}
