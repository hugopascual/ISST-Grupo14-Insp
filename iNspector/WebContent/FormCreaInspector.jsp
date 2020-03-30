<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de un inspector en el sistema</title>
</head>
<body>

<form action="FormCreaInspectorServlet">
<input type="text" name="nombre" placeholder="Nombre">
<input type="text" name="apellido_1" placeholder="Primer Apellido">
<input type="text" name="apellido_2" placeholder="Segundo Apellido">
<input type="text" name="email" placeholder="Email">
<input type="text" name="usuario" placeholder="Usuario">
<input type="password" name="password" placeholder="Contraseña">
<button type="submit">Registrar profesor</button>
</form>

</body>
</html>