package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.ProfessorDAOImplementation;
import es.upm.dit.isst.tfg.dao.TFGDAOImplementation;
import es.upm.dit.isst.tfg.model.Professor;
import es.upm.dit.isst.tfg.model.TFG;

@WebServlet("/FormLoginServlet")
public class FormLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final String ADMIN_EMAIL = "root";
	private final String ADMIN_PASSWORD = "root";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//Del formulario coge los parametros email y password
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		List<Professor> profesores = (List<Professor>) ProfessorDAOImplementation.getInstance().readAll();
		List<TFG> tfgs = (List<TFG>) TFGDAOImplementation.getInstance().readAll();
		
		//Objetos que despues se pasaran a la servlet
		TFG tfg = TFGDAOImplementation.getInstance().login(email, password);
		Professor professor = ProfessorDAOImplementation.getInstance().login(email, password);

		if( ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) ) {
			//si se logea como admin y pego los distintos atributos en la sesion
			req.getSession().setAttribute("admin", true);
			req.getSession().setAttribute("profesores", profesores); //lista de profesores
			req.getSession().setAttribute("tfgs", tfgs);//lista de tfgs
			getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);//redirijo a la vista Admin.jsp
		} else if ( null != tfg ) {
			//si corresponde a un tfg registrado
			//pego en la sesion el atributo del tfg y redirijo la servlet a la vista TFG.jsp
			req.getSession().setAttribute("tfg", tfg);//el objeto tfg es el que paso a la siguiente servlet
			getServletContext().getRequestDispatcher("/TFGView.jsp").forward(req,resp);
		} else if ( null != professor ) {
			//si se corresponde con un profesor registrado
			//pego el atributo de email del profesor en la sesion y redirijo a la vista Profesor.jsp
			req.getSession().setAttribute("profesor",ProfessorDAOImplementation.getInstance().read(professor.getEmail()));//el objeto profesor es el que paso a la siguiente servlet
			getServletContext().getRequestDispatcher("/ProfesorView.jsp").forward(req,resp);
		} else {
			getServletContext().getRequestDispatcher("/index.html").forward(req,resp);
		}
	}
}