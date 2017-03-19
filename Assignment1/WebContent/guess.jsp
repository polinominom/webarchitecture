<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<?xml version="1.0" encoding="UTF-8" ?>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
</head>
<body>

<form action="play/new" method="POST">
	<INPUT type="submit" title="NEW GAME!!!" value="NEW GAME!!!"/>	
</form>


<div id="prevGuesses">
	<h2> Previous Guesses </h2>
	<!-- ${applicationScope.randomNo} -->
	<ul>
		<c:forEach var="guess" items="${sessionScope.guesses}">
			<li> <c:out value="${guess.nick}" /> (<c:out value="${guess.date}" />) - <c:out value="${guess.heat}" />  
				<c:out value="${guess.value}" />
		    </li>
		</c:forEach>
	</ul>
</div>

<div id="newGuess">
	<form action="play" method="POST">
	<FIELDSET title="New Guess">
		<legend>Nick Name:</legend>
		<c:if test="${sessionScope.nick == null}">
		 <input type="text" name="name"> </input>
		</c:if>
		
		<c:if test="${sessionScope.nick != null}">
		 <input type="text" value="${sessionScope.nick}" readonly="readonly"/>
		</c:if>	 
		 
		 <br/>
		<legend>Guess : </legend><input type="text" name="guess"> </input><br/>
		<c:if test="${requestScope.won != true}">
			<input type="submit" />
		</c:if> 
		<c:if test="${requestScope.won == true}">
			<input type="submit" disabled="disabled" value="YOU WON SILLY!!!"/>
		</c:if>
	</FIELDSET>
	</form>
</div>

</body>
</html>
