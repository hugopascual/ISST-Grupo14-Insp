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

<h2>Estás viendo el establecimiento ${establecimiento.nombre}</h2>

<p>Nombre: ${establecimiento.nombre}</p>
<p>Dirección: ${establecimiento.direccion}</p>
<p>Nombre: ${establecimiento.ciudad}</p>

<div>
<c:choose>
	<c:when test="${soy_cliente}">
		<boton_perfil><%@ include file = "FormPerfilUsuario.jsp" %></boton_perfil>
		<p>Botones de cliente: Reportar incidencia</p>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	<c:when test="${soy_inspector}">
		<boton_registrar><%@ include file = "BotonPaginaInspeccion.jsp" %></boton_registrar>
		<boton_registrar><%@ include file = "BotonHistorialInspecciones.jsp" %></boton_registrar>
		<boton_perfil><%@ include file = "FormPerfilUsuario.jsp" %></boton_perfil>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	<c:when test="${usuario_no_registrado}">
		<p>Un usuario no registrado está viendo el establecimiento</p>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	
</c:choose>
</div>	



</body>
</html>