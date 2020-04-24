package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FormLogoutServlet")
public class FormLogoutServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("admin");
		req.getSession().removeAttribute("tfgs");
		req.getSession().removeAttribute("tfg");
		req.getSession().removeAttribute("profesor");
		req.getSession().removeAttribute("profesores");
		req.getSession().invalidate();//mata la sesion borrando la tabla y sus atributos
		getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
	}
}