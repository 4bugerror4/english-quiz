package com.bug.error.entity.dto;

import javax.validation.constraints.NotBlank;

import com.bug.error.entity.Note;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoteAddDto {
	
	@NotBlank
	private String subject;
	
	@NotBlank
	private String title;
	
	@NotBlank
	private String content;
	
	public Note toEntity() {
		return Note.builder()
				.subject(subject)
				.title(title)
				.content(content)
				.build();
	}

}
