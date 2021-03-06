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
<link rel="stylesheet" href="CSS/elements.css" type="text/css"></link>
<!-- Favicon -->
<link rel="icon" href="img/favi.ico">
</head>


<body class="text-center">
 
<%@ include file = "header.jsp" %>
 
<div class="container" >
<c:choose>
	<c:when test="${soy_cliente}">
	<div class="row">
		<div class="col-lg-12">
			<h3>Bienvenido de nuevo a iNspector, ${cliente.nombre}</h3>
			<div class= row id ="botonesHistorialInspeccionesView">
				<%@ include file = "BotonIncidenciasCliente.jsp" %>
				<%@ include file = "FormListaEstablecimientos.jsp" %>
			</div>
		</div>
	</div>
	</c:when>
	<c:when test="${soy_inspector}">
		<div class="row">
			<div class="col-lg-12">
				<h3>Bienvenido de nuevo a iNspector, ${inspector.nombre}</h3>
				<p> Hay ${num_incidencias_pendientes} incidencias pendientes en ${num_establecimientos} establecimientos </p>
				<div class= row id ="botonesHistorialInspeccionesView">
					<%@ include file = "BotonInspeccionesInspector.jsp" %>
					<%@ include file = "FormListaEstablecimientos.jsp" %>
					<%@ include file = "BotonProximasInspecciones.jsp" %>
					<%@ include file = "BotonIncidenciasPendientes.jsp" %>
				</div>
			</div>
		</div>
	</c:when>
	<c:otherwise>
	<div class="container">
	<div class="row">
	<div class="col-lg-6">
		<div class="row mt-1 mb-1 ml-1 mr-1">
			<h3>�Bienvenido a iNspector!</h3>
		</div>
		<div class="row mt-1 mb-1 ml-1 mr-1">
			<h4>Acceder a cuenta existente en la p�gina</h4>
		</div>
		<div class="row mt-1 mb-1 ml-1 mr-1">
			<form action="FormLoginServlet">
				<input type="text" name="email" placeholder="Email">
				<input type="password" name="password" placeholder="Password">
				<button type="submit" class="btn btn-primary">Login</button>
			</form>
		</div>
		<div class="row mt-1 mb-1 ml-1 mr-1">
			<c:if test="${not empty loginError}">
	    		<p style="color:red;">Las credenciales introducidas no son correctas...</p>
			</c:if>
		</div>
	
		<div class="row mt-1 mb-1 ml-1 mr-1">
			<h4>�A�n no tienes cuenta?</h4>
		</div>	
		<div class="row mt-1 mb-1 ml-1 mr-1">
			<form method="get" action="FormCreaCliente.jsp">
	  			<button type="submit" class="btn btn-primary mt-1 mb-1 ml-1 mr-1">Registrarse</button>
			</form>
		</div>
		
		<div class="row mt-1 mb-1 ml-1 mr-1">
			<h5>Si lo prefieres, puedes usar iNspector sin iniciar sesi�n</h5>
		</div>
		<div class="row mt-1 mb-1 ml-1 mr-1">
			<%@ include file = "FormListaEstablecimientos.jsp" %>
		</div>
	</div>
	<div class="col-lg-6">
		<img src="img/indeximg.jpg" class="img-fluid" alt="Responsive image">
	</div>
	</div>
	</div>
	
	</c:otherwise>
</c:choose>

</div>	

<c:choose>
	<c:when test="${nuevo_usuario}">
		<script>
		alert('�Usuario registrado con �xito! Ahora introduce tus credenciales para acceder a iNspector');
		</script>
	</c:when>
</c:choose>

</body>

</html>