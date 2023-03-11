package com.adem.springdata.controllers;

import com.adem.springdata.entities.User;
import com.adem.springdata.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class LoginController
{
	UserService userService;

	@Autowired
	public LoginController(UserService userService)
	{
		this.userService = userService;
	}

	@RequestMapping("/add")
	@ResponseBody
	public String addUser()
	{
		User user = new User();

		user.setEmail("MohamedAdemBenMoussa@Gmail.com");
		user.setPassword("Domdom");
		user.setFirstName("Mohamed Adem");
		user.setLastName("BEN MOUSSA");

		this.userService.saveUser(user);

		return "Added";
	}
}
