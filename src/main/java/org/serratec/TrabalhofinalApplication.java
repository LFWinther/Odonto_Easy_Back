package org.serratec;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
@EnableScheduling
public class TrabalhofinalApplication {	
	public static void main(String[] args) {
		SpringApplication.run(TrabalhofinalApplication.class, args);
	}
}
