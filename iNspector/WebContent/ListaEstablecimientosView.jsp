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
<link rel="stylesheet" href="CSS/elements.css" type="text/css"></link>
<!-- Favicon -->
<link rel="icon" href="img/favi.ico">
</head>
<body>
<%@ include file = "header.jsp" %>

<div class="container">
	<h2>Establecimientos registrados en iNspector</h2>
	<p>Actualmente hay registrados ${fn:length(establecimientos)} establecimientos en iNspector<p>
	
	<div>
	<c:choose>
	<c:when test="${fn:length(establecimientos)==0}">
	</c:when>
	<c:otherwise>
	<table  class= "table" border="1">
		<thead class = "my-thead">
		<tr>
			<td><b>Nombre</b></td>
			<td><b>Dirección</b></td>
			<td><b>Ciudad</b></td>
			<td><b>Tipo</b></td>
			<td><b>Acción</b>
		</tr>
		</thead>
	
		<c:forEach items="${establecimientos}" var="establecimientoi">
			<tr>
				<td>${establecimientoi.nombre}</td>
				<td>${establecimientoi.direccion}</td>
				<td>${establecimientoi.ciudad}</td>
				<td>${establecimientoi.tipo}</td>
				<td><%@ include file = "BotonDetalleEstablecimiento.jsp" %></td>
			</tr>
		</c:forEach>	
	</table>
	</c:otherwise>
	</c:choose>
	</div>
	
	<div>
	<c:choose>
		<c:when test="${soy_cliente}">
		<!--por si hay que incluir algo cuando eres cliente -->
		</c:when>
		<c:when test="${soy_inspector}">
			<!--por si hay que incluir algo cuando eres inspector -->
		</c:when>
		<c:otherwise>
			<div class="row">
				<div class="col mt-2">
					<p>Hemos visto que aún no estás registrado...</p>
					<form method="get" action="FormCreaCliente.jsp">
			  			<button type="submit" class="btn btn-primary mt-1 mb-1 ml-1 mr-1">Registrarse</button>
					</form>
				</div>
			</div>
		</c:otherwise>
	</c:choose>
	</div>
</div>		



</body>
</html>