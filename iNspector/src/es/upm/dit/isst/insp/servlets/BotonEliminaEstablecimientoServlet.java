/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspeccion;
import es.upm.dit.isst.insp.model.Incidencia;

/**
 * Servlet que realiza las tareas necesarias para eliminar un establecimiento
 * 
 * Antes de eliminar un establecimiento, es necesario eliminar todas las incidencias e inspecciones
 * asociadas a el, ya que sus claves primarias son claves foraneas del establecimiento
 */

@WebServlet("/BotonEliminaEstablecimientoServlet")
public class BotonEliminaEstablecimientoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//a través del indentificador del establecimiento (CIF) obtengo el establecimiento correspondiente
		Establecimiento establecimiento = EstablecimientoDAOImplementation.getInstance().read(req.getParameter("establecimientoCIF"));
		
		//antes de eliminar un establecimiento hay que eliminar todas las inspecciones e incidencias asociadas a este
		List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento);
		for(Inspeccion inspeccion : inspecciones) {
			InspeccionDAOImplementation.getInstance().delete(inspeccion);
		}
		
		List<Incidencia> incidencias = IncidenciaDAOImplementation.getInstance().readAllIncidencias_Establ(establecimiento);
		for(Incidencia incidencia : incidencias) {
			IncidenciaDAOImplementation.getInstance().delete(incidencia);
		}
	
		//elimino el establecimiento de la base de datos
		EstablecimientoDAOImplementation.getInstance().delete(establecimiento);
		
		//actualizo la lista de establecimientos 
		List<Establecimiento> establecimientos = (List<Establecimiento>) EstablecimientoDAOImplementation.getInstance().readAll();
		
		req.getSession().setAttribute("establecimientos", establecimientos);
		getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
	}
}