package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.InspectorDAOImplementation;
import es.upm.dit.isst.tfg.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.tfg.model.Professor;
import es.upm.dit.isst.tfg.model.TFG;

/*
 * Con el metodo doGet se leen los parametros, se crea un objeto TFG y lo respalda en la base de datos. Tambien actualiza
 * la sesion (no es estrictamente necesario ya que el usuario aun no esta autenticado) y pasa el control de nuevo a index.html.
 * Ojo! Es importante comprobar que existe el profesor en la base de datos, ya que si no existe, no se podria crear el TFG.
 */

@WebServlet("/Form1TFGServlet")
public class Form1TFGServlet extends HttpServlet  {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String title = req.getParameter("titulo");
		String advisorEmail = req.getParameter("profesor");
		
		/*Inspector advisor = InspectorDAOImplementation.getInstance().read(advisorEmail); //busca al profesor segun su email
		if( null != advisor ) { //comprobacion de que el profesor esta en la base de datos
			TFG tfg = new TFG();
			tfg.setEmail(email);
			tfg.setPassword(password);
			tfg.setName(name);
			tfg.setTitle(title);
			tfg.setAdvisor(advisor);
			EstablecimientoDAOImplementation.getInstance().create(tfg);
			req.getSession().setAttribute("tfg", tfg);
		}*/
		getServletContext().getRequestDispatcher("/index.html")
		.forward(req, resp);
	}
}
