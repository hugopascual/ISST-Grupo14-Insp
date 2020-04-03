package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.tfg.model.Establecimiento;

/*
 * Servlet para eliminar un establecimiento
 */

@WebServlet("/BotonEliminaEstablecimientoServlet")
public class BotonEliminaEstablecimientoServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		Establecimiento establecimiento = EstablecimientoDAOImplementation.getInstance().read(req.getParameter("establecimientoCIF"));//a trav�s del indentificador del establecimiento (CIF) obtengo el establecimiento correspondiente
		EstablecimientoDAOImplementation.getInstance().delete(establecimiento);//elimina el establecimiento de la base de datos
		
		List<Establecimiento> establecimientos = (List<Establecimiento>) EstablecimientoDAOImplementation.getInstance().readAll();//lee la lista de establecimientos de la base de datos
		req.getSession().setAttribute("establecimientos", establecimientos);//actualiza el atributo de la sesion con los establecimientos

		getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
	}
}