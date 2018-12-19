package com.example.javaserver;

import com.example.javaserver.services.NofluffParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class JavaServerApplication {

	@Autowired
	private static NofluffParser nofluffParser ;

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(JavaServerApplication.class, args);
		System.out.println("Done");
	}
}
