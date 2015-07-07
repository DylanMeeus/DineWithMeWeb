<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<form method="post" action="Servlet?action=login">
<fieldset>
<!--  only username/password at first. Legacy reasons (mobile apps, swing) -->
<label>Username <input type="text" id="username" name="username"/></label><br>
<label>Password <input type="password" id="password" name="password" /></label><br>
<input type="submit" value="login">
</fieldset>
</form>
</body>
</html>