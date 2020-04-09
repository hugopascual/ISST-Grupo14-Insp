<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Registro de un restaurante</title>
</head>
<body>

<form action="FormCreaEstablecimientoServlet" method="post" enctype="multipart/form-data">
	<input type="text" name="cif" placeholder="CIF">
	<input type="text" name="nombre" placeholder="Nombre">
	<input type="text" name="direccion" placeholder="Direccion">
	<input type="text" name="ciudad" placeholder="Ciudad">
	<input type="text" name="rep_legal" placeholder="Representante legal">
	<select name="tipo">
	   <option value="" disabled selected>Tipo de establecimiento</option>
	       <option value="Restaurante">Restaurante</option> 
	       <option value="Cafeteria">Cafetería</option> 
	       <option value="Bar">Bar</option> 
	       <option value="Hotel">Hotel</option> 
	</select>
	<input type="file" name="image" />
	<button type="submit">Registrar establecimiento</button>
</form>

</body>
</html>