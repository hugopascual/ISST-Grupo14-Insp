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
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspeccion;

/*
 * Servlet para sacar toda la información relativa al establecimiento seleccionado
 */

@WebServlet("/BotonDetalleEstablecimientoServlet")
public class BotonDetalleEstablecimientoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object soy_cliente = req.getSession().getAttribute("soy_cliente");
		Object soy_inspector = req.getSession().getAttribute("soy_inspector");
		
		Establecimiento establecimiento = EstablecimientoDAOImplementation.getInstance().read(req.getParameter("establecimientoCIF"));//a través del indentificador del establecimiento (CIF) obtengo el establecimiento que corresponde
		req.getSession().setAttribute("establecimiento", establecimiento);
		
		List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento); //todas las inspecciones del establecimiento
		req.getSession().setAttribute("inspecciones", inspecciones);
		
		if (inspecciones.size() > 0) {
			Inspeccion ultima_inspeccion = InspeccionDAOImplementation.getInstance().ultimaInspeccion(establecimiento);//query para sacar la inspeccion más reciente
			
			String colorNota = colorNota(ultima_inspeccion);
			
			req.getSession().setAttribute("colorNota", colorNota);
			req.getSession().setAttribute("ultima_inspeccion", ultima_inspeccion);
			
		} else {
			req.getSession().removeAttribute("ultima_inspeccion");
		}
		
		if (establecimiento.getImagen().length == 0) {
			req.getSession().setAttribute("tiene_imagen",false);
		} else {
			req.getSession().setAttribute("tiene_imagen",true);
		}
		
		if (null != soy_cliente) { //compruebo que el usuario logeado es un cliente
			req.getSession().getAttribute("cliente");
		} else if ( null != soy_inspector) { //compruebo que el usuario logeado es un inspector
			req.getSession().getAttribute("inspector");
		}
		
		getServletContext().getRequestDispatcher("/EstablecimientoView.jsp").forward(req,resp);
			
	}
	
	/*
	 * En funcion de la nota de la inspeccion, el texto aparece en diferente color 
	 */
	private String colorNota(Inspeccion inspeccion) {
		String color= null;
		String nota = inspeccion.getNota();
		if (nota.equals("Favorable")) {
			color = "#00A135"; //verde
		} else if (nota.equals("Favorable condicionado")) {
			color = "#FFFF00"; //amarillo
		} else if (nota.equals("Desfavorable")) {
			color = "#FF0000"; //rojo
		}
		return "color:"+color+";";
	}
}