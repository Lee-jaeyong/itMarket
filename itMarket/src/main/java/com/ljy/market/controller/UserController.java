package com.ljy.market.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljy.market.user.UserDTO;
import com.ljy.market.user.UserEntity;
import com.ljy.market.user.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@GetMapping({ "", "/" })
	public String index() {
		return "view/index";
	}

	@GetMapping("list")
	public String list() {
		return "view/list";
	}

	@GetMapping("about")
	public String about() {
		return "view/about";
	}

	@GetMapping("contact")
	public String contact() {
		return "view/contact";
	}

	@GetMapping("post")
	public String post() {
		return "view/post";
	}
	
	@GetMapping("testLogin")
	public @ResponseBody String test(UserEntity user) {
		System.out.println(user);
		return "test";
	}

	@GetMapping("loginForm")
	public String loginForm() {
		return "/user/loginForm";
	}

	@GetMapping("joinForm")
	public String joinForm() {
		return "/user/joinForm";
	}

	@PostMapping("join")
	public String join(UserDTO user) {
		userService.save(user);
		return "redirect:";
	}
}
