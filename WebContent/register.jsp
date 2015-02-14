<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register (DWM)</title>
</head>
<body>
<h1> Register!</h1>

<form method="post" action="Servlet?register">
<fieldset>
<label>Username <input type="text" id="username" name="username"/></label><br>
<label>First name<input type="text" id="first" name="first"/></label><br>
<label> Last name <input type="text" id="last" name="last"/></label><br>
<label>  e-mail <input type="text" id="mail" name="mail"/></label>

<input type="submit" value="Register"/>
</fieldset>
</form>

</body>
</html>