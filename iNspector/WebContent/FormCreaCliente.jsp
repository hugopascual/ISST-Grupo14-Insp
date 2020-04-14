
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
<h2>¡Venga, regístrate en iNspector!</h2>
<div class="col-lg-12">
	<form action="FormCreaClienteServlet" class="form">
		<div class="form-row">
			<div class="col mb-1">
			<input type="text" name="nombre" placeholder="Nombre">
			</div>
			<div class="col">
			<input type="text" name="apellido_1" placeholder="Primer Apellido">
			</div>
		</div>
		<div class="form-row">
			<div class="col">
			<input type="text" name="apellido_2" placeholder="Segundo Apellido">
			</div>
			<div class="col mb-1">
			<input type="text" name="email" placeholder="Email">
			</div>
		</div>
		<div class="form-row">
			<div class="col">
			<input type="text" name="usuario" placeholder="Usuario">
			</div>
			<div class="col mb-1">
			<input type="password" name="password" placeholder="Contraseña">
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

<%@ include file = "footer.jsp" %>

</body>
</html>