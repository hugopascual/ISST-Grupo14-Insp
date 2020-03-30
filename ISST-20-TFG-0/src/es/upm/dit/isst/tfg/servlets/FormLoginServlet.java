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

		if( ADMIN_EMAIL.equals(email) && ADMIN_PASSWORD.equals(password) ) {
			//si se logea como admin y pego los distintos atributos en la sesion
			req.getSession().setAttribute("admin", true);
			req.getSession().setAttribute("inspectores", inspectores); //lista de profesores
			req.getSession().setAttribute("establecimientos", establecimientos);//lista de tfgs
			req.getSession().setAttribute("clientes", clientes);
			getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);//redirijo a la vista Admin.jsp
		} else if ( null != cliente ) {
			//si corresponde a un tfg registrado
			//pego en la sesion el atributo del tfg y redirijo la servlet a la vista TFG.jsp
			req.getSession().setAttribute("cliente", cliente);//el objeto tfg es el que paso a la siguiente servlet
			getServletContext().getRequestDispatcher("/ClienteView.jsp").forward(req,resp);
		} else if ( null != inspector ) {
			//si se corresponde con un profesor registrado
			//pego el atributo de email del profesor en la sesion y redirijo a la vista Profesor.jsp
			req.getSession().setAttribute("inspector",inspector);//el objeto profesor es el que paso a la siguiente servlet
			getServletContext().getRequestDispatcher("/InspectorView.jsp").forward(req,resp);
		} else {
			getServletContext().getRequestDispatcher("/index.html").forward(req,resp);
		}
	}
}