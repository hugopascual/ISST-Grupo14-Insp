<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<html>

<link rel="stylesheet" href="CSS/header.css" type="text/css"></link>

<head>
<meta charset="UTF-8">
<title>iNspector</title>
</head>

<header>
	<div style='float: left'>
		<img src="files/logo_iNspector.png" alt="Logo de iNspector"
			width="250">
	</div>


	<div style='float: right'>
		<!-- selección de boton para logout dependiendo de si estas o no registrado -->
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
	
	<div style='clear:both'></div>

</header>

</html>