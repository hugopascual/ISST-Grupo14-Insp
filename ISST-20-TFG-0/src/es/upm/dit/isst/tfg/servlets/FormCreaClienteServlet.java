package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.ClienteDAOImplementation;
import es.upm.dit.isst.tfg.model.Cliente;

@WebServlet("/FormCreaClienteServlet")
public class FormCreaClienteServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String nombre = req.getParameter("nombre");
		String apellido_1 = req.getParameter("apellido_1");
		String apellido_2 = req.getParameter("apellido_2");
		String email = req.getParameter("email");
		String usuario = req.getParameter("usuario");
		String password = req.getParameter("password");
		
		Cliente cliente = new Cliente();
		
		cliente.setNombre(nombre);
		cliente.setApellido_1(apellido_1);
		cliente.setApellido_2(apellido_2);
		cliente.setEmail(email);
		cliente.setUsuario(usuario);
		cliente.setPassword(password);
		
		ClienteDAOImplementation.getInstance().create(cliente);
		List<Cliente> clientes = new ArrayList<Cliente>();
		//clientes.addAll((List<Cliente>)
				//req.getSession().getAttribute("clientes"));
		//clientes.add(cliente);
		req.getSession().setAttribute("clientes", clientes);
		getServletContext().getRequestDispatcher("/index.html").forward(req,resp);
	}
}
