package com.bug.error.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TranslateController {
	
	@GetMapping("/translate")
	public String translate() {
		
		return "translate";
	}

}
