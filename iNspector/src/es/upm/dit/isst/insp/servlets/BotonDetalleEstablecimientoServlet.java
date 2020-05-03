/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

/**
 * Servlet que obtiene toda la información relativa al establecimiento que se ha seleccionado
 */

@WebServlet("/BotonDetalleEstablecimientoServlet")
public class BotonDetalleEstablecimientoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object soy_cliente = req.getSession().getAttribute("soy_cliente");
		Object soy_inspector = req.getSession().getAttribute("soy_inspector");
		
		//obtengo el establecimiento seleccionado usando el identificador obtenido como parametro
		Establecimiento establecimiento = EstablecimientoDAOImplementation.getInstance().read(req.getParameter("establecimientoCIF"));
		req.getSession().setAttribute("establecimiento", establecimiento);
		
		//lista de todas las inspecciones del establecimiento
		List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento);
		req.getSession().setAttribute("inspecciones", inspecciones);
		
		//si se han registrado inspecciones en el establecimiento, obtengo la inspeccion mas reciente
		if (inspecciones.size() > 0) {
			
			Inspeccion ultima_inspeccion = InspeccionDAOImplementation.getInstance().ultimaInspeccion(establecimiento);
			String colorNota = colorNota(ultima_inspeccion);
			
			req.getSession().setAttribute("colorNota", colorNota);
			req.getSession().setAttribute("ultima_inspeccion", ultima_inspeccion);
			
		} else {
			req.getSession().removeAttribute("ultima_inspeccion");
		}
		
		//compruebo si el establecimiento tiene asociada una imagen
		if (establecimiento.getImagen().length == 0) {
			req.getSession().setAttribute("tiene_imagen",false);
		} else {
			req.getSession().setAttribute("tiene_imagen",true);
		}
		
		//compruebo si el usuario logeado es un cliente o un inspector
		if (null != soy_cliente) {
			req.getSession().getAttribute("cliente");
		} else if ( null != soy_inspector) {
			req.getSession().getAttribute("inspector");
		}
		
		String fecha_hoy = fechaHoy();
		
		req.getSession().setAttribute("fecha_hoy", fecha_hoy);
		getServletContext().getRequestDispatcher("/EstablecimientoView.jsp").forward(req,resp);
			
	}
	
	/**
	 * Metodo auxiliar que comprueba la nota de la inspeccion y devuelve un color asociado a esta
	 * @param inspeccion de la que se quiere mostrar la nota
	 * @return string que será utilizado para definir el estilo del texto que muetsre la nota
	 */
	private String colorNota(Inspeccion inspeccion) {
		String color= null;
		String nota = inspeccion.getNota();
		if (nota.equals("Favorable")) {
			color = "#00A135"; //verde
		} else if (nota.equals("Favorable condicionado")) {
			color = "#FAB521"; //amarillo
		} else if (nota.equals("Desfavorable")) {
			color = "#FF0000"; //rojo
		}
		return "color:"+color+";";
	}
	
	/**
	 * Metodo auxiliar que devuelve un string con la fecha actual con formato yyyy-MM-dd
	 * @return string con la fecha de hoy que será utilizado para definir limites en la seleccion de fechas
	 */
	private String fechaHoy() {
		Date fecha_hoy =new Date();
		String hoy = new SimpleDateFormat("yyyy-MM-dd").format(fecha_hoy);
		return hoy;
	}
}