package com.wickedhobo.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wickedhobo.DAO.hibernateDAO.UserDAOHibSpringImpl;
import com.wickedhobo.object.User;

@Controller
public class UserController {

	@Autowired
	UserDAOHibSpringImpl userDAO;

	@RequestMapping(value = "/userService/findUserByUsernameWithAction/{userName}", method = RequestMethod.GET)
	public String findUserByUsernameWithAction(@PathVariable String userName, Model model) {
		User user = new User();
		user = userDAO.findUserByUsername(userName);

		model.addAttribute("user", user);
		model.addAttribute("userAction", "findUserByUsername");
		return "/result";
	}
	
	@RequestMapping(value = "/userService/findUserByUsername/{userName}", method = RequestMethod.GET)
	public @ResponseBody User findUserByUsername(@PathVariable String userName, Model model) {
		User user = new User();
		user = userDAO.findUserByUsername(userName);
		return user;
	}
	
}
