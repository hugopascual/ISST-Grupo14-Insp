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

import es.upm.dit.isst.insp.dao.ClienteDAOImplementation;
import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.dao.InspectorDAOImplementation;
import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspector;

/**
 * Servlet encargada de comprobar las credenciales de un usuario y de que acceda con los privilegios que le corresponden 
 */
@WebServlet("/FormLoginServlet")
public class FormLoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private final String ADMIN_EMAIL = "root";
	private final String ADMIN_PASSWORD = "root";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email").toLowerCase();
		String password = req.getParameter("password");
		
		List<Inspector> inspectores = (List<Inspector>) InspectorDAOImplementation.getInstance().readAll();
		List<Establecimiento> establecimientos = (List<Establecimiento>) EstablecimientoDAOImplementation.getInstance().readAll();
		List<Cliente> clientes = (List<Cliente>) ClienteDAOImplementation.getInstance().readAll();
		
		Inspector inspector = InspectorDAOImplementation.getInstance().login(email, password);
		Cliente cliente = ClienteDAOImplementation.getInstance().login(email, password);
		
		req.getSession().setAttribute("nuevo_usuario", false);//desactiva la variable que hace que salte la alerta de "Usuario registrado"
		

		//acceso admin
		if( ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) ) { 
			
			req.getSession().setAttribute("admin", true);
			req.getSession().setAttribute("inspectores", inspectores);
			req.getSession().setAttribute("establecimientos", establecimientos);
			req.getSession().setAttribute("clientes", clientes);
			
			getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
		//acceso cliente
		} else if ( null != cliente ) {
		
			req.getSession().setAttribute("soy_cliente", true);
			req.getSession().setAttribute("cliente", cliente);
			req.getSession().setAttribute("establecimientos", establecimientos);
			
			boolean imagen = false;
			if (cliente.getImagen() != null) {
				imagen = true;
			}
			
			req.getSession().setAttribute("imagen",imagen);
			
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		//acceso inspector
		} else if ( null != inspector ) {
			
			req.getSession().setAttribute("soy_inspector", true);
			req.getSession().setAttribute("inspector",inspector);
			req.getSession().setAttribute("establecimientos", establecimientos);
			
			boolean imagen = false;
			if (inspector.getImagen() != null) {
				imagen = true;
			}
			
			int num_incidencias_pendientes = IncidenciaDAOImplementation.getInstance().getIncidenciasPendientes();	
			int num_establecimientos = IncidenciaDAOImplementation.getInstance().getNumEstablecimientosConIncidenciasPendientes();
			
			req.getSession().setAttribute("num_incidencias_pendientes",num_incidencias_pendientes );
			req.getSession().setAttribute("num_establecimientos",num_establecimientos );
			req.getSession().setAttribute("imagen",imagen);
			
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		//las credenciales no se corresponden con ningun tipo de usuario
		} else {
			req.getSession().setAttribute("loginError", true);
			
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
		}
	}
}