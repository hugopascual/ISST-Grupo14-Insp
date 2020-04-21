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

<div class= container>
	<c:choose>
		<c:when test="${segun_establecimiento}">
			<!-- CAMBIAR PARA IMPLEMENTACION SEGUN ESTABLECIMIENTO -->
			<h2>Lista de incidencias reportadas en el establecimiento ${establecimiento.nombre}</h2>
			<p>En este establecimiento los clientes han reportado ${fn:length(incidencias)} incidencias</p>
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
		<c:when test="${segun_cliente}">
			<h2>Lista de incidencias reportadas por el cliente ${cliente.nombre} ${cliente.apellido_1} ${cliente.apellido_2}</h2>
			<p>Este cliente ha reportado ${fn:length(incidencias)} incidencias</p>
			<table class ="table" border="1">
				<thead class="my-thead">
				<tr>
					<td><b>Fecha de la incidencia</b></td>
					<td><b>Cliente</b></td>
					<td><b>Establecimiento</b></td>
					<td><b>Gravedad</b></td>
					<td><b>Estado de la incidencia</b></td>
					<td><b>Descripción de la incidencia</b></td>
				</tr>
				</thead>
				<c:forEach items="${incidencias}" var="incidenciai">
					<tr>
						<td>${incidenciai.fecha}</td>
						<td>${incidenciai.cliente_incidencia.nombre} ${incidencia.cliente_incidencia.apellido_1} ${incidencia.cliente_incidencia.apellido_2}</td>
						<td>${incidenciai.establecimiento_incidencia.nombre}</td>
						<td>${incidenciai.gravedad}</td>
						<td>${incidenciai.status}</td>
						<td>${incidenciai.descripcion}</td>
						
						<!-- ANADIR BOTON DETALLE INCIDENCIA -->
						<!-- <td>
						   <form action="ServeFileServlet" method="get">
							   <input type="hidden" name="inspeccion_id" value="${inspeccioni.id}" />
							   <button type="submit">Descargar informe de inspección</button>
						   </form>
						</td> -->
					</tr>
				</c:forEach>
			</table>
		</c:when>
	</c:choose>

	<div class="row" id="botonesHistorialInspeccionesView">
		<%@ include file = "FormPerfilUsuario.jsp" %>
	</div>	
</div>

</body>
</html>