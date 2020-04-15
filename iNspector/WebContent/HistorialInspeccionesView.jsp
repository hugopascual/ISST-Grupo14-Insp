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

<!-- este archivo puede dar conflicto debido al merge -->
<div class= container>

<c:choose>
	<c:when test="${segun_establecimiento}">
		<h2>Lista de inspecciones realizadas en el establecimiento ${establecimiento.nombre}</h2>
		<p>Ahora mismo el inspector ${inspector.nombre} está visualizando el historial de inspecciones</p>
		<p>En este establecimiento se han realizado ${fn:length(inspecciones)} inspecciones</p>
		<div>
		<table class="table" border="1">
			<thead class="my-thead">
			<tr>
				<td><b>Inspector</b></td>
				<td><b>Establecimiento</b></td>
				<td><b>Fecha de la inspección</b></td>
				<td><b>Nota</b></td>
				<td><b>Comentarios del inspector</b></td>
				<td><b>Informe</b></td>
			</tr>
			</thead>
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
		<p>Este inspector ha realizado ${fn:length(inspecciones)} inspecciones</p>
		<p>Nota. en un futuro aquí también aparecerán las inspecciones programadas para que las realice el inspector</p>
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

<div class="row" id="botonesHistorialInspeccionesView">
	<h2><%@ include file = "FormPerfilUsuario.jsp" %></h2>
	<h2><%@ include file = "FormIndex.jsp" %></h2>
	<h2><%@ include file = "FormLogout.jsp" %></h2>
</div>	
</div>


</body>
</html>