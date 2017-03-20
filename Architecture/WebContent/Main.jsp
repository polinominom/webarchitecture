<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Assignment1 - Main Login Page</title>
		<style>
input[type=text], select {
    width: 45%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

input[type=submit] {
    width: 45%;
    background-color: #4CAF50;
    color: white;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
}

input[type=submit]:hover {
    background-color: #45a049;
}

div {
    border-radius: 5px;
    background-color: #f2f2f2;
    padding: 20px;
}
</style>
	</head>
	
	<body>
	<c:if test="${sessionScope.userLogged==null }">
		<form action="LoginServlet/login" method="POST">
			<p>Username:<br>	<input type="text" name="username"/> </p>
			<br>
			<p>Password:<br>	<input type="text" name="password"/> </p>
			<br>
			<input type="submit" title="Login" name="sign" value="Login"/><br>
			<input type="submit" title="Register" name="sign" value="Register"/>
		</form>
	</c:if>

	
	<c:if test="${sessionScope.userLogged!=null }">
		<c:if test="${sessionScope.userLogged==true }">
			<!-- User already logged in! Redirect to the Record List -->
			<% response.sendRedirect("/Architecture/ListRecords.jsp"); %>
		</c:if>
	</c:if>
	
	<c:if test="${sessionScope.registerMessage != null}">
		<br>
		<p style="color:red;">
			<c:out value="${sessionScope.registerMessage}"></c:out> 
		</p>
	</c:if>
	
	<c:if test="${sessionScope.loginMessage != null}">
		<br>
		<p style="color:red;">
			<c:out value="${sessionScope.loginMessage}"></c:out> 
		</p>
	</c:if>
	
	
		
	</body>
	
</html>