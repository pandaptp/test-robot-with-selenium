package com.demo.rpa.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.rpa.model.User;
import com.demo.rpa.services.impl.LoginService;
@CrossOrigin("*")
@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping(value = "login")
	public User login(@RequestBody User user) {
		System.out.println(user);
		if(loginService.authenticate(user)) {
			return user;
		}
		return null;
	}
	
}
