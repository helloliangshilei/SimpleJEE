<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Result page</title>
	</head>
	<body>
		<% if (pageContext.findAttribute("addUser") != null && pageContext.findAttribute("addUser").equals("true")) %>
		User added
		<% else if (pageContext.findAttribute("removeUser") != null && pageContext.findAttribute("removeUser").equals("true")) %>
		User Removed
		<% else if (pageContext.findAttribute("updateUser") != null && pageContext.findAttribute("updateUser").equals("true")) %>
		User Updated.
		<% else %>
		No Action requested.
		
	</body>
</html>