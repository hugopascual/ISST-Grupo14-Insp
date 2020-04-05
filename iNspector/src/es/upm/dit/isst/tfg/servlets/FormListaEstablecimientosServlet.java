package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.tfg.model.Establecimiento;

/*
 * Servlet para volver a la vista del cliente desde cualquier página siempre que acceda un cliente
 */

@WebServlet("/FormListaEstablecimientosServlet")
public class FormListaEstablecimientosServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object soy_cliente = req.getSession().getAttribute("soy_cliente");
		Object soy_inspector = req.getSession().getAttribute("soy_inspector");
		List<Establecimiento> establecimientos = (List<Establecimiento>) EstablecimientoDAOImplementation.getInstance().readAll();
		
		req.getSession().setAttribute("establecimientos", establecimientos);
		
		if (null != soy_cliente) { //compruebo que el usuario logeado es un cliente
			req.getSession().getAttribute("cliente");
		} else if ( null != soy_inspector) { //compruebo que el usuario logeado es un inspector
			req.getSession().getAttribute("inspector");
		} else {
			req.getSession().setAttribute("usuario_no_registrado",true);
		}
		
		getServletContext().getRequestDispatcher("/ListaEstablecimientosView.jsp").forward(req,resp);
			
	}
}