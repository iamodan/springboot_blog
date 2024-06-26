package com.example.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

// 사용자가 요청 -> 응답(HTML 파일)
// @Controller

// 사용자가 요청 -> 응답(Data)
@RestController
public class HttpControllerTest {
	
	private static final String TAG= "HttpControllerTest : ";
	
	@GetMapping("/https/lombok")
	public String lombokTest() {
		// builder패턴쓰면 생성자의 순서 안지켜도 됨!!
		Member m = Member.builder().username("ssar").password("1234").email("ssar@nate").build();
		System.out.println(TAG+"getter :"+m.getUsername());
		m.setUsername("cos");
		System.out.println(TAG+"setter :"+m.getUsername());
		return "lombok test 완료";
	}

	@GetMapping("/http/get")
	public String getTest(Member m) {
		System.out.println(TAG+"getter : " +m.getId());
		m.setId(5000);
		System.out.println(TAG+"setter :  + m.getId())");
	    return "get 요청 : " + m.getId()+", "+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}

	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m) { //MessageConverter (스프링부트)
		return "get 요청 : " + m.getId()+", "+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	@PutMapping("/http/put")
	public String putTest(@RequestBody Member m) {
		return "put 요청"+ m.getId()+", "+m.getUsername()+","+m.getPassword()+","+m.getEmail();
	}
	
	@DeleteMapping("/http/delete")
	public String deleteTest() {
		return "delete 요청";
	}
	
}
