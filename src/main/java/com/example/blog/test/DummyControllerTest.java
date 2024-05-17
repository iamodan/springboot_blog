package com.example.blog.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.AppUser;
import com.example.blog.repository.AppUserRepository;

@RestController
public class DummyControllerTest {
	
	@Autowired
	private AppUserRepository  AppUserRepository;
	

	// http://localhost:8000/blog/dummy/join(요청)
	// http의 body에 username, password, email 데이터를 가지고(요청)
	@PostMapping("/dummy/join") // key=value(약속된 규칙)
	public String join(AppUser AppUser) {
		System.out.println("id : " + AppUser.getId());
		System.out.println("username : " + AppUser.getUsername());
		System.out.println("password : " + AppUser.getPassword());
		System.out.println("email : " + AppUser.getEmail());
		System.out.println("role :" + AppUser.getRole());
		System.out.println("createDate :" + AppUser.getCreateDate());
		
		AppUserRepository.save(AppUser);
		return "회원가입이 완료되었습니다.";
	}
	
	
}
