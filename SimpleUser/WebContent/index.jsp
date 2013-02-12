<%@ page contentType="text/html" %>
<%-- <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %> --%>

<head>
<title>Welcome to Jason's simple User App</title>
</head>
<body>
<a href="http://localhost:8080/SimpleApp/ServletTestRunner?suite=test.SimpleDAOTestServlet&transform=yes">Go to the Simple JDBC Tests</a><br/>
<a href="http://localhost:8080/SimpleApp/ServletTestRunner?suite=test.HibernateDAOTestServlet&transform=yes">Go to the DAO Hibernate Tests</a><br/>
<a href="http://localhost:8080/SimpleApp/ServletTestRunner?suite=test.HibernateSpringDAOTestServlet&transform=yes">Go to the DAO Hibernate Spring Tests</a><br/>
<a href="addUser.jsp">Add a Person using Spring/Hibernate stuff.</a><br/>
<a href="updateUser.jsp">Update a Person using Spring/Hibernate stuff.</a><br/>
<a href="removeUser.jsp">Remove a Person using Spring/Hibernate stuff.</a><br/>
</body>
</html>
