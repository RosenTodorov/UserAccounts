package com.user.accounts.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@ComponentScan({ "com.user.accounts.app", "com.user.accounts.config", "com.user.accounts.controllers",
		"com.user.accounts.services" })
@EntityScan("com.user.accounts.entities")
@EnableJpaRepositories(basePackages = { "com.user.accounts.repositories" })
@EnableScheduling
public class UserAccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAccountsApplication.class, args);
	}
}
