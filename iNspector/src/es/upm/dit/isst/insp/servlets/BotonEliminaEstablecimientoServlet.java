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

/*
 * Servlet para eliminar un establecimiento
 */

@WebServlet("/BotonEliminaEstablecimientoServlet")
public class BotonEliminaEstablecimientoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		Establecimiento establecimiento = EstablecimientoDAOImplementation.getInstance().read(req.getParameter("establecimientoCIF"));//a través del indentificador del establecimiento (CIF) obtengo el establecimiento correspondiente
		
		//antes de eliminar un establecimiento hay que eliminar todas las inspecciones e incidencias asociadas a este
		List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento);
		for(Inspeccion inspeccion : inspecciones) {
			InspeccionDAOImplementation.getInstance().delete(inspeccion);
		}
		
		List<Incidencia> incidencias = IncidenciaDAOImplementation.getInstance().readAllIncidencias_Establ(establecimiento);
		for(Incidencia incidencia : incidencias) {
			IncidenciaDAOImplementation.getInstance().delete(incidencia);
		}
	
		EstablecimientoDAOImplementation.getInstance().delete(establecimiento);//elimina el establecimiento de la base de datos
		
		List<Establecimiento> establecimientos = (List<Establecimiento>) EstablecimientoDAOImplementation.getInstance().readAll();//lee la lista de establecimientos de la base de datos
		
		req.getSession().setAttribute("establecimientos", establecimientos);//actualiza el atributo de la sesion con los establecimientos

		getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
	}
}