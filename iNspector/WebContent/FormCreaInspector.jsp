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
						<input type="text" name="nombre" placeholder="Nombre" required>
						</div>
						<div class="col">
						<input type="text" name="apellido_1" placeholder="Primer Apellido" required>
						</div>
						<div class="col">
						<input type="text" name="apellido_2" placeholder="Segundo Apellido">
						</div>
					</div>
					<div class="form-row">
						<div class="col">
						<input type="text" name="email" placeholder="Email" required>
						</div>
						<div class="col">
						<input type="text" name="usuario" placeholder="Usuario" required>
						</div>
						<div class="col">
						<input type="password" name="password" placeholder="Contraseña" required>
						</div>
					</div>
					<div class="form-row mt-1">
						<div class="col text-center">
						<button type="submit">Registrar inspector</button>
						</div>
					</div>
			</form>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>