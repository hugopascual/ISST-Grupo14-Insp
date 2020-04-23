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
	<h2>Lista de proximas Inspecciones</h2>
		
	<div>
	<table  class= "table" border="1">
		<thead class = "my-thead">
		<tr>
			<td><b>Nombre</b></td>
			<td><b>Fecha Prevista</b></td>
			<td><b>Acci�n</b>
		</tr>
		</thead>
	
		<c:forEach items="${establecimientos}" var="establecimientoi">
			<tr>
				<td>${establecimientoi.nombre}</td>
				<td>${establecimientoi.proxima_inspeccion}</td>
				<td><%@ include file = "BotonDetalleEstablecimiento.jsp" %></td>
			</tr>
		</c:forEach>	
	</table>
	</div>
</div>		

</body>
</html>