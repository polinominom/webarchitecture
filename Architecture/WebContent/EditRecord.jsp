<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> Edit Record</title>
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
	<c:if test="${sessionScope.userLogged!=null && sessionScope.userLogged==true}">
	<c:if test="${sessionScope.editingRecord != null}">
	
		<c:out value="User: "> </c:out>
		<c:out value="${sessionScope.user.getUsername()}"> </c:out>
		<c:out value="|| Edit Record"></c:out>
		<br><br>
		<form action="RecordEditServlet" method="POST">
			<input type="submit" title="List Records" name="record" value="List">
			<input type="submit" title="Logout" name="logout" value="Logout">
			<br>
			<br>
			<p>Name:<br>	<input type="text" name="name" value="${sessionScope.editingRecord.getRecord().getName()}"/> </p>
			<br>
			<p>Surname:<br>		<input type="text" name="surname" value="${sessionScope.editingRecord.getRecord().getSurname()}"/> </p>
			<br>
			<p>Address:<br>		<input type="text" name="address" value="${sessionScope.editingRecord.getRecord().getAddress()}"/> </p>
			<br>
			<p>Telephone:<br>		<input type="text" name="telephone" value="${sessionScope.editingRecord.getRecord().getTelephone()}"/> </p>
			<br>
			<p>Age:<br>	<input type="text" name="age" value="${sessionScope.editingRecord.getRecord().getAge() }"/> </p>
			<br>
			<select name="sessionType">
  				<option value="Session">Session Scope</option>
  				<option value="Application">Application Scope</option>
			</select>
			<br>
			<input type="submit" title="Record" name="record" value="Record"/><br>
		</form>
	</c:if>
	</c:if>
	
	<c:if test="${sessionScope.user == null}">
		<% response.sendRedirect("/Architecture/Warning.jsp");%>
	</c:if>
</body>
</html>