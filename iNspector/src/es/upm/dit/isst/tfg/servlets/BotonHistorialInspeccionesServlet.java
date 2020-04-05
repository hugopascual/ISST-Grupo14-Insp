package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.tfg.dao.InspectorDAOImplementation;
import es.upm.dit.isst.tfg.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Inspector;
import es.upm.dit.isst.tfg.model.Inspeccion;

/*
 * Servlet para sacar toda la información relativa al establecimiento seleccionado
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
		
		getServletContext().getRequestDispatcher("/HistorialInspeccionesView.jsp").forward(req,resp);
			
	}
}