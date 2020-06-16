package com.example.firstopensift.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {

	@GetMapping
	public String getMessage() {
		return "Hello First App";
	}
}
