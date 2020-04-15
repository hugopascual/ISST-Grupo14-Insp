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

<form action="BotonHistorialInspeccionesServlet">
	<input type="hidden" name="establecimientoCIF" value="${establecimientoi.cif}" />
	<input type="hidden" name="inspector_email" value="${inspector.email}"/>
	<button type="submit" class="btn btn-primary mt-1 mb-1 ml-1 mr-1">Ver inspecciones</button>
</form>

</body>
</html>