<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>

<div>
<c:choose>
	<c:when test="${soy_cliente}">
		<form action="FormLogoutServlet">
			<button type="submit">Logout</button>
		</form>
	</c:when>
	<c:when test="${soy_inspector}">
		<form action="FormLogoutServlet">
			<button type="submit">Logout</button>
		</form>
	</c:when>
	<c:otherwise>
		<form action="FormLogoutServlet">
			<button type="submit">Volver a la página principal</button>
		</form>
	</c:otherwise>
</c:choose>
</div>		

</body>
</html>