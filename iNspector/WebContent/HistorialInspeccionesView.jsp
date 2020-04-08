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

<c:choose>
	<c:when test="${segun_establecimiento}">
		<h2>Lista de inspecciones realizadas en el establecimiento ${establecimiento.nombre}</h2>
		<h3>Ahora mismo el inspector ${inspector.nombre} está visualizando el historial de inspecciones</h3>
		<h3>En este establecimiento se han realizado ${fn:length(inspecciones)} inspecciones</h3>
		<div>
		<table border="1">
			<tr>
				<td><b>Inspector</b></td>
				<td><b>Establecimiento</b></td>
				<td><b>Fecha de la inspección</b></td>
				<td><b>Nota</b></td>
				<td><b>Comentarios del inspector</b></td>
			</tr>
			<c:forEach items="${inspecciones}" var="inspeccioni">
				<tr>
					<td>${inspeccioni.inspector_realiza_inspeccion.nombre} ${inspeccioni.inspector_realiza_inspeccion.apellido_1} ${inspeccioni.inspector_realiza_inspeccion.apellido_2}</td>
					<td>${inspeccioni.establecimiento_inspeccion.nombre}</td>
					<td>${inspeccioni.fecha_insp}</td>
					<td>${inspeccioni.nota}</td>
					<td>${inspeccioni.descripcion}</td>
					<td>
					   <form action="ServeFileServlet" method="get">
						   <input type="hidden" name="inspeccion_id" value="${inspeccioni.id}" />
						   <button type="submit">Descargar informe de inspección</button>
					   </form>
					</td>
				</tr>
			</c:forEach>	
		</table>
		</div>
	</c:when>
	<c:when test="${segun_inspector}">
		<h2>Lista de inspecciones realizadas por el inspector ${inspector.nombre} ${inspector.apellido_1} ${inspector.apellido_2}</h2>
		<h3>Este inspector ha realizado ${fn:length(inspecciones)} inspecciones</h3>
		<h3>Nota. en un futuro aquí también aparecerán las inspecciones programadas para que las realice el inspector</h3>
		<table border="1">
			<tr>
				<td><b>Inspector</b></td>
				<td><b>Establecimiento</b></td>
				<td><b>Fecha de la inspección</b></td>
				<td><b>Nota</b></td>
				<td><b>Comentarios del inspector</b></td>
			</tr>
			<c:forEach items="${inspecciones}" var="inspeccioni">
				<tr>
					<td>${inspeccioni.inspector_realiza_inspeccion.nombre} ${inspeccioni.inspector_realiza_inspeccion.apellido_1} ${inspeccioni.inspector_realiza_inspeccion.apellido_2}</td>
					<td>${inspeccioni.establecimiento_inspeccion.nombre}</td>
					<td>${inspeccioni.fecha_insp}</td>
					<td>${inspeccioni.nota}</td>
					<td>${inspeccioni.descripcion}</td>
					<td>
					   <form action="ServeFileServlet" method="get">
						   <input type="hidden" name="inspeccion_id" value="${inspeccioni.id}" />
						   <button type="submit">Descargar informe de inspección</button>
					   </form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</c:when>
</c:choose>

<div>
	<boton_insp><%@ include file = "FormPerfilUsuario.jsp" %></boton_insp>
	<h2><%@ include file = "FormIndex.jsp" %></h2>
	<h2><%@ include file = "FormLogout.jsp" %></h2>
</div>		

</body>
</html>