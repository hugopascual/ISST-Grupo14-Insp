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
<link rel="stylesheet" href="CSS/elements.css" type="text/css"></link>
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!-- Favicon -->
<link rel="icon" href="img/favi.ico">
</head>
<body>

<%@ include file = "header.jsp" %>

<div class="container">
	<div class="col">
		<h2>Establecimiento ${establecimiento.nombre}</h2>
	</div>
	<div class="row">
		<div class="col-lg-6">
			<p>Nombre:${establecimiento.nombre}</p>
			<p>Direcci�n: ${establecimiento.direccion}</p>
			<p>Ciudad: ${establecimiento.ciudad}</p>

			<p>N�mero de inspecciones: ${fn:length(inspecciones)}</p>
			<c:choose>
				<c:when test="${soy_inspector}">
					<p>Fecha pr�xima inspecci�n: ${establecimiento.proxima_inspeccion}</p>
					<div id="oculto" style="display: none;">
						<form action="ModificarFechaInspeccionServlet" method="get">
							<input type="hidden" name="establecimientoCIF" value="${establecimiento.cif}" />
							<input required type="date" name="nueva_fecha_insp" min=${fecha_hoy} placeholder="Nueva fecha de la inspecci�n">
							<button class="btn btn-primary mt-1 ml-1 mr-1 mb-1" type="submit" id="botonNuevaFecha">Guardar</button>
						</form>
					</div>
					<button class="btn btn-primary mt-1 ml-1 mr-1 mb-1" onclick="if(document.getElementById('oculto') .style.display=='none') {document.getElementById('oculto') .style.display=''}else{document.getElementById('oculto') .style.display='none'}"  type="button">Modificar fecha </button>
				</c:when>
			</c:choose>
			<c:choose>
				<c:when test="${not empty ultima_inspeccion}">
					<p>Nota �ltima inspecci�n:<b style=${colorNota}> ${ultima_inspeccion.nota}</b> </p>
				</c:when>
				<c:otherwise>
					<p>Este establecimiento a�n no tiene registrada ninguna inspecci�n</p>
				</c:otherwise>
			</c:choose>
		</div>
		<div class=col-lg-6>
		<c:choose>
				
			<c:when test="${tiene_imagen}">
				<div>
					<img src="${pageContext.request.contextPath}/ServeImageServlet?id=${establecimiento.cif}" width="500" height="350" />
				</div>
			</c:when>
			<c:otherwise>
				<div>
					<img src="img/Imagen_Establecimiento_NoDisponible.PNG" width="500" height="350" />
				</div>
			</c:otherwise>
		</c:choose>
					
		
		</div>
		
	</div>	
	
	
	
	<div class=row>
	<c:choose>
		<c:when test="${soy_cliente}">
			<div class=row>
				<!--Aqu� se incluyen los botones de cliente-->
				<%@ include file = "BotonPaginaReportar.jsp" %>
				<%@ include file = "FormListaEstablecimientos.jsp" %>
			</div>
		</c:when>
		<c:when test="${soy_inspector}">
			<div class=row >
				<%@ include file = "BotonPaginaInspeccion.jsp" %>
				<%@ include file = "BotonHistorialInspecciones.jsp" %>
				
				<form action="BotonIncidenciasEstablecimientoServlet">
					<input type="hidden" name="cliente_email" value="${cliente.email}"/>
					<button type="submit" class="btn btn-primary mt-1 mb-1 ml-1 mr-1">Incidencias</button>
				</form>
				
				<%@ include file = "FormListaEstablecimientos.jsp" %>
			</div>
		</c:when>
		<c:when test="${usuario_no_registrado}">
			<div class=row>
				<p>Hemos visto que a�n no est�s registrado...</p>
				<p><form method="get" action="FormCreaCliente.jsp">
		  			<button type="submit" class="btn btn-primary mt-1 mb-1 ml-1 mr-1">Registrarse</button>
				</form>
				<p><%@ include file = "FormListaEstablecimientos.jsp" %></p>
			</div>
		</c:when>
		
	</c:choose>
	
	</div>	
	
	<div class=container>
	<!-- TODO colocar el mapa -->
		
		
			<%@ include file = "MapaViewDetalles.jsp" %> 
		
		<!--    -->
	
	</div>

	
	
	
	
	
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>