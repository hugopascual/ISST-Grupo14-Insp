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

import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.model.Establecimiento;

/**
 * Servlet que recopila la informacion necesaria para mostrar la lista de establecimientos registrados en la aplicacion
 */

@WebServlet("/FormListaEstablecimientosServlet")
public class FormListaEstablecimientosServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Object soy_cliente = req.getSession().getAttribute("soy_cliente");
		Object soy_inspector = req.getSession().getAttribute("soy_inspector");
		List<Establecimiento> establecimientos = (List<Establecimiento>) EstablecimientoDAOImplementation.getInstance().readAll();
		
		req.getSession().setAttribute("establecimientos", establecimientos);
		
		//segun el tipo de usuario, se pasan los datos de uno o de otro
		if (null != soy_cliente) {
			req.getSession().getAttribute("cliente");
		} else if ( null != soy_inspector) { 
			req.getSession().getAttribute("inspector");
		} else {
			req.getSession().setAttribute("usuario_no_registrado",true);
		}
		
		getServletContext().getRequestDispatcher("/ListaEstablecimientosView.jsp").forward(req,resp);
			
	}
}