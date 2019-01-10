package com.brendt.bootHello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.brendt.bootHello.dao.UserDao;

@SpringBootApplication
public class BootHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootHelloApplication.class, args);
		System.out.println("Hello");

	}

}

