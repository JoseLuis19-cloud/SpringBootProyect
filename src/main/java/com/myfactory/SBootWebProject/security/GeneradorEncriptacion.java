package com.myfactory.SBootWebProject.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class GeneradorEncriptacion{

	public String generarPasswordEncrip(String nuevaPassword) {
	  BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	  String encodedPassword = encoder.encode(nuevaPassword); 
	  System.out.println(encodedPassword);
	  return encodedPassword;
	}

}
