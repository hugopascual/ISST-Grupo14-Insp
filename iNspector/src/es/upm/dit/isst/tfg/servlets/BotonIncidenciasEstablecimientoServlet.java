package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import es.upm.dit.isst.tfg.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Incidencia;

/*
 * Servlet sacar toda la informacion necesaria para sacar todas las incidencias reportadas en un establecimiento
 */

@WebServlet("/BotonIncidenciasEstablecimientoServlet")
public class BotonIncidenciasEstablecimientoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Establecimiento establecimiento = (Establecimiento) req.getSession().getAttribute("establecimiento");
		
		List<Incidencia> incidencias_pendientes = (List<Incidencia>) IncidenciaDAOImplementation.getInstance().readAllIncidencias_EstablStatus(establecimiento,"pendiente");	
		List<Incidencia> incidencias_revisadas = (List<Incidencia>) IncidenciaDAOImplementation.getInstance().readAllIncidencias_EstablStatus(establecimiento,"revisada");
		
		req.getSession().setAttribute("incidencias_pendientes", incidencias_pendientes);
		req.getSession().setAttribute("incidencias_revisadas", incidencias_revisadas);
		
		req.getSession().setAttribute("segun_establecimiento", true);//marcamos que queremos las incidencias segun el establecimeinto en el que se reportan
		
		getServletContext().getRequestDispatcher("/IncidenciasView.jsp").forward(req,resp);
			
	}
}