<%@ page language="java" import="simpleDAO.*, commonDAO.*, object.*, java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="test" type="simpleJDBC.DBConnection" class="simpleJDBC.DBConnection" scope="request" />  
		
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Insert title here</title>
</head>
<body>
	<%
	UserDAO userDAO = UserDAOFactory.getUserDAO("jdbc");
	User user = userDAO.getUser("mckerrj");
	out.println("Lastname is: " + user.getLastName());
	%>

	 
</body>
</html>