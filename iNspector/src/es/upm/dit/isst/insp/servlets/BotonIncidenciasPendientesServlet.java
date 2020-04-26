package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Incidencia;

/**
 * Servlet implementation class BotonIncidenciasPendientesServlet
 */
@WebServlet("/BotonIncidenciasPendientesServlet")
public class BotonIncidenciasPendientesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		List<Establecimiento> establecimientos = (List<Establecimiento>) EstablecimientoDAOImplementation.getInstance().readAll();
		
		//List<Incidencia> incidencias_pendientes = (List<Incidencia>) IncidenciaDAOImplementation.getInstance().getIncidenciasPendientes();//todas las incidencias pendientes
		
		Map<Establecimiento,Integer> num_incidencias_pendientes = new HashMap<Establecimiento,Integer>();
		
		for (Establecimiento e :establecimientos) {
			//query que saca numero incidencias pendientes
			int num = IncidenciaDAOImplementation.getInstance().getIncidenciasPendientes(e);
			num_incidencias_pendientes.put(e, num); //anade Establecimiento y numero incidencias pendientes
		}
		
		Map<Establecimiento,Integer> num_incidencias_pendientes_ordenadas = sortByValue(num_incidencias_pendientes);
		
		req.getSession().getAttribute("num_incidencias_pendientes");
		req.getSession().getAttribute("num_establecimientos");
		
		req.getSession().setAttribute("num_incidencias_establecimiento", num_incidencias_pendientes_ordenadas);
		getServletContext().getRequestDispatcher("/IncidenciasPendientes.jsp").forward(req,res);
	}
	
	/*
	 * Metodo que ordena el Map segun el numero de incidencias pendientes (integer).
	 */
	private static Map<Establecimiento, Integer> sortByValue(final Map<Establecimiento, Integer> num_incidencias_pendientes) {
        return num_incidencias_pendientes.entrySet()
                .stream()
                .sorted((Map.Entry.<Establecimiento, Integer>comparingByValue().reversed()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }

}
