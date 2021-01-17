package com.demo.rpa.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class MultipleAccount {
	private String file;
	private String batchUserEmail;
}
