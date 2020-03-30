<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>iNspector</title>
</head>
<body>

<h2>Página del inspector ${inspector.nombre} ${inspector.apellido_1} ${inspector.apellido_2}</h2>

<p>Email: ${inspector.email}</p>

<h2>
<form action="FormLogoutServlet">
<button type="submit">Logout</button>
</form>
</h2>

</body>
</html>