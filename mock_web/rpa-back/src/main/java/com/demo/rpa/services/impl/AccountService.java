package com.demo.rpa.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.demo.rpa.model.Account;
import com.demo.rpa.services.IAccountService;
@Service
public class AccountService implements IAccountService {
	@Autowired
	private JavaMailSender javaMailSender;

	@Override
	@Async
	public void createAccountSingle(Account account) {
		String data = "Dear " + account.getUserName3bb();
		data += "\nPlease select \"AIS SMART Login\" network\nUsername: "+account.getUserName3bb()+" Password: 2487\nThank you for using Wifi.";
		sendEmail(account.getEmail(),data);
		//return account;
	}
	
	@Override
	@Async
	public void createAccountMultiple(List<Account> accounts, String batchEmail) {
		//String batchData = "";
		for(int i = 0; i < accounts.size(); i++) {
			Account account = accounts.get(i);
			String data = "Dear " + account.getUserName3bb();
			data += "\nPlease select \"AIS SMART Login\" network\nUsername: "+account.getUserName3bb()+" Password: 2487\nThank you for using Wifi.";
			//batchData += data + "\n\n";
			sendEmail(account.getEmail(), data);
			sendEmail(batchEmail,data);
		}
		
		
	}
	private void sendEmail(String toEmail, String data) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(toEmail);
		msg.setSubject("Your wifi username and password");
		msg.setText(data);
		javaMailSender.send(msg);
	}
}
