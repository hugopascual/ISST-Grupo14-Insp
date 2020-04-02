package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.InspectorDAOImplementation;
import es.upm.dit.isst.tfg.dao.ClienteDAOImplementation;
import es.upm.dit.isst.tfg.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.tfg.model.Inspector;
import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Cliente;

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
		
		//Objetos que despues se pasaran a la servlet
		Establecimiento establecimiento = EstablecimientoDAOImplementation.getInstance().login(email, password);
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
			//getServletContext().getRequestDispatcher("/ClienteView.jsp").forward(req,resp);
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		} else if ( null != inspector ) {
			
			req.getSession().setAttribute("soy_inspector", true);
			req.getSession().setAttribute("inspector",inspector);
			req.getSession().setAttribute("establecimientos", establecimientos);
			//getServletContext().getRequestDispatcher("/InspectorView.jsp").forward(req,resp);
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		} else {
			
			getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			
		}
	}
}