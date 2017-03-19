<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${sessionScope.user != null}">
		<c:if test="${sessionScope.userLogged == true}">
		<c:out value="User: "> </c:out>
		<c:out value="${sessionScope.user.getUsername()}"> </c:out>
		<br> <br>
		<c:out value="User's List Range:"></c:out>
		<br>
		<table>
		<tr>
			<td>Name</td><td>Surname</td><td>Address</td><td>Telephone</td><td>Age</td><td>Scope Type</td>
		</tr>
			<c:forEach var="record" items="${sessionScope.user.getRecords()}">
				<tr>
					<td>
						<c:out value="${record.getName()}"></c:out>
					</td>
					
					<td>
						<c:out value="${record.getSurname()}"></c:out>
					</td>
					
					<td>
						<c:out value="${record.getAdress()}"></c:out>
					</td>
					
					<td>
						<c:out value="${record.getTelephone()}"></c:out>
					</td>
					
					<td>
						<c:out value="${record.getAge()}"></c:out>
					</td>
					
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</c:if>
</body>
</html>