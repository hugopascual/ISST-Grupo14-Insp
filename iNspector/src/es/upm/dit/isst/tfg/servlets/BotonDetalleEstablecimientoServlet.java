package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.tfg.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Inspeccion;

/*
 * Servlet para sacar toda la informaci�n relativa al establecimiento seleccionado
 */

@WebServlet("/BotonDetalleEstablecimientoServlet")
public class BotonDetalleEstablecimientoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object soy_cliente = req.getSession().getAttribute("soy_cliente");
		Object soy_inspector = req.getSession().getAttribute("soy_inspector");
		
		Establecimiento establecimiento = EstablecimientoDAOImplementation.getInstance().read(req.getParameter("establecimientoCIF"));//a trav�s del indentificador del establecimiento (CIF) obtengo el establecimiento que corresponde
		req.getSession().setAttribute("establecimiento", establecimiento);
		
		List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento); //todas las inspecciones del establecimiento
		req.getSession().setAttribute("inspecciones", inspecciones);
		
		if (inspecciones.size() > 0) {
			//query para sacar la inspeccion m�s reciente
			Inspeccion ultima_inspeccion = InspeccionDAOImplementation.getInstance().ultimaInspeccion(establecimiento);
			req.getSession().setAttribute("ultima_inspeccion", ultima_inspeccion);
		} else {
			req.getSession().removeAttribute("ultima_inspeccion");
		}
		
		if (null != soy_cliente) { //compruebo que el usuario logeado es un cliente
			req.getSession().getAttribute("cliente");
		} else if ( null != soy_inspector) { //compruebo que el usuario logeado es un inspector
			req.getSession().getAttribute("inspector");
		}
		
		getServletContext().getRequestDispatcher("/EstablecimientoView.jsp").forward(req,resp);
			
	}
}