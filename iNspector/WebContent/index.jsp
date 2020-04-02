<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<head>
<meta charset="UTF-8">
<title>iNspector</title>
</head>
<body>

<div>
<c:choose>
	<c:when test="${soy_cliente}">
		<h1>Bienvenido de nuevo a iNspector, ${cliente.nombre}</h1>
		<boton_cli><%@ include file = "FormPerfilUsuario.jsp" %></boton_cli>
		<h2><%@ include file = "FormListaEstablecimientos.jsp" %></h2>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	<c:when test="${soy_inspector}">
		<h1>Bienvenido de nuevo a iNspector, ${inspector.nombre}</h1>
		<boton_cli><%@ include file = "FormPerfilUsuario.jsp" %></boton_cli>
		<h2><%@ include file = "FormListaEstablecimientos.jsp" %></h2>
		<h2><%@ include file = "FormLogout.jsp" %></h2>
	</c:when>
	<c:otherwise>
		<h1>¡Bienvenido a iNspector!</h1>
		<h2>Acceder a cuenta existente en la página</h2>
	
		<form action="FormLoginServlet">
			<input type="text" name="email" placeholder="Email">
			<input type="password" name="password" placeholder="Password">
			<button type="submit">Login</button>
		</form>

		<h3>¿Aún no tienes cuenta?</h3>
		<form method="get" action="FormCreaCliente.html">
  			<button type="submit">Registrarse</button>
		</form>
		
		<div>
		<h3>Si lo prefieres, puedes usar iNspector sin iniciar sesión</h3>
		<h2><%@ include file = "FormListaEstablecimientos.jsp" %></h2>
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
</html>