package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.InspectorDAOImplementation;
import es.upm.dit.isst.tfg.model.Inspector;

/*
 * Servlet para la accion de eliminar un inspector
 */

@WebServlet("/BotonEliminaInspectorServlet")
public class BotonEliminaInspectorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
		Inspector inspector = InspectorDAOImplementation.getInstance().read(req.getParameter("email_inspector"));//a través del indentificador del inspector (email) obtengo el inspector correspondiente
		InspectorDAOImplementation.getInstance().delete(inspector);//elimina el inspector de la base de datos
		
		List<Inspector> inspectores = (List<Inspector>) InspectorDAOImplementation.getInstance().readAll();//lee la lista de inspectores de la base de datos
		req.getSession().setAttribute("inspectores", inspectores);//actualiza el atributo de la sesion con los inspectores

		getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
	}
}