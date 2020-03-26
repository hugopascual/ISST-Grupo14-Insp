<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h2>Admin</h2>
<p><b>Número de trabajos activos: </b>${fn:length(tfgs)}</p>
<p><b>Número de profesores activos: </b>${fn:length(profesores)}</p>

<h2>Profesores</h2>
<table border="1">
<c:forEach items="${profesores}" var="profesori">
<tr>
<td>${profesori.name}</td>
<td>${profesori.email}</td>
</tr>
</c:forEach>
</table>

<h2>TFGs</h2>
<table border="1">
<c:forEach items="${tfgs}" var="tfgi">
<tr>
<td>${tfgi.title}</td>
<td>${tfgi.status}</td>
<td>${tfgi.email}</td>
</tr>
</c:forEach>
</table>


<h2>Registrar un nuevo profesor</h2>
<%@ include file = "FormCreaProfesor.jsp" %>

<h2>
<form action="FormLogoutServlet">
<button type="submit">Logout</button>
</form>
</h2>

</body>
</html>