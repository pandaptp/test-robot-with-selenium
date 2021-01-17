package com.demo.rpa.services.impl;

import org.springframework.stereotype.Service;

import com.demo.rpa.model.User;
import com.demo.rpa.services.ILoginService;

@Service
public class LoginService implements ILoginService {

	@Override
	public boolean authenticate(User user) {
		if(user.getUserName().equals("demo") && user.getPassword().equals("demopassword")) {
			return true;
		}
		return false;
	}

}
