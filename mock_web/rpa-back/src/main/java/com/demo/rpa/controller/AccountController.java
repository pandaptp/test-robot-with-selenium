package com.demo.rpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import com.demo.rpa.model.Account;
import com.demo.rpa.model.MultipleAccount;
import com.demo.rpa.services.impl.AccountService;
import com.demo.rpa.storage.StorageFileNotFoundException;
import com.demo.rpa.storage.StorageService;

@CrossOrigin("*")
@RestController
public class AccountController {
	
	
 	private final StorageService storageService;

    @Autowired
    public AccountController(StorageService storageService) {
        this.storageService = storageService;
    }
	@Autowired
	private AccountService accountService;
	
	@PostMapping("/manage-account/create-single-account")
	public Account createAccountSingle(@RequestBody Account account) {
		System.out.println("Request Start");
		accountService.createAccountSingle(account);
		System.out.println("Request End");
		return account;
	}
	
	
	@PostMapping("/manage-account/create-multiple-account")
	public Account createAccountMultiple(@RequestBody MultipleAccount multipleAccount) {
		System.out.println(multipleAccount);
		byte[] decoded = Base64.getDecoder().decode(multipleAccount.getFile());
		String str = new String(decoded, StandardCharsets.UTF_8);
		System.out.println(str);
		String[] lines = str.split("\\r?\\n");
		
		List<Account> accounts = new ArrayList<Account>();
		for(String x: lines ) {
			System.out.println("x: "+x);
			Account account = new Account();
			String[] line = x.split("\\|");
			String userName = line[0];
			String email = line[1];
			account.setCreateType("create-account");
			account.setEmail(email);
			account.setUserName3bb(userName);
			accounts.add(account);
			
			System.out.println(userName);
			System.out.println(email);
		}
		accountService.createAccountMultiple(accounts, multipleAccount.getBatchUserEmail());
	    
		return accounts.get(0);
	}
	
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
