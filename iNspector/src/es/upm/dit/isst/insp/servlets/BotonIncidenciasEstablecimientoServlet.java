/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Incidencia;

/**
 * Servlet que obtiene todas las incidencias reportadas en un establecimiento, clasificadas según su estado
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
		req.getSession().setAttribute("segun_establecimiento", true);//marco que quiero las incidencias segun el establecimiento
		
		getServletContext().getRequestDispatcher("/IncidenciasView.jsp").forward(req,resp);
			
	}
}