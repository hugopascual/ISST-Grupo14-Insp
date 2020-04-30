package es.upm.dit.isst.insp.servlets;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.insp.dao.InspectorDAOImplementation;
import es.upm.dit.isst.insp.model.Inspector;

/*
 * Con el metodo doGet lee los parametros, crea un objeto Profesor respaldado en la base de datos
 * y actualiza la lista de profesores en la sesion de manera que Admin.jsp pueda mostrar en cada 
 * momento la lista de profesores dados de alta en la aplicacion
 */

@WebServlet("/FormCreaInspectorServlet")
public class FormCreaInspectorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Inspector> lista_inspectores = (List<Inspector>) req.getSession().getAttribute("inspectores");
		
		if (lista_inspectores.size() == 0) {
			//si no hay inspectores, debemos crear el "inspector falso" al que se le asignaran las inspecciones de inspectores eliminados
			String nombre = "Inspector";
			String apellido_1 = "Eliminado";
			String apellido_2 = null;
			String email = "inspector_falso";//necesita email porque es el id
			String usuario = null;
			String password = null; // no necesita password porque no queremos que pueda iniciar sesion
			
			Inspector inspector_falso = new Inspector();
			
			inspector_falso.setNombre(nombre);
			inspector_falso.setApellido_1(apellido_1);
			inspector_falso.setApellido_2(apellido_2);
			inspector_falso.setEmail(email);
			inspector_falso.setUsuario(usuario);
			inspector_falso.setPassword(password);
			
			InspectorDAOImplementation.getInstance().create(inspector_falso);		
		}
		
		String nombre = req.getParameter("nombre");
		String apellido_1 = req.getParameter("apellido_1");
		String apellido_2 = req.getParameter("apellido_2");
		String email = req.getParameter("email");
		String usuario = req.getParameter("usuario");
		String password = req.getParameter("password");
		
		Inspector inspector = new Inspector();
		
		inspector.setNombre(nombre);
		inspector.setApellido_1(apellido_1);
		inspector.setApellido_2(apellido_2);
		inspector.setEmail(email);
		inspector.setUsuario(usuario);
		inspector.setPassword(password);
		
		try {InspectorDAOImplementation.getInstance().create(inspector);
		List<Inspector> inspectores = new ArrayList<Inspector>();
		inspectores.addAll((List<Inspector>) 
				req.getSession().getAttribute("inspectores"));
		inspectores.add (inspector);
		req.getSession().setAttribute("inspectores", inspectores);
		getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
		
		} catch (Exception e) {
			req.getSession().setAttribute("error_insp", true);
			req.getSession().setAttribute("error_establ", false);
			getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
		}
		
		
	}
}
