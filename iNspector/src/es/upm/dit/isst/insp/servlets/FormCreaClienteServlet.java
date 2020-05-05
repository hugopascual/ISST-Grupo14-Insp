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

import es.upm.dit.isst.insp.dao.ClienteDAOImplementation;
import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.validacion.ClaseValidadora;

/**
 * Servlet que se encarga de recoger los datos del formulario de registro de un cliente y respaldarlos en la base de datos
 * Es necesario manejar la excepcion que puede saltar al intentar registrar dos usuarios con la misma clave primaria (email)
 */

@WebServlet("/FormCreaClienteServlet")
public class FormCreaClienteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String email = req.getParameter("email").toLowerCase();
		
	
		if (!ClaseValidadora.compruebaEmail(email)) {
			
			req.getSession().setAttribute("email_no_valido", true);//variable que hara que salte la alerta de email incorrecto
			getServletContext().getRequestDispatcher("/FormCreaCliente.jsp").forward(req,resp);
			
		} else {
			
			String nombre = req.getParameter("nombre");
			String apellido_1 = req.getParameter("apellido_1");
			String apellido_2 = req.getParameter("apellido_2");
			String usuario = req.getParameter("usuario");
			String password = req.getParameter("password");
			
			Cliente cliente = new Cliente();
			
			cliente.setNombre(nombre);
			cliente.setApellido_1(apellido_1);
			cliente.setApellido_2(apellido_2);
			cliente.setEmail(email);
			cliente.setUsuario(usuario);
			cliente.setPassword(password);
			
			//al crear el cliente en la BBDD, si ya exite un cliente con el mismo id, salta una excepcion ya que se vulnera la unicidad de claves
			try {
				ClienteDAOImplementation.getInstance().create(cliente);
				
				req.getSession().setAttribute("nuevo_usuario", true);//variable que hara que salte alerta de usuario registrado
				req.getSession().setAttribute("error_cliente", false);
				req.getSession().setAttribute("email_no_valido", false);
				req.getSession().setAttribute("loginError", false);
				
				getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
			} catch (Exception e) {
				req.getSession().setAttribute("error_cliente", true);//variable que hara que salte alerta de usuario repetido
				req.getSession().setAttribute("nuevo_usuario", false);
				req.getSession().setAttribute("email_no_valido", false);
				
				
				getServletContext().getRequestDispatcher("/FormCreaCliente.jsp").forward(req,resp);
			}
		}
	}
}
