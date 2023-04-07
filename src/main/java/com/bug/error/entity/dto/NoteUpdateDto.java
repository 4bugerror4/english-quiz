package com.bug.error.entity.dto;

import com.bug.error.entity.Note;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NoteUpdateDto {
	
	private Long id;
	private String subject;
	private String title;
	private String content;
	
	public Note toEntity() {
		return Note.builder()
				.id(id)
				.subject(subject)
				.title(title)
				.content(content)
				.build();
	}

}