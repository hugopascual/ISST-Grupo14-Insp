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

<div class="col-lg-12">
			<h2>Registrar un nuevo inspector</h2>
			<form action="FormCreaInspectorServlet" class="form">
					<div class="form-row">
						<div class="col mb-1">
						<input type="text" name="nombre" placeholder="Nombre">
						</div>
						<div class="col">
						<input type="text" name="apellido_1" placeholder="Primer Apellido">
						</div>
						<div class="col">
						<input type="text" name="apellido_2" placeholder="Segundo Apellido">
						</div>
					</div>
					<div class="form-row">
						<div class="col">
						<input type="text" name="email" placeholder="Email">
						</div>
						<div class="col">
						<input type="text" name="usuario" placeholder="Usuario">
						</div>
						<div class="col">
						<input type="password" name="password" placeholder="Contraseña">
						</div>
					</div>
					<div class="form-row mt-1">
						<div class="col text-center">
						<button type="submit">Registrar inspector</button>
						</div>
					</div>
			</form>
</div>

</body>
</html>