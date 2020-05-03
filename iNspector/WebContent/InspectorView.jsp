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
<!-- Favicon -->
<link rel="icon" href="img/favi.ico">
</head>

<body>
<%@ include file = "header.jsp" %>

<div class="container">
	<div class="row">
		<div class="col-lg-6">
			<h2>Página del inspector ${inspector.nombre} ${inspector.apellido_1} ${inspector.apellido_2}</h2>
			
			<p><b>Nombre:</b> ${inspector.nombre}</p>
			<p><b>Apellidos:</b> ${inspector.apellido_1} ${inspector.apellido_2}</p>
			<p><b>Usuario:</b> ${inspector.usuario}</p>
			<p><b>Email:</b> ${inspector.email}</p>
			
		</div>
		<div class="col-lg-6">
			<c:choose>
				<c:when test="${imagen}">
					<img src="${pageContext.request.contextPath}/ServeImageServlet?id=${inspector.email}" width="200" height="250" />
				</c:when>
				<c:otherwise>
					<img src="img/FotoNoDisponible.PNG" width="200" height="250"/>
					<form action="FotoPerfilServlet" method="post" enctype="multipart/form-data">
						<p>Aquí puedes actualizar tu foto de perfil</p>
						<input type="hidden" name="email" value="${inspector.email}" />
						<input type="file" name="image"/>
						<button type="submit" class="btn btn-primary">Subir foto</button>
					</form>
				</c:otherwise>	
			</c:choose>
		</div>	
	</div>
	<div class="row">
		
		<%@ include file = "FormListaEstablecimientos.jsp" %>
		
	</div>
</div>



</body>
</html>