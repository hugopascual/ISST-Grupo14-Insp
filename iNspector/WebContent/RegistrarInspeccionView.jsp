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

<h1>El inspector ${inspector.nombre} ${inspector.apellido_1} ${inspector.apellido_2} est� registrando una inspecci�n del establecimiento ${establecimiento.nombre}</h1>
	
<h1>La fecha actual es: ${fecha} </h1>

<form action="FormRegistrarInspeccionServlet" method="post" enctype="multipart/form-data">

	<input type="text" name="establecimiento" value="${establecimiento.nombre}">
	<input type="date" name="fecha_insp" value="${fecha}" min="2018-01-01" max="${fecha}" placeholder="Fecha de la inspecci�n">
	<select name="nota">
		   <option value="" disabled selected>Nota</option>
	       <option value="Favorable">Favorable</option> 
	       <option value="Favorable condicionado">Favorable Condicionado</option> 
	       <option value="Desfavorable">Desfavorable</option> 
	</select>
	<input type="text" name="descripcion" placeholder="Comentarios adicionales sobre la inspecci�n">

    <input type="file" name="file" />
    <button type="submit">Registrar informe</button>
</form>


<h2><%@ include file = "FormPerfilUsuario.jsp" %></h2>

<h2><%@ include file = "FormListaEstablecimientos.jsp" %></h2>

<h2><%@ include file = "FormLogout.jsp" %></h2>


</body>
</html>