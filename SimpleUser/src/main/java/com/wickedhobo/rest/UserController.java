package com.wickedhobo.rest;

import java.util.ArrayList;
import java.util.List;

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

  @RequestMapping(value = "/findUserByUsernameWithAction/userName/{userName}", method = RequestMethod.GET)
  public String findUserByUsernameWithAction(@PathVariable String userName, Model model) {
    User user = new User();
    user = userDAO.findUserByUsername(userName);

    model.addAttribute("user", user);
    model.addAttribute("userAction", "findUserByUsername");
    return "/result";
  }

  @RequestMapping(value = "/findUserByUsername/userName/{userName}", method = RequestMethod.GET)
  public @ResponseBody User findUserByUsername(@PathVariable String userName) {
    User user = new User();
    user = userDAO.findUserByUsername(userName);
    return user;
  }

  @RequestMapping(value = "/addUser/" +
      "userName/{userName}/" +
      "firstName/{firstName}/" +
      "lastName/{lastName}/" +
      "password/{password}",
      method = RequestMethod.POST)
  public @ResponseBody String addUser(@PathVariable String userName,
      @PathVariable String firstName,
      @PathVariable String lastName,
      @PathVariable String password) {
    User user = new User();
    user.setUserName(userName);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    userDAO.saveUser(user);

    return "/result";
  }

  @RequestMapping(value = "/updateUser/" +
      "userName/{userName}/" +
      "firstName/{firstName}/" +
      "lastName/{lastName}/" +
      "password/{password}",
      method = RequestMethod.PUT)
  public @ResponseBody String updateUser(@PathVariable String userName,
      @PathVariable String firstName,
      @PathVariable String lastName,
      @PathVariable String password) {
    User user = new User();
    user.setUserName(userName);
    user.setPassword(password);
    user.setFirstName(firstName);
    user.setLastName(lastName);
    userDAO.updateUser(user);

    return "/result";
  }

  @RequestMapping(value = "/removeUser/userName/{userName}/", method = RequestMethod.DELETE)
  public @ResponseBody String removeUser(@PathVariable String userName) {
    User user = new User();
    user.setUserName(userName);
    userDAO.deleteUser(user);

    return "/result";
  }

  @RequestMapping(value = "/listUsers", method = RequestMethod.GET)
  public @ResponseBody List<User> listUsers() {

    List<User> userList = new ArrayList<User>();
    userList = userDAO.listUsers();
    return userList;
  }

  @RequestMapping(value = "/listUsersByRole/roleName/{roleName}", method = RequestMethod.GET)
  public @ResponseBody List<User> listUsersByRoles(@PathVariable String roleName, Model model) {

    List<User> userList = new ArrayList<User>();
    userList = userDAO.listUsersByRoles(roleName);
    return userList;
  }
}
