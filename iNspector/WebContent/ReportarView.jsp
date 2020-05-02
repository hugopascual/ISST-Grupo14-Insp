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
	<div class=row>
		<h1>Página de registro de incidencias</h1>
		<h3>El cliente ${cliente.nombre} ${cliente.apellido_1} ${cliente.apellido_2} está reportando una incidencia en el establecimiento ${establecimiento.nombre}</h3>
	</div>
	<div class=row id="informeReportarIncidencia">
		<form action="FormReportarServlet" method="post" enctype="multipart/form-data">
			
			<p>Nombre del establecimiento: <input type="text" name="establecimiento" value="${establecimiento.nombre}" disabled required></p>
			<p>Denunciante:<input type="text" name="cliente" value="${cliente.nombre} ${cliente.apellido_1} ${cliente.apellido_2}" disabled required>
			<p>Fecha: <input type="date" name="fecha_incidencia" min="2000-01-01" max=${fecha_hoy} placeholder="Fecha de la incidencia" required></p>
			<p>Nota de la inspección:
				<select name="gravedad" required>
					   <option value="" disabled selected>Gravedad</option>
				       <option value="Muy grave">Muy grave</option> 
				       <option value="Grave">Grave</option> 
				       <option value="Leve">Leve</option> 
				</select>
			</p>
			<p>Descripción de la incidencia</p>
			<textarea rows="10" cols="40" name="descripcion" placeholder="Descripción de la incidencia"></textarea>
			<p>Si quieres puedes adjuntar una foto relacionada con la incidencia. <input type="file" name="image" /></p>
		    <p><button class="btn btn-primary" type="submit" id="botonReportarIncidencia">Reportar incidencia</button></p>
		    
		</form>
	</div>
		
	<div class= row id="botonesHistorialInspeccionesView">
		<%@ include file = "FormListaEstablecimientos.jsp" %>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>