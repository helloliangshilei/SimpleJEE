package web;

import object.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import DAO.hibernateDAO.UserDAOHibSpringImpl;

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

		return "/result.jsp";
	}
}
