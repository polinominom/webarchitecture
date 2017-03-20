<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Warning Page</title>
</head>
<body>
<c:if test="${sessionScope.user == null}">
	<c:out value="Error: Any progress without login is forbidden! Please login first!">
	</c:out><br><br>
	<form action="AccessErrorServlet" method="POST">
		<button type="submit" name="login" value="Back">Return to the login page</button>
	</form>

</c:if>

<c:if test="${sessionScope.user != null}">
	<% response.sendRedirect("/Architecture/ListRecords.jsp");%>
</c:if>
	

</body>
</html>