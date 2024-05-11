package com.example.blog.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
public class IndexController {
	
	@GetMapping("/")
	public String index() {
		String name = "ody";
		log.debug("2222 : name = {}", name);
		return "index";
	}
	

}
