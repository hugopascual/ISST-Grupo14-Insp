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

<!-- este archivo puede dar conflicto debido al merge -->

<h2>Est�s viendo el establecimiento ${establecimiento.nombre}</h2>

<p>Nombre: ${establecimiento.nombre}</p>
<p>Direcci�n: ${establecimiento.direccion}</p>
<p>Ciudad: ${establecimiento.ciudad}</p>
<p>N�mero de inspecciones: ${fn:length(inspecciones)}</p>
<c:choose>
	<c:when test="${not empty ultima_inspeccion}">
		<p>Nota �ltima inspecci�n: ${ultima_inspeccion.nota} <p>
		<p>Fecha de la �ltima inspecci�n: ${ultima_inspeccion.fecha_insp} </p>
	</c:when>
	<c:otherwise>
		<p>Este establecimiento a�n no tiene registrada ninguna inspecci�n <p>
	</c:otherwise>
</c:choose>

<div>
	<img src="${pageContext.request.contextPath}/ServeImageServlet?id=${establecimiento.cif}" width="500" height="350" />
</div>	

<div>
<c:choose>
	<c:when test="${soy_cliente}">
		<h2><%@ include file = "FormPerfilUsuario.jsp" %></h2>
		<p>Botones de cliente: Reportar incidencia</p>
		<h2><%@ include file = "FormIndex.jsp" %></h2>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	<c:when test="${soy_inspector}">
		<h2><%@ include file = "BotonPaginaInspeccion.jsp" %></h2>
		<h2><%@ include file = "BotonHistorialInspecciones.jsp" %></h2>
		<h2><%@ include file = "FormPerfilUsuario.jsp" %></h2>
		<h2><%@ include file = "FormIndex.jsp" %></h2>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	<c:when test="${usuario_no_registrado}">
		<p>Hemos visto que a�n no est�s registrado...</p>
		<form method="get" action="FormCreaCliente.html">
  			<button type="submit">Registrarse</button>
		</form>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	
</c:choose>
</div>	

</body>
</html>