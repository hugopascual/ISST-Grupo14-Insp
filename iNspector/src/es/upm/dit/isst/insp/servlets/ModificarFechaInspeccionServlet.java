/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */
package es.upm.dit.isst.insp.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.taglibs.standard.extra.spath.ParseException;

import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.dao.InspectorDAOImplementation;
import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspeccion;
import es.upm.dit.isst.insp.model.Inspector;
import es.upm.dit.isst.insp.model.Incidencia;

/**
 * Servlet que se encarga de actualizar la fecha de proxima inspeccion de un establecimiento cuando un inspector
 * la modifica de forma manual
 */

@WebServlet("/ModificarFechaInspeccionServlet")
public class ModificarFechaInspeccionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Establecimiento establecimiento = EstablecimientoDAOImplementation.getInstance().read(req.getParameter("establecimientoCIF"));
		
		//string con la fecha de hoy que sera utilizado para limitar la eleccion de fechas
		String fecha_hoy = fechaHoy();
		
		Date nueva_fecha_insp = null;
		
		try {
			nueva_fecha_insp = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("nueva_fecha_insp")); //getParameter siempre devuelve string
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
	
		establecimiento.setProxima_inspeccion(nueva_fecha_insp);
		
		EstablecimientoDAOImplementation.getInstance().update(establecimiento);
		
		Establecimiento establec = EstablecimientoDAOImplementation.getInstance().read(establecimiento.getCif());
		
		req.getSession().setAttribute("establecimiento", establec);
		req.getSession().setAttribute("fecha_hoy", fecha_hoy);
		req.getSession().getAttribute("inspecciones");
		req.getSession().getAttribute("ultima_inspeccion");
		req.getSession().getAttribute("tiene_imagen");
		req.getSession().getAttribute("inspector");
    	getServletContext().getRequestDispatcher("/EstablecimientoView.jsp").forward(req,resp);
	}
	
	/**
	 * Metodo auxiliar que devuelve un string con la fecha de hoy
	 * @return string con la fecha de hoy
	 */
	private String fechaHoy() {
		Date fecha_hoy =new Date();
		String hoy = new SimpleDateFormat("yyyy-MM-dd").format(fecha_hoy);
		return hoy;
	}
	
	
}