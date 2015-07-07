
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="js/FriendScripts.js"></script>


<title>Add friend</title>
</head>
<body onload="openChatHandler();">
	<h1>Friends</h1>

	<table id="friendstable" border="1px">
		<c:forEach var="friend" items="${friends}">
			<tr>
				<td onClick="openChat(${friend})">${friend}</td>
			</tr>
		</c:forEach>
	</table>

	<input value="Add friend" type="button" onClick="showAddForm()">


</body>
</html>