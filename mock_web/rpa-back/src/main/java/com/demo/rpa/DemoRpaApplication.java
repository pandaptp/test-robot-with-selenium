package com.demo.rpa;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;

import com.demo.rpa.storage.StorageProperties;


@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
@EnableAsync(proxyTargetClass = true)
@EnableCaching
public class DemoRpaApplication 
{

	public static void main(String[] args) {
		SpringApplication.run(DemoRpaApplication.class, args);
	}
}
