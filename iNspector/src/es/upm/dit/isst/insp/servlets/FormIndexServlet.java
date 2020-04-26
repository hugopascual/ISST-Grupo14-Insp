package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;

/*
 * Servlet para volver a la vista del usuario desde cualquier página. Debe utilizarse acompanada de FormPerfilUsuario.jsp
 */

@WebServlet("/FormIndexServlet")
public class FormIndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object soy_cliente = req.getSession().getAttribute("soy_cliente");
		Object soy_inspector = req.getSession().getAttribute("soy_inspector");
		
		if (null != soy_cliente) { //compruebo que el usuario logeado es un cliente.
			
			req.getSession().getAttribute("cliente");//me quedo con los datos del cliente.
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		} else if ( null != soy_inspector) {//compruebo que el usuario logeado es un inspector.
			
			int num_incidencias_pendientes = IncidenciaDAOImplementation.getInstance().getIncidenciasPendientes();	
			int num_establecimientos = IncidenciaDAOImplementation.getInstance().getNumEstablecimientosConIncidenciasPendientes();
			
			req.getSession().setAttribute("num_incidencias_pendientes",num_incidencias_pendientes );
			req.getSession().setAttribute("num_establecimientos",num_establecimientos );
			req.getSession().getAttribute("inspector");//me quedo con los datos del inspector.
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
		}
		
	}
}
