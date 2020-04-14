<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
<meta charset="UTF-8">
<title>iNspector</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="CSS/index.css" type="text/css"></link>
</head>

<body class="text-center">
 
<%@ include file = "header.jsp" %>
 
<div class="container" >
<c:choose>
	<c:when test="${soy_cliente}">
		<h3>Bienvenido de nuevo a iNspector, ${cliente.nombre}</h3>
		<div class="btn-group-vertical">
			<%@ include file = "FormPerfilUsuario.jsp" %>
			<%@ include file = "FormListaEstablecimientos.jsp" %>
			<%@ include file = "FormLogout.jsp" %>
		</div>
	</c:when>
	<c:when test="${soy_inspector}">
		<h3>Bienvenido de nuevo a iNspector, ${inspector.nombre}</h3>
			<div class="btn-group-vertical ">
				<%@ include file = "FormPerfilUsuario.jsp" %>
				<%@ include file = "BotonInspeccionesInspector.jsp" %>
				<%@ include file = "FormListaEstablecimientos.jsp" %>
				<%@ include file = "FormLogout.jsp" %>
			</div>
	</c:when>
	<c:otherwise>
	<div class="container">
	<div class="col">
		<div class="row ">
			<h3>¡Bienvenido a iNspector!</h3>
		</div>
		<div class="row">
			<h4>Acceder a cuenta existente en la página</h4>
		</div>
		<div class="row">
			<form action="FormLoginServlet">
				<input type="text" name="email" placeholder="Email">
				<input type="password" name="password" placeholder="Password">
				<button type="submit" class="btn btn-primary">Login</button>
			</form>
		</div>
		<div class="row">
			<c:if test="${not empty loginError}">
	    		<p style="color:red;">Las credenciales introducidas no son correctas...</p>
			</c:if>
		</div>
	
		<div class="row">
			<h4>¿Aún no tienes cuenta?</h4>
		</div>	
		<div class="row">
			<form method="get" action="FormCreaCliente.jsp">
	  			<button type="submit" class="btn btn-primary">Registrarse</button>
			</form>
		</div>
		
		<div class="row">
			<h5>Si lo prefieres, puedes usar iNspector sin iniciar sesión</h5>
		</div>
		<div class="row">
			<%@ include file = "FormListaEstablecimientos.jsp" %>
		</div>
	</div>
	</div>
	
	</c:otherwise>
</c:choose>
</div>	

<c:choose>
	<c:when test="${nuevo_usuario}">
		<script>
		alert('¡Usuario registrado con éxito! Ahora introduce tus credenciales para acceder a iNspector');
		</script>
	</c:when>
</c:choose>

</body>

<jsp:include page="footer.jsp"/>

</html>