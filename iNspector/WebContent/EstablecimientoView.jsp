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

<!-- este archivo puede dar conflicto debido al merge -->

<div class="container">
	<div class="col">
		<h2>Establecimiento ${establecimiento.nombre}</h2>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<p>Nombre: ${establecimiento.nombre}</p>
			<p>Direcci�n: ${establecimiento.direccion}</p>
			<p>Ciudad: ${establecimiento.ciudad}</p>
			<p>N�mero de inspecciones: ${fn:length(inspecciones)}</p>
			<c:choose>
				<c:when test="${not empty ultima_inspeccion}">
					<p>Nota �ltima inspecci�n: ${ultima_inspeccion.nota} <p>
					<p>Fecha de la �ltima inspecci�n: ${ultima_inspeccion.fecha_insp} </p>
					<p>Fecha proxima inspeci�n: ${establecimiento.proxima_inspeccion} </p>
				</c:when>
				<c:otherwise>
					<p>Este establecimiento a�n no tiene registrada ninguna inspecci�n </p>
					<p>Fecha proxima inspeci�n: ${establecimiento.proxima_inspeccion} </p>
				</c:otherwise>
			</c:choose>
		</div>
		
		<div class="col-lg-6">
			<c:choose>
				<c:when test="${sin_imagen}">
					<div>
						<img src="img/Imagen_Establecimiento_NoDisponible.PNG" width="500" height="350" />
					</div>
				</c:when>
				<c:otherwise>
					<div>
						<img src="${pageContext.request.contextPath}/ServeImageServlet?id=${establecimiento.cif}" width="500" height="350" />
					</div>
				</c:otherwise>
			</c:choose>
		</div>
	</div>	
	
	<div>
	<c:choose>
		<c:when test="${soy_cliente}">
			<div class="row">
				<!--Aqu� se incluyen los botones de cliente-->
				<%@ include file = "BotonPaginaReportar.jsp" %>
				<%@ include file = "FormListaEstablecimientos.jsp" %>
			</div>
		</c:when>
		<c:when test="${soy_inspector}">
			<div class="row">
				<%@ include file = "BotonPaginaInspeccion.jsp" %>
				<%@ include file = "BotonHistorialInspecciones.jsp" %>
				<%@ include file = "FormListaEstablecimientos.jsp" %>
			</div>
		</c:when>
		<c:when test="${usuario_no_registrado}">
			<div class="row">
				<p>Hemos visto que a�n no est�s registrado...</p>
				<p><form method="get" action="FormCreaCliente.jsp">
		  			<button type="submit" class="btn btn-primary mt-1 mb-1 ml-1 mr-1">Registrarse</button>
				</form>
				<%@ include file = "FormListaEstablecimientos.jsp" %></p>
			</div>
		</c:when>
		
	</c:choose>
	</div>	
</div>



</body>
</html>