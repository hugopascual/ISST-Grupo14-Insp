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
import es.upm.dit.isst.insp.model.Establecimiento;

/**
 * Servlet encargada de obtener todos los establecimientos ordenados segun su fecha de proxima inspeccion
 */

@WebServlet("/BotonProximasInspeccionesServlet")
public class BotonProximasInspeccionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		List<Establecimiento> establecimientos = (List<Establecimiento>) EstablecimientoDAOImplementation.getInstance().readAllOrderInspeccion();
		
		req.getSession().setAttribute("establecimientos", establecimientos);
		
		getServletContext().getRequestDispatcher("/ProximasInspeccionesView.jsp").forward(req,res);
	}

	
}
