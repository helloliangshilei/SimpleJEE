package com.wickedhobo.web;

import com.wickedhobo.object.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.wickedhobo.DAO.hibernateDAO.UserDAOHibSpringImpl;

@Controller
public class UserController {

	@Autowired
	UserDAOHibSpringImpl userDAO;

	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@RequestParam("userName") String userName,
													@RequestParam("password") String password,
													@RequestParam("firstName") String firstName,
													@RequestParam("lastName") String lastName, 
													Model model) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);

		userDAO.saveUser(user);

		model.addAttribute("addUser", "true");
		return "/result";
	}
	
	@RequestMapping(value = "/removeUser", method = RequestMethod.POST)
	public String removeUser(@RequestParam("userName") String userName,
													Model model) {
		User user = new User();
		user.setUserName(userName);

		userDAO.deleteUser(user);
		
		model.addAttribute("removeUser", "true");
		return "/result";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
	public String updateUser(@RequestParam("userName") String userName,
													@RequestParam("password") String password,
													@RequestParam("firstName") String firstName,
													@RequestParam("lastName") String lastName, 
													Model model) {
		User user = new User();
		user.setUserName(userName);
		user.setPassword(password);
		user.setFirstName(firstName);
		user.setLastName(lastName);

		userDAO.updateUser(user);

		model.addAttribute("updateUser", "true");
		return "/result";
	}
}
