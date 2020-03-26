package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.ProfessorDAOImplementation;
import es.upm.dit.isst.tfg.model.Professor;

/*
 * Con el metodo doGet lee los parametros, crea un objeto Profesor respaldado en la base de datos
 * y actualiza la lista de profesores en la sesion de manera que Admin.jsp pueda mostrar en cada 
 * momento la lista de profesores dados de alta en la aplicacion
 */

@WebServlet("/FormCreaProfesorServlet")
public class FormCreaProfesorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		Professor professor = new Professor();
		
		professor.setEmail(email);
		professor.setPassword(password);
		professor.setName(name);
		
		ProfessorDAOImplementation.getInstance().create(professor);
		List<Professor> lp = new ArrayList<Professor>();
		lp.addAll((List<Professor>)
				req.getSession().getAttribute("profesores"));
		lp.add (professor);
		req.getSession().setAttribute("profesores", lp);
		getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
	}
}
