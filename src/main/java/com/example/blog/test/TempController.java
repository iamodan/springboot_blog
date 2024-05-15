package com.example.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempController {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일리턴 기본경로: src/main/resources/statichome.html
		// 리턴명: /home.html
		// 타임리트 설정?
		return "home";
	}

	@GetMapping("/temp/img")
	public String tempImg() {
//		return "/a.jpg";
		return "redirect:/a.jpg";
	}

	@GetMapping("/temp/jsp")
	public String tempJsp() {
		return "/test.jsp";
	}

}
