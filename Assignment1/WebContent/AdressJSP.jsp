<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Assignment1 - Adress Book</title>
	</head>
	<body>
		<c:if test="${sessionScope.userLogged == null}">
			<form action="AdressServlet/login" method="POST">
				<p>Username:	<input type="text" name="username"/> </p>
				<br>
				<p>Password:	<input type="text" name="password"/> </p>
				<br>
				<input type="submit" title="Login" name="sign" value="Login"/><br>
				<input type="submit" title="Register" name="sign" value="Register"/>
			</form>
		</c:if>
		<c:if test="${sessionScope.userLogged != null}">
			<c:if test="${sessionScope.userLogged == true}">
			<c:out value="${sessionScope.user.getUsername()}"></c:out>
			</c:if>

		</c:if>
		<div>
		</div>
		
	</body>
	
</html>