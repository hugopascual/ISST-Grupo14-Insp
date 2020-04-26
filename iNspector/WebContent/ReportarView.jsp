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
		<h1>P�gina de registro de incidencias</h1>
		<h3>El cliente ${cliente.nombre} ${cliente.apellido_1} ${cliente.apellido_2} est� reportando una incidencia en el establecimiento ${establecimiento.nombre}</h3>
	</div>
	<div class=row id="informeReportarIncidencia">
		<form action="FormReportarServlet" method="post" enctype="multipart/form-data">
			
			<p>Nombre del establecimiento: <input type="text" name="establecimiento" value="${establecimiento.nombre}" disabled></p>
			<p>Denunciante:<input type="text" name="cliente" value="${cliente.nombre} ${cliente.apellido_1} ${cliente.apellido_2}" disabled>
			<p>Fecha: <input required type="date" name="fecha_incidencia" min="1900-01-01" placeholder="Fecha de la incidencia"></p>
			<p>Nota de la inspecci�n:
				<select name="gravedad">
					   <option value="" disabled selected>Gravedad</option>
				       <option value="Muy grave">Muy grave</option> 
				       <option value="Grave">Grave</option> 
				       <option value="Leve">Leve</option> 
				</select>
			</p>
			<p>Descripci�n de la incidencia</p>
			<textarea rows="10" cols="40" name="descripcion" placeholder="Descripci�n de la incidencia"></textarea>
			<p>Si quieres puedes adjuntar una foto relacionada con la incidencia. <input type="file" name="image" /></p>
		    <p><button class="btn btn-primary" type="submit" id="botonReportarIncidencia">Reportar incidencia</button></p>
		    
		</form>
	</div>
		
	<div class= row id="botonesHistorialInspeccionesView">
		<%@ include file = "FormPerfilUsuario.jsp" %>
		<%@ include file = "FormListaEstablecimientos.jsp" %>
	</div>
</div>

</body>
</html>