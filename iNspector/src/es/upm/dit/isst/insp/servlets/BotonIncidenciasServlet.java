package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Incidencia;

/*
 * Servlet sacar toda la informacion necesaria para sacar todas las incidencias reportadas por un cliente
 */

@WebServlet("/BotonIncidenciasServlet")
public class BotonIncidenciasServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");
		
		List<Incidencia> incidencias = (List<Incidencia>) IncidenciaDAOImplementation.getInstance().readAllIncidencias_Cliente(cliente);	
		req.getSession().setAttribute("incidencias", incidencias);
		req.getSession().setAttribute("segun_cliente", true);//marcamos que queremos las incidencias segun el cliente que las ha reportado
		
		getServletContext().getRequestDispatcher("/IncidenciasView.jsp").forward(req,resp);
			
	}
}