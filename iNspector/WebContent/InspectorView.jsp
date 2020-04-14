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
<%@ include file = "header.jsp" %>

<h2>Página del inspector ${inspector.nombre} ${inspector.apellido_1} ${inspector.apellido_2}</h2>

<p>Nombre: ${inspector.nombre}<p>
<p>Apellidos: ${inspector.apellido_1} ${inspector.apellido_2}<p>
<p>Usuario: ${inspector.usuario}<p>
<p>Email: ${inspector.email}<p>

<div>
	
</div>

<div>
<c:choose>
	<c:when test="${imagen}">
		<img src="${pageContext.request.contextPath}/ServeImageServlet?id=${inspector.email}" width="200" height="250" />
	</c:when>
	<c:otherwise>
		<img src="img/FotoNoDisponible.PNG" width="200" height="250"/>
		<form action="FotoPerfilServlet" method="post" enctype="multipart/form-data">
			<p>Aquí puedes actualizar tu foto de perfil</p>
			<input type="hidden" name="email" value="${inspector.email}" />
			<input type="file" name="image" />
			<button type="submit">Subir foto</button>
		</form>
	</c:otherwise>	
</c:choose>
</div>	

<h2><%@ include file = "FormListaEstablecimientos.jsp" %></h2>

<h2><%@ include file = "FormIndex.jsp" %></h2>

<h2><%@ include file = "FormLogout.jsp" %></h2>

<%@ include file = "footer.jsp" %>
</body>
</html>