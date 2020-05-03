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

import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Incidencia;

/**
 * Servlet que se encarga de obtener las incidencias que ha reportado un cliente
 */

@WebServlet("/BotonIncidenciasServlet")
public class BotonIncidenciasServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");
		
		List<Incidencia> incidencias = (List<Incidencia>) IncidenciaDAOImplementation.getInstance().readAllIncidencias_Cliente(cliente);	
		
		req.getSession().setAttribute("incidencias", incidencias);
		req.getSession().setAttribute("segun_cliente", true);//marco que quiero las incidencias segun el cliente
		
		getServletContext().getRequestDispatcher("/IncidenciasView.jsp").forward(req,resp);
			
	}
}