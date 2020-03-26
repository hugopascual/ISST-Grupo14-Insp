<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de un TFG</title>
</head>
<body>

<h2>Solicitar un nuevo TFG</h2>
<form action="Form1TFGServlet">
<input type="text" name="email" placeholder="Email">
<input type="password" name="password" placeholder="Password">
<input type="text" name="name" placeholder="Nombre y apellidos">
<input type="text" name="titulo" placeholder="Título del proyecto">
<input type="text" name="profesor" placeholder="Profesor tutor">
<button type="submit">Registrar</button>
</form>

</body>
</html>