package com.guest.book;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.guest.book.dao.UserDao;
import com.guest.book.dao.UserDaoImpl;
import com.guest.book.service.UserService;
import com.guest.book.service.UserServiceImpl;

@Configuration
@EnableScheduling
public class AppConfig {

	@Bean
	public UserDao userDao() {
		return new UserDaoImpl();
	}

	@Bean
	public UserService userService() {
		return new UserServiceImpl();
	}
}