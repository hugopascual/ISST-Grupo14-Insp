/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet para ir a la vista 'Mi perfil' del usuario desde cualquier p�gina.
 */

@WebServlet("/FormPerfilUsuarioServlet")
public class FormPerfilUsuarioServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object soy_cliente = req.getSession().getAttribute("soy_cliente");
		Object soy_inspector = req.getSession().getAttribute("soy_inspector");
		
		if (null != soy_cliente) { //compruebo que el usuario logeado es un cliente
			
			req.getSession().getAttribute("cliente");//me quedo con los datos del cliente
			getServletContext().getRequestDispatcher("/ClienteView.jsp").forward(req,resp);
			
		} else if ( null != soy_inspector) { //compruebo que el usuario logeado es un inspector
			
			req.getSession().getAttribute("inspector");//me quedo con los datos del inspector
			getServletContext().getRequestDispatcher("/InspectorView.jsp").forward(req,resp);
		}
		
	}
}