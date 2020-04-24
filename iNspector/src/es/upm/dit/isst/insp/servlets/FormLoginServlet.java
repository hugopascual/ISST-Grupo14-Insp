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
import es.upm.dit.isst.insp.dao.InspectorDAOImplementation;
import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspector;

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
		
		List<Inspector> inspectores = (List<Inspector>) InspectorDAOImplementation.getInstance().readAll();
		List<Establecimiento> establecimientos = (List<Establecimiento>) EstablecimientoDAOImplementation.getInstance().readAll();
		List<Cliente> clientes = (List<Cliente>) ClienteDAOImplementation.getInstance().readAll();
		
		//Se pasara a la servlet el objeto que haya introducido sus credenciales correspondientes
		Inspector inspector = InspectorDAOImplementation.getInstance().login(email, password);
		Cliente cliente = ClienteDAOImplementation.getInstance().login(email, password);
		
		req.getSession().setAttribute("nuevo_usuario", false);//Desactiva la variable que hace que salte el mensaje de "Usuario registrado"
		

		if( ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) ) {
			
			req.getSession().setAttribute("admin", true);
			req.getSession().setAttribute("inspectores", inspectores);
			req.getSession().setAttribute("establecimientos", establecimientos);
			req.getSession().setAttribute("clientes", clientes);
			getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
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
			
		} else if ( null != inspector ) {
			
			req.getSession().setAttribute("soy_inspector", true);
			req.getSession().setAttribute("inspector",inspector);
			req.getSession().setAttribute("establecimientos", establecimientos);
			boolean imagen = false;
			if (inspector.getImagen() != null) {
				imagen = true;
			}
			req.getSession().setAttribute("imagen",imagen);
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		} else {
			
			req.getSession().setAttribute("loginError", true);
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		}
	}
}