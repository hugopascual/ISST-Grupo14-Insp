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

import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.model.Inspeccion;
import es.upm.dit.isst.insp.model.Inspector;

/**
 * Servlet que se encarga de obtener todas las inspecciones realizadas por un inspector
 */

@WebServlet("/BotonInspeccionesInspectorServlet")
public class BotonInspeccionesInspectorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Inspector inspector = (Inspector) req.getSession().getAttribute("inspector");
		
		List<Inspeccion> inspecciones = (List<Inspeccion>) InspeccionDAOImplementation.getInstance().readAllInspecciones_Insp(inspector);	
		
		req.getSession().setAttribute("inspecciones", inspecciones);
		req.getSession().setAttribute("segun_inspector", true);//marco que quiero las inspecciones segun el inspector
		req.getSession().setAttribute("segun_establecimiento", false);
		
		getServletContext().getRequestDispatcher("/HistorialInspeccionesView.jsp").forward(req,resp);
			
	}
}