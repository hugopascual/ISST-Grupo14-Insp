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

import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;

/**
 * Servlet para volver a la vista principal desde cualquier página del usuario 
 */

@WebServlet("/FormIndexServlet")
public class FormIndexServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//identifico el tipo de usuario que ha accedido
		Object soy_cliente = req.getSession().getAttribute("soy_cliente");
		Object soy_inspector = req.getSession().getAttribute("soy_inspector");
		Object admin = req.getSession().getAttribute("admin");
		
		if (null != soy_cliente) {//un cliente
			
			req.getSession().getAttribute("cliente");//me quedo con los datos del cliente.
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		} else if ( null != soy_inspector) {//un inspector
			
			//obtengo el numero de incidencias pendientes y establecimientos con incidencias pendientes 
			int num_incidencias_pendientes = IncidenciaDAOImplementation.getInstance().getIncidenciasPendientes();	
			int num_establecimientos = IncidenciaDAOImplementation.getInstance().getNumEstablecimientosConIncidenciasPendientes();
			
			req.getSession().setAttribute("num_incidencias_pendientes",num_incidencias_pendientes );
			req.getSession().setAttribute("num_establecimientos",num_establecimientos );
			req.getSession().getAttribute("inspector");//me quedo con los datos del inspector.
			
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		} else if ( null != admin ) {//un administrador
			
			getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
		} else {//un usuario no registrado
			
			req.getSession().setAttribute("loginError", false);
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
		}
		
	}
}
