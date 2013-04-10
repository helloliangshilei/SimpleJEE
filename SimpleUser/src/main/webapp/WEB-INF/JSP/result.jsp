<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Result page</title>
	</head>
	<body>
	<% String addUser = (String)pageContext.findAttribute("addUser"); %>
	<% String removeUser = (String)pageContext.findAttribute("removeUser"); %>
	<% String updateUser = (String)pageContext.findAttribute("updateUser"); %>
	
	<c:choose>
		<c:when test="${addUser}">User Was Added</c:when>
		<c:when test="${removeUser}">User Was Removed</c:when>
		<c:when test="${updateUser}">User Was Updated</c:when>
		<c:otherwise>No request specified</c:otherwise>
	</c:choose>
	</body>
</html>