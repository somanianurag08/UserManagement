package com.training.util;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.training")
public class SpringBootRestApiApp {

	public static void main(String[] args) {
		System.getProperties().put( "server.port", 8181 );  //8181 port is set here
		SpringApplication.run(SpringBootRestApiApp.class, args);
	}
}
