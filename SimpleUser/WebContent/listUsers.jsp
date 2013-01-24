<%@ page language="java" import="simpleDAO.*, commonDAO.*, object.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
		
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
</head>
<body>
	<%
	UserDAO userDAOGet = UserDAOFactory.getUserDAO("jdbc");
	User userGet = userDAOGet.getUser("mckerrj");
	out.println("Lastname is: " + userGet.getLastName());
	%>
	
	<%
	UserDAO userDAOAdd = UserDAOFactory.getUserDAO("jdbc");
	User userAdd = new User();
	userAdd.setUserName("tarantinoq");
	userAdd.setFirstName("Quentin");
	userAdd.setLastName("Tarantino");
	userAdd.setPassword("asdfasdf");
	
	int add = userDAOAdd.saveUser(userAdd);
	if (add == 1)
		out.println("<br/>  Add user <b>" + userAdd.getUserName() + "</b> successfull");
	else
		out.println("<br/>  User insertion failed.");
	%>
	
	<%
	UserDAO userDAOChange = UserDAOFactory.getUserDAO("jdbc");
	User userChange = new User();
	userChange.setUserName("tarantinoq");
	userChange.setFirstName("Quentin");
	userChange.setLastName("TarantinoChanged");
	userChange.setPassword("asdfasdf");
	
	int change = userDAOChange.changeUser(userChange);
	if (change == 1)
		out.println("<br/>  Change user <b>" + userChange.getUserName() + "</b> successfull");
	else
		out.println("<br/>  User Change failed.");
	%>
	
	<%
	UserDAO userDAOList = UserDAOFactory.getUserDAO("jdbc");
	List<User> userList = userDAOList.listAllUsers();
	out.println("<br/><br/> List of Users:<br/>");
	for (User users: userList) {
		out.println("User: " + users.getFirstName() + " " + users.getLastName() + "<br/>");  
	}
	%>
	
	<%
	UserDAO userDAORemove = UserDAOFactory.getUserDAO("jdbc");
	User userRemove = new User();
	userRemove.setUserName("tarantinoq");
	int test = userDAORemove.removeUser(userRemove);
	if (test == 1)
		out.println("<br/>  Delete user <b>" + userRemove.getUserName() + "</b> successfull");
	else
		out.println("<br/> Deleting user failed");
	%>
	 
</body>
</html>