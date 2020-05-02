
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>iNspector</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
</head>
<body>

<%@ include file = "header.jsp" %>



<div class="container text-center">
<h2 style="margin-bottom:30px">¡Venga, regístrate en iNspector!</h2>
<div class="col-lg-12">
	<form action="FormCreaClienteServlet" class="form">
		<div class="form-row">
			<div class="col mb-1">
			<input type="text" name="nombre" placeholder="Nombre" required>
			</div>
			<div class="col">
			<input type="text" name="apellido_1" placeholder="Primer Apellido" required>
			</div>
		</div>
		<div class="form-row">
			<div class="col">
			<input type="text" name="apellido_2" placeholder="Segundo Apellido">
			</div>
			<div class="col mb-1">
			<input type="text" name="email" placeholder="Email" required>
			</div>
		</div>
		<div class="form-row">
			<div class="col">
			<input type="text" name="usuario" placeholder="Usuario" required>
			</div>
			<div class="col mb-1">
			<input type="password" name="password" placeholder="Contraseña" required>
			</div>
		</div>
		<div class="form-row">
			<div class="col text-center">
			<button type="submit" class="btn btn-primary">Registrarse</button>
			</div>
		</div>
	</form>
</div>
</div>


<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>