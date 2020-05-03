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
<!-- Favicon -->
<link rel="icon" href="img/favi.ico">
</head>
<body>
<%@ include file = "header.jsp" %>

<div class= container>
	<c:choose>
	
		<c:when test="${segun_establecimiento}">
			<h2>Incidencias reportadas en el establecimiento ${establecimiento.nombre}</h2>
			<p>En este establecimiento los clientes han reportado ${fn:length(incidencias_pendientes)} incidencias que aún no han sido revisadas y ${fn:length(incidencias_revisadas)} incidencias que ya han sido revisadas</p>
			
			<div>
			<c:if test="${fn:length(incidencias_pendientes)!=0}">
			<h3>Incidencias pendientes</h3>
			<table class="table" border="1">
				<thead class="my-thead">
					<tr>
						<td><b>Establecimiento</b></td>
						<td><b>Denunciante</b></td>
						<td><b>Fecha de la incidencia</b></td>
						<td><b>Gravedad</b></td>
						<td><b>Descripción</b></td>
						<td><b>Estado</b></td>
						<td><b>Detalles</b></td>
					</tr>
				</thead>
				<c:forEach items="${incidencias_pendientes}" var="incidenciai" varStatus="loop">
					<tr>
						<td>${incidenciai.establecimiento_incidencia.nombre}</td>
						<td>${incidenciai.cliente_incidencia.nombre} ${incidenciai.cliente_incidencia.apellido_1} ${incidenciai.cliente_incidencia.apellido_2}</td>
						<td>${incidenciai.fecha}</td>
						<td>${incidenciai.gravedad}</td>
						<td>${incidenciai.descripcion}</td>
						<td>${incidenciai.status}</td>
						<td>
								<button class="showhide btn btn-primary mt-1 mb-1 ml-1 mr-1" id="showHideButton_${loop.index}"> Ver detalles </button>
						</td>
					</tr>
					
					<div id="data_${loop.index}" class="modal">
  						<!-- Modal content -->
  						<div class="modal-content">
  							<h3>Detalles de la incidencia reportanda en ${incidenciai.establecimiento_incidencia.nombre}</h3>
    						<p> <b>Fecha de la incidencia:</b> ${incidenciai.fecha}</p>
    						<p> <b>Denunciante:</b> ${incidenciai.cliente_incidencia.nombre} ${incidenciai.cliente_incidencia.apellido_1} ${incidenciai.cliente_incidencia.apellido_2}</p>
    						<p> <b>Establecimiento:</b> ${incidenciai.establecimiento_incidencia.nombre}</p>
    						<p> <b>Gravedad:</b> ${incidenciai.gravedad}</p>
    						<p> <b>Estado:</b> ${incidenciai.status}</p>
    						<p> <b>Descripción:</b> ${incidenciai.descripcion}<p>
    						<img src="${pageContext.request.contextPath}/ServeImageServletInt?id=${incidenciai.id}" width="400" height="400" alt="No hay imagen adjuntada por el cliente" />
    						<button class="showhide btn btn-primary mt-1 mb-1 ml-1 mr-1" id="showHideButton_${loop.index}"> Cerrar detalles</button>
  						</div>
					</div>
				</c:forEach>	
			</table>
			</c:if>
			</div>
			<div>
			<c:if test="${fn:length(inspecciones)!=0}">
			<h3>Incidencias revisadas</h3>
			<table class="table" border="1">
				<thead class="my-thead">
				<tr>
					<td><b>Establecimiento</b></td>
					<td><b>Cliente</b></td>
					<td><b>Fecha de la incidencia</b></td>
					<td><b>Gravedad</b></td>
					<td><b>Descripción</b></td>
					<td><b>Estado</b></td>
				</tr>
				</thead>
				<c:forEach items="${incidencias_revisadas}" var="incidenciai">
					<tr>
						<td>${incidenciai.establecimiento_incidencia.nombre}</td>
						<td>${incidenciai.cliente_incidencia.nombre} ${incidenciai.cliente_incidencia.apellido_1} ${incidenciai.cliente_incidencia.apellido_2}</td>
						<td>${incidenciai.fecha}</td>
						<td>${incidenciai.gravedad}</td>
						<td>${incidenciai.descripcion}</td>
						<td>${incidenciai.status}</td>
					</tr>
				</c:forEach>	
			</table>
			</c:if>
			</div>
		</c:when>
		
		<c:when test="${segun_cliente}">
			<h2>Incidencias reportadas por el cliente ${cliente.nombre} ${cliente.apellido_1} ${cliente.apellido_2}</h2>
			<p>Este cliente ha reportado ${fn:length(incidencias)} incidencias</p>
			<c:if test="${fn:length(incidencias)!=0}">
			<table class ="table" border="1">
				<thead class="my-thead">
				<tr>
					<td><b>Fecha de la incidencia</b></td>
					<td><b>Establecimiento</b></td>
					<td><b>Gravedad</b></td>
					<td><b>Descripción de la incidencia</b></td>
					<td><b>Detalles</b></td>
				</tr>
				</thead>
				<c:forEach items="${incidencias}" var="incidenciai" varStatus="loop">
					<tr>
						<td>${incidenciai.fecha}</td>
						<td>${incidenciai.establecimiento_incidencia.nombre}</td>
						<td>${incidenciai.gravedad}</td>
						<td>${incidenciai.descripcion}</td>
						<td>
							<button class="showhide btn btn-primary mt-1 mb-1 ml-1 mr-1" id="showHideButton_${loop.index}"> Ver detalles </button>
						</td>
					</tr>
				
	
					<div id="data_${loop.index}" class="modal">
  						<!-- Modal content -->
  						<div class="modal-content">
  							<h3>Detalles de la incidencia reportanda en ${incidenciai.establecimiento_incidencia.nombre}</h3>
  							<p> <b>Fecha de la incidencia:</b> ${incidenciai.fecha}</p>
    						<p> <b>Denunciante:</b> ${incidenciai.cliente_incidencia.nombre} ${incidenciai.cliente_incidencia.apellido_1} ${incidenciai.cliente_incidencia.apellido_2}</p>
    						<p> <b>Establecimiento:</b> ${incidenciai.establecimiento_incidencia.nombre}</p>
    						<p> <b>Gravedad:</b> ${incidenciai.gravedad}</p>
    						<p> <b>Estado:</b> ${incidenciai.status}</p>
    						<p> <b>Descripción:</b> ${incidenciai.descripcion}<p>
    						<img src="${pageContext.request.contextPath}/ServeImageServletInt?id=${incidenciai.id}" width="400" height="400" alt="No hay imagen adjuntada por el cliente" />
    						<button class="showhide btn btn-primary mt-1 mb-1 ml-1 mr-1" id="showHideButton_${loop.index}"> Cerrar detalles</button>
  						</div>
					</div>
					
				</c:forEach>
			</table>
			</c:if>
		</c:when>
		
	</c:choose>

	<div class="row" id="botonesHistorialInspeccionesView">
	</div>	
</div>

<script type="text/javascript">
        
        var buttons = document.getElementsByClassName('showhide');

        for (var i=0;i<buttons.length;i++) {
        	buttons[i].addEventListener("click", clickHandler);

        }

        function clickHandler(event) {
        	var buttonId = event.target.id;	
        	var rowId = buttonId.split("_")[1];
        	var rowData = document.getElementById('data_' + rowId);
        	
        	// do whatever you want with your row 
        	if (rowData.style.display !== 'none') {
        		rowData.style.display = 'none';
        	} else {
        		rowData.style.display = 'block';
        	}
        }
</script>

</body>
</html>