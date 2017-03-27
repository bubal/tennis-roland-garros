package com.app.tennis.tests.webservices;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.app.tennis.services.JoueurService;
import com.app.tennis.services.impl.JoueurServiceImpl;


@Configuration
@EnableWebMvc
@ImportResource("classpath:spring-application-context.xml")
public class RestTestConfig {
	public JoueurService joueurService() {
		JoueurService joueurService = new JoueurServiceImpl();
		return joueurService;
	}
}
