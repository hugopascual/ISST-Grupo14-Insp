<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="UTF-8">
<title>iNspector</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<%@ include file = "header.jsp" %>

<div class="container">
	<h2>Bienvenido administrador</h2>
	<p><b>N�mero de establecimientos registrados: </b>${fn:length(establecimientos)}</p>
	<p><b>N�mero de inspectores registrados: </b>${fn:length(inspectores)}</p>
</div>

<div class="container">
	<div class="row">
		<div class="col-lg-4">
			<h2>Inspectores</h2>
			<table border="1">
					<tr>
						<td><b>Nombre</b></td>
						<td><b>Apellidos</b></td>
						<td><b>Email</b></td>
						<td></td>
					</tr>
				<c:forEach items="${inspectores}" var="inspectori">
					<tr>
						<td>${inspectori.nombre}</td>
						<td>${inspectori.apellido_1} ${inspectori.apellido_2}</td>
						<td>${inspectori.email}</td>
						<td><%@ include file = "BotonEliminaInspector.jsp" %></td>
					</tr>
				</c:forEach>
			</table>
			</div>
			
			<div class="col-lg-8">
			<h2>Establecimientos</h2>
			<table border="1">
					<tr>
						<td><b>CIF</b></td>
						<td><b>Nombre</b></td>
						<td><b>Direcci�n</b></td>
						<td><b>Ciudad</b></td>
						<td><b>Representante Legal</b></td>
						<td><b>Tipo de establecimiento</b></td>
						<td></td>
					</tr>
				<c:forEach items="${establecimientos}" var="establecimientoi">
					<tr>
						<td>${establecimientoi.cif}</td>
						<td>${establecimientoi.nombre}</td>
						<td>${establecimientoi.direccion}</td>
						<td>${establecimientoi.ciudad}</td>
						<td>${establecimientoi.rep_legal}</td>
						<td>${establecimientoi.tipo}</td>
						<td><%@ include file = "BotonEliminaEstablecimiento.jsp" %></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</div>

<div class="container">
	<%@ include file = "FormCreaInspector.jsp" %>
</div>

<div class="container">
	<%@ include file = "FormCreaRestaurante.jsp" %>
</div>

<%@ include file = "footer.jsp" %>

</body>
</html>