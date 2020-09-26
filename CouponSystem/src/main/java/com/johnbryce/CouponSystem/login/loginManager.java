package com.johnbryce.CouponSystem.login;

import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class loginManager {

	private String email;
	private String password;
	private String type;
	
	
	public loginManager() {
	
	}


	public loginManager(String email, String password, String type) {
		super();
		this.email = email;
		this.password = password;
		this.type = type;
	}
	
	
	
	
	
}