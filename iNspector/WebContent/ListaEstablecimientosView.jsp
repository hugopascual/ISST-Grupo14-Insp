<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>iNspector</title>
</head>
<body>

<h2>Lista de establecimientos registrados en iNspector</h2>
<h3>Actualmente hay registrados ${fn:length(establecimientos)} establecimientos</h3>

<div>
<table border="1">
	<tr>
		<td><b>Nombre</b></td>
		<td><b>Dirección</b></td>
		<td><b>Ciudad</b></td>
		<td><b>Tipo</b></td>
	</tr>

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
</div>

<div>
<c:choose>
	<c:when test="${soy_cliente}">
		<boton_cli><%@ include file = "FormPerfilUsuario.jsp" %></boton_cli>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	<c:when test="${soy_inspector}">
		<boton_insp><%@ include file = "FormPerfilUsuario.jsp" %></boton_insp>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	<c:otherwise>
		<p>Hemos visto que aún no estás registrado...</p>
		<form method="get" action="FormCreaCliente.html">
  			<button type="submit">Registrarse</button>
		</form>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:otherwise>
</c:choose>
</div>		



</body>
</html>