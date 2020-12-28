package com.myfactory.SBootWebProject;




import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.myfactory.SBootWebProject"}) 
public class SBootWebProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SBootWebProjectApplication.class, args);

	}

}
