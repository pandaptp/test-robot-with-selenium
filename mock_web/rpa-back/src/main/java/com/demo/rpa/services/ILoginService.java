package com.demo.rpa.services;

import com.demo.rpa.model.User;

public interface ILoginService {
	
	public boolean authenticate(User user);
	
}
