/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

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

/**
 * Servlet que se encarga de recoger los datos del formulario de registro de un inspector y respaldarlos en la base de datos
 * Es necesario manejar la excepcion que puede saltar al intentar registrar dos inspectores con la misma clave primaria (email)
 * Ademas, si es el primer inspector que se esta registrando, se crea un inspector falso que sera utilizado para asignarle las 
 * inspecciones de inspectores eliminados
 */

@WebServlet("/FormCreaInspectorServlet")
public class FormCreaInspectorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Inspector> lista_inspectores = (List<Inspector>) req.getSession().getAttribute("inspectores");
		
		//si no hay inspectores, se crea el "inspector falso"
		if (lista_inspectores.size() == 0) {
		
			String nombre = "Inspector";
			String apellido_1 = "Eliminado";
			String apellido_2 = null;
			String email = "inspector_falso";
			String usuario = null;
			String password = null; //no necesita password porque no queremos que pueda iniciar sesion
			
			Inspector inspector_falso = new Inspector();
			
			inspector_falso.setNombre(nombre);
			inspector_falso.setApellido_1(apellido_1);
			inspector_falso.setApellido_2(apellido_2);
			inspector_falso.setEmail(email);
			inspector_falso.setUsuario(usuario);
			inspector_falso.setPassword(password);
			
			InspectorDAOImplementation.getInstance().create(inspector_falso);		
		}
		
		//registro del inspector real
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
		
		//al registrar al inspector en la BBDD, si ya exite uno con el mismo id, salta una excepcion ya que se vulnera la unicidad de claves
		try {
			InspectorDAOImplementation.getInstance().create(inspector);
			
			List<Inspector> inspectores = new ArrayList<Inspector>();
			inspectores.addAll((List<Inspector>) 
					req.getSession().getAttribute("inspectores"));
			inspectores.add (inspector);//actualizacion de la lista de insepctores
			
			req.getSession().setAttribute("inspectores", inspectores);
			req.getSession().setAttribute("error_insp", false);
			req.getSession().setAttribute("error_establ", false);
			
			getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
		
		} catch (Exception e) {
			req.getSession().setAttribute("error_insp", true);//variable que hara que salte la alerta de inspector duplicado
			req.getSession().setAttribute("error_establ", false);
			
			getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
		}
		
		
	}
}
