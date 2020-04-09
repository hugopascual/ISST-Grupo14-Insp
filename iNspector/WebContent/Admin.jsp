<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>iNspector</title>
</head>
<body>

<h2>Bienvenido administrador</h2>
<p><b>Número de establecimientos registrados: </b>${fn:length(establecimientos)}</p>
<p><b>Número de inspectores registrados: </b>${fn:length(inspectores)}</p>

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

<h2>Establecimientos</h2>
<table border="1">
		<tr>
			<td><b>CIF</b></td>
			<td><b>Nombre</b></td>
			<td><b>Dirección</b></td>
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


<h2>Registrar un nuevo inspector</h2>
<%@ include file = "FormCreaInspector.jsp" %>

<h2>Registrar un nuevo establecimiento</h2>
<%@ include file = "FormCreaRestaurante.jsp" %>

<h2>
<%@ include file = "FormLogout.jsp" %>
</h2>


</body>
</html>