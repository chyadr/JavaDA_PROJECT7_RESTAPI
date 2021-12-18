package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.IUserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController
{

	private final IUserService userService;

	public HomeController(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping("/")
	public String home(Model model, HttpServletRequest request)
	{
		// check if user is already exist in our database, otherwise redirect him to add form with predefined  github information
		User user = userService.checkIfUserExist();
		if(user == null){
			User newUser = userService.getOAuth2UserInfos();
			model.addAttribute("user",newUser);
			request.getSession().setAttribute("username",newUser.getUsername());
			return "/user/add";
		}

		// put the user in the session attribute
		request.getSession().setAttribute("username",user.getUsername());
		return "home";
	}


}
