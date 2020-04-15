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
	<div class="logo">
		<img src="files/logo_iNspector.png" alt="Logo de iNspector"
			width="250">
	</div>


	<div class="button">
		<!-- selección de boton para logout dependiendo de si estas o no registrado -->
		<c:choose>
			<c:when test="${soy_cliente}">
					<div class="logout">
						<form action="FormLogoutServlet">
							<button type="submit" class="btn btn-danger mt-1 mb-1 ml-1 mr-1">Logout</button>
						</form>
					</div>
					<div class="perfil">
						<%@ include file = "FormPerfilUsuario.jsp" %>
					</div>
			</c:when>
			<c:when test="${soy_inspector}">
					<div class="logout">
						<form action="FormLogoutServlet">
							<button type="submit" class="btn btn-danger mt-1 mb-1 ml-1 mr-1">Logout</button>
						</form>
					</div>
					<div class="perfil">
						<%@ include file = "FormPerfilUsuario.jsp" %>
					</div>
			</c:when>
			<c:otherwise>
				<form action="FormLogoutServlet">
					<button type="submit" class="btn btn-danger mt-1 mb-1 ml-1 mr-1">Volver a la página principal</button>
				</form>
			</c:otherwise>
		</c:choose>
	</div>
	
	<div style='clear:both'></div>
	

</header>

</html>