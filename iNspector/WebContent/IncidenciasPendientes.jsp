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
	<h2>Lista de Incidencias Pendientes</h2>
	<p> Hay ${num_incidencias_pendientes} incidencias pendientes en ${num_establecimientos} establecimientos </p>
		
	<div>
	<c:if test="${num_incidencias_pendientes !=0}">
	<table  class= "table" border="1">
		<thead class = "my-thead">
		<tr>
			<td><b>Establecimiento</b></td>
			<td><b>Nº Incidencias Pendientes</b></td>
			<td><b>Ver indicencias</b>
		</tr>
		</thead>
	
		<c:forEach var="establecimientoi" items="${num_incidencias_establecimiento}">
			<tr>
				<td>${establecimientoi.key.nombre}</td>
				<td>${establecimientoi.value}</td>
				<td>
					<form action="BotonDetalleEstablecimientoServlet">
						<input type="hidden" name="establecimientoCIF" value="${establecimientoi.key.cif}" />
						<button type="submit" class="btn btn-primary">Ver establecimiento</button>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
	</c:if>
	</div>
</div>		

</body>
</html>