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

import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.dao.InspectorDAOImplementation;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspeccion;
import es.upm.dit.isst.insp.model.Inspector;

/**
 * Servlet encargada de obtener el historial de inspecciones registradas en un establecimiento
 */

@WebServlet("/BotonHistorialInspeccionesServlet")
public class BotonHistorialInspeccionesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Establecimiento establecimiento = (Establecimiento) req.getSession().getAttribute("establecimiento");
		List<Inspeccion> inspecciones = (List<Inspeccion>) InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento);
		
		req.getSession().getAttribute("inspector");
		req.getSession().setAttribute("inspecciones", inspecciones);
		req.getSession().setAttribute("segun_establecimiento", true);//marcamos que queremos las inspecciones segun el establecimiento
		req.getSession().setAttribute("segun_inspector", false);
		
		getServletContext().getRequestDispatcher("/HistorialInspeccionesView.jsp").forward(req,resp);
			
	}
}