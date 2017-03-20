<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		table, th, td {
		    border: 1px solid black;
		    border-collapse: collapse;
		    text-align: middle;
		}
		th, td {
		    padding: 5px;
		    text-align: middle;
		}
		table#t01 {
		    width: 100%;    
		    background-color: #f1f1c1;
		}
		tr#head{
			background-color: #99d6ff;
		}
		td#application{
			background-color: #ff9999;
		}
		
		td#session{
			background-color: #66ffcc;
		}
	</style>
</head>
<body>
	<c:if test="${sessionScope.user != null}">
		<c:if test="${sessionScope.userLogged == true}">
		<c:out value="User: "> </c:out>
		<c:out value="${sessionScope.user.getUsername()}"> </c:out>
		<c:out value="|| List Records"></c:out>
		<br><br>
		<form action="RecordEnterServlet/new" method="POST">
			<input type="submit" title="New Record" name="new" value="New"/>
			<input type="submit" title="Logout" name="logout" value="Logout"/>
		</form>
		<br><br>
		<c:out value="Record List for this user: "></c:out>
		<br>
		<table style="width:100%">
		<tr id="head">
			<td>Name</td><td>Surname</td><td>Address</td><td>Telephone</td><td>Age</td><td>Scope Type</td><td>Edit &amp; Delete</td>
		</tr>
			<c:if test="${records != null}">
				<c:forEach var="applicationRecord" items="${records}" varStatus="loop">
					<tr>
						<td>
							<c:out value="${applicationRecord.getName()}"></c:out>
						</td>
						
						<td>
							<c:out value="${applicationRecord.getSurname()}"></c:out>
						</td>
						
						<td>
							<c:out value="${applicationRecord.getAddress()}"></c:out>
						</td>
						
						<td>
							<c:out value="${applicationRecord.getTelephone()}"></c:out>
						</td>
						
						<td>
							<c:out value="${applicationRecord.getAge()}"></c:out>
						</td>
						
						<td id="application">
							<c:out value="${applicationRecord.getSessionType()}"></c:out>
						</td>
						<td>
						<form action="RecordEnterServlet/applicationEdit" method="POST">
							<button type="submit" title="Edit" name="edit" value="${loop.index}">Edit</button>
							<button type="submit" title="Delete" name="delete" value="${loop.index}">Delete</button>
						</form>	
						</td>
						
					</tr>				
				</c:forEach>
			
			</c:if>
			<c:forEach var="record" items="${sessionScope.user.getRecords()}" varStatus="loop">
				<tr>
					<td>
						<c:out value="${record.getName()}"></c:out>
					</td>
					
					<td>
						<c:out value="${record.getSurname()}"></c:out>
					</td>
					
					<td>
						<c:out value="${record.getAddress()}"></c:out>
					</td>
					
					<td>
						<c:out value="${record.getTelephone()}"></c:out>
					</td>
					
					<td>
						<c:out value="${record.getAge()}"></c:out>
					</td>
					
					<td id="session">
						<c:out value="${record.getSessionType()}"></c:out>
					</td>
					<td>
						<form action="RecordEnterServlet/sessionEdit" method="POST">
							<button type="submit" title="Edit" name="edit" value="${loop.index}">Edit</button>
							<button type="submit" title="Edit" name="delete" value="${loop.index}">Delete</button>
						</form>	
					</td>
					
				</tr>
			</c:forEach>
		</table>
		</c:if>
	</c:if>
	
	<c:if test="${sessionScope.user == null}">
		<% response.sendRedirect("/Architecture/Warning.jsp");%>
	</c:if>
	
</body>
</html>