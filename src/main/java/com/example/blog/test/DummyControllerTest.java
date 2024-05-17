package com.example.blog.test;

import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.blog.model.AppUser;
import com.example.blog.model.RoleType;
import com.example.blog.repository.AppUserRepository;

// html파일이 아니라 data를 리턴해주는 controller = RestController
@RestController
public class DummyControllerTest {

	@Autowired // 의존성 주입(DI)
	private AppUserRepository AppUserRepository;

	// email, password
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable("id") int id, @RequestBody AppUser requestUser) {
		System.out.println("id : " + id);
		System.out.println("password : " + requestUser.getPassword());
		System.out.println("email : " + requestUser.getEmail());

		AppUser appUser = AppUserRepository.findById(id).orElseThrow(() -> {
			return new IllegalArgumentException("수정에 실패하였습니다.");
		});
		appUser.setPassword(requestUser.getPassword());
		appUser.setEmail(requestUser.getEmail());

		// save함수는 id를 전달하지 않으면 insert를 해주고
		// save함수는 id를 전달하면 해당 id에 대한 데이터가 없으면 insert를 해요.
		// AppUserRepository.save(appUser);
		
		// 더티 체킹 
		// update 할떄는 save 사용안해도됨
		return null;
	}

	// http://localhost:8000/blog/dummy/user
	@GetMapping("/dummy/users")
	public List<AppUser> list() {
		return AppUserRepository.findAll();
	}

	// 한 페이지당 2건에 데이터를 리턴받아 볼 예정
	// http://localhost:8000/blog/user/page
	@GetMapping("/dummy/user")
	public List<AppUser> pageList(
			@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<AppUser> pagingUser = AppUserRepository.findAll(pageable);

		List<AppUser> users = pagingUser.getContent();
		return users;
	}

	// {id} 주소로 파라미터를 전달 받을 수 있음.
	// http://localhost:8000/blog/dummy/user/3
	@GetMapping("/dummy/user/{id}")
	public AppUser detail(@PathVariable("id") int id) {
		// user/4을 찾으면 내가 데이터베이스에서 못찾아오게 되면 user가 null이 될 것 아냐?
		// 그럼 return null이 리턴이 되잖아.. 그럼 프로그램에 문제가 있찌 않겠니?
		// Optional로 너의 User객체를 감싸서 가져올테니 null인지 아닌지 판단하게 return해!

		AppUser appUser = AppUserRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
			@Override
			public IllegalArgumentException get() {
				return new IllegalArgumentException("해당 유저는 없습니다. id : " + id);
			}
		});
		// 요청 : 웹브라우저
		// AppUser객체 = 자바 오브젝트
		// 변환(웹브라우저가 이해할수잇는 데이터) -> json (Gson 라이브러리)
		// 스프링부트 = MessageConverter라는 애가 응답시에 자동 작동
		// 만약에 자바 오브젝트를 리턴하게 되면 MessageConverter가 Jackson
		// 라이브러리를 호출해서 user 오브젝트를 json으로 변환해서 브라우저에게 던져줍니다.
		return appUser;
	}

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

		AppUser.setRole(RoleType.USER);
		AppUserRepository.save(AppUser);
		return "회원가입이 완료되었습니다.";
	}

}
