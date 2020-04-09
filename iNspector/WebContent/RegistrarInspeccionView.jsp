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

<h1>Página de registro de inspecciones</h1>
<h3>El inspector ${inspector.nombre} ${inspector.apellido_1} ${inspector.apellido_2} está registrando una inspección del establecimiento ${establecimiento.nombre}</h3>
	
<form action="FormRegistrarInspeccionServlet" method="post" enctype="multipart/form-data">
	<input type="text" name="establecimiento" value="${establecimiento.nombre}">
	<input required type="date" name="fecha_insp" min="1900-01-01" placeholder="Fecha de la inspección">
	<select name="nota">
		   <option value="" disabled selected>Nota</option>
	       <option value="Favorable">Favorable</option> 
	       <option value="Favorable condicionado">Favorable Condicionado</option> 
	       <option value="Desfavorable">Desfavorable</option> 
	</select>
	<input type="text" name="descripcion" placeholder="Comentarios adicionales sobre la inspección">

    <input type="file" name="file" />
    <button type="submit">Registrar informe</button>
</form>


<h2><%@ include file = "FormPerfilUsuario.jsp" %></h2>

<h2><%@ include file = "FormListaEstablecimientos.jsp" %></h2>

<h2><%@ include file = "FormLogout.jsp" %></h2>


</body>
</html>