package com.demo.rpa.services;

import java.util.List;

import com.demo.rpa.model.Account;

public interface IAccountService {
	
	public void createAccountSingle(Account account);
	public void createAccountMultiple(List<Account> account,String batchEmail);
	
}
