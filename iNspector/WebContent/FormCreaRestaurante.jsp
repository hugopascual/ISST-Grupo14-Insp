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

<div class="col-lg-12">
	<h2>Registrar un nuevo establecimiento</h2>
	<form action="FormCreaEstablecimientoServlet" method="post" enctype="multipart/form-data" class="form">
		<div class="form-row">
			<div class="col mb-1">
			<input type="text" name="cif" placeholder="CIF">
			</div>
			<div class="col">
			<input type="text" name="nombre" placeholder="Nombre">
			</div>
			<div class="col">
			<input type="text" name="direccion" placeholder="Direccion">
			</div>
			<div class="col">
			<input type="text" name="ciudad" placeholder="Ciudad">
			</div>
		</div>
		<div class="form-row">
			<div class="col">
			<input type="text" name="rep_legal" placeholder="Representante legal">
			</div>
			<div class="col">
			<select name="tipo">
			   <option value="" disabled selected>Tipo de establecimiento</option>
			       <option value="Restaurante">Restaurante</option> 
			       <option value="Cafeteria">Cafetería</option> 
			       <option value="Bar">Bar</option> 
			       <option value="Hotel">Hotel</option> 
			       <option value="Otro">Otro</option>
			</select>
			</div>
			<div class="col">
			<input type="file" name="image" />
			</div>
			<div class="col">
			<button type="submit">Registrar establecimiento</button>
			</div>
		</div>
	</form>
</div>
</body>
</html>