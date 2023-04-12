package com.bug.error.entity.dto;

import javax.validation.constraints.NotBlank;

import com.bug.error.entity.Problem;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProblemUpdateDto {
	
	private Long id;
	
	@NotBlank
	private String problem;
	
	@NotBlank
	private String answer;
	private String hint;
	
	@NotBlank
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
