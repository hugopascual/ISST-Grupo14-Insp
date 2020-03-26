<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de un profesor</title>
</head>
<body>

<form action="FormCreaProfesorServlet">
<input type="text" name="email" placeholder="Email">
<input type="password" name="password" placeholder="Password">
<input type="text" name="name" placeholder="Nombre y apellidos">
<button type="submit">Registrar profesor</button>
</form>

</body>
</html>