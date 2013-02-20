package web;

import object.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import DAO.hibernateDAO.UserDAOHibSpringImpl;

@Controller
public class UserControllerRest {

	@Autowired
	UserDAOHibSpringImpl userDAO;
	
	private Jaxb2Marshaller jaxb2Mashaller;
	
	public void setJaxb2Mashaller(Jaxb2Marshaller jaxb2Mashaller) {
		this.jaxb2Mashaller = jaxb2Mashaller;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/getUser/{userName}")
	public ModelAndView getUser(@PathVariable String userName) {
		User user = userDAO.findUserByUsername(userName);
		return new ModelAndView("employees", "object", user);
	}

	/*@RequestMapping(value = "/addUser", method = RequestMethod.POST)
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
		return "/result.jsp";
	}*/
	
	/*@RequestMapping(value = "/removeUser", method = RequestMethod.POST)
	public String removeUser(@RequestParam("userName") String userName,
													Model model) {
		User user = new User();
		user.setUserName(userName);

		userDAO.deleteUser(user);
		
		model.addAttribute("removeUser", "true");
		return "/result.jsp";
	}*/
	
	/*@RequestMapping(value = "/updateUser", method = RequestMethod.POST)
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
		return "/result.jsp";
	}*/
}
