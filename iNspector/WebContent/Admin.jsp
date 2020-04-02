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
	<c:forEach items="${inspectores}" var="inspectori">
		<tr>
			<td>${inspectori.nombre}</td>
			<td>${inspectori.apellido_1}</td>
			<td>${inspectori.apellido_2}</td>
			<td>${inspectori.email}</td>
			<td><%@ include file = "BotonEliminaInspector.jsp" %></td>
		</tr>
	</c:forEach>
</table>

<h2>Establecimientos</h2>
<table border="1">
	<c:forEach items="${establecimientos}" var="establecimientoi">
		<tr>
			<td>${establecimientoi.cif}</td>
			<td>${establecimientoi.nombre}</td>
			<td>${establecimientoi.direccion}</td>
			<td>${establecimientoi.ciudad}</td>
			<td>${establecimientoi.rep_legal}</td>
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