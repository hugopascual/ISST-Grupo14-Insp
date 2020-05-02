<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>iNspector</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<!-- Favicon -->
<link rel="icon" href="img/favi.ico">
</head>
<body>
<%@ include file = "header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<h2>Página del cliente ${cliente.nombre} ${cliente.apellido_1} ${cliente.apellido_2}</h2>
			<p>Nombre: ${cliente.nombre}</p>
			<p>Apellidos: ${cliente.apellido_1} ${cliente.apellido_2}</p>
			<p>Usuario: ${cliente.usuario}</p>
			<p>Email: ${cliente.email}</p>
		</div>
		
		<div class="col-lg-6">
		<c:choose>
			<c:when test="${imagen}">
				<img src="${pageContext.request.contextPath}/ServeImageServlet?id=${cliente.email}" width="200" height="250" />
			</c:when>
			<c:otherwise>
				<img src="img/FotoNoDisponible.PNG" width="200" height="250"/>
				<form action="FotoPerfilServlet" method="post" enctype="multipart/form-data">
					<p>Aquí puedes actualizar tu foto de perfil</p>
					<input type="hidden" name="email" value="${cliente.email}" />
					<input type="file" name="image" />
					<button type="submit" class="btn btn-primary">Subir foto</button>
				</form>
			</c:otherwise>	
		</c:choose>
		</div>
	</div>
	<div class="row">
		
		<%@ include file = "FormListaEstablecimientos.jsp" %>
		
	</div>
</div>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>