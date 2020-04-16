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
<h1>Página de registro de inspecciones</h1>
<h3>El inspector ${inspector.nombre} ${inspector.apellido_1} ${inspector.apellido_2} está registrando una inspección del establecimiento ${establecimiento.nombre}</h3>
</div>
<div class=row id="informeRegistrarInspeccion">
<form action="FormRegistrarInspeccionServlet" method="post" enctype="multipart/form-data">
	
	<p>Nombre del establecimiento: <input type="text" name="establecimiento" value="${establecimiento.nombre}"></p>
	<p>Fecha: <input required type="date" name="fecha_insp" min="1900-01-01" placeholder="Fecha de la inspección"></p>
	<p> Nota de la inspección:
	<select name="nota">
		   <option value="" disabled selected>Nota</option>
	       <option value="Favorable">Favorable</option> 
	       <option value="Favorable condicionado">Favorable Condicionado</option> 
	       <option value="Desfavorable">Desfavorable</option> 
	</select>
	</p>
	<p>
	<!--  <input type="text" name="descripcion" placeholder="Comentarios adicionales sobre la inspección" style="width:500px;height:200px"> -->
	<textarea rows="10" cols="40" name="descripcion" placeholder="Comentarios adicionales sobre la inspección"></textarea>
	</p>
	<p>
    <input type="file" name="file" />
 	</p>
    <p>
    <button class="btn btn-primary" type="submit" id="botonRegistrarInforme">Registrar informe</button>
    </p>
    
</form>
</div>

<div class= row id="botonesHistorialInspeccionesView">
<h2><%@ include file = "FormPerfilUsuario.jsp" %></h2>

<h2><%@ include file = "FormListaEstablecimientos.jsp" %></h2>



</div>
</div>
</body>
</html>