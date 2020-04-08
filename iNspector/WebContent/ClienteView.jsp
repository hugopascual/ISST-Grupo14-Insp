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

<h2>Página del cliente ${cliente.nombre} ${cliente.apellido_1} ${cliente.apellido_2}</h2>
<p>Nombre: ${cliente.nombre}<p>
<p>Apellidos: ${cliente.apellido_1} ${cliente.apellido_2}<p>
<p>Usuario: ${cliente.usuario}<p>
<p>Email: ${cliente.email}<p>

<h2><%@ include file = "FormListaEstablecimientos.jsp" %></h2>

<h2><%@ include file = "FormIndex.jsp" %></h2>

<h2><%@ include file = "FormLogout.jsp" %></h2>


</body>
</html>