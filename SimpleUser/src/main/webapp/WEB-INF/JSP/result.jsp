<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Results page</title>
</head>
<body>
	<%
		String userAction = (String) pageContext.findAttribute("userAction");
	%>

	<c:choose>
		<c:when test="${userAction eq 'addUser'}">User Was Added</c:when>
		<c:when test="${userAction eq 'removeUser'}">User Was Removed</c:when>
		<c:when test="${userAction eq 'updateUser'}">User Was Updated</c:when>
		<c:when test="${userAction eq 'findUserByUsername' }">
			<c:choose>
				<c:when test="${empty user}">
					User not found.
				</c:when>
				<c:when test="${not empty user}">
					User Name: ${user.userName} </br>
					First Name: ${user.firstName} </br>
					Last Name: ${user.lastName} </br>
				</c:when>
			</c:choose>
		</c:when>
		<c:when test="${userAction eq 'listUsers' }">
			<table style="border: 1px solid black;">
				<th style="border: 1px solid black;">First Name</th>
				<th style="border: 1px solid black;">Last Name</th>
				<th style="border: 1px solid black;">User Name</th>
				<c:forEach items="${userList}" var="user">
					<tr>
						<td style="border: 1px solid black;"><c:out value="${user.firstName}" /></td>
						<td style="border: 1px solid black;"><c:out value="${user.lastName}" /></td>
						<td style="border: 1px solid black;"><c:out value="${user.userName}" /></td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>No request specified</c:otherwise>
	</c:choose>
</body>
</html>