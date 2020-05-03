/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */
package es.upm.dit.isst.insp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.insp.dao.ClienteDAOImplementation;
import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.dao.InspectorDAOImplementation;
import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspector;
import es.upm.dit.isst.insp.model.Incidencia;

/**
 * Servlet encargada de devolver la imagen asociada a un inspector, un cliente o un establecimiento
 */

@WebServlet("/ServeImageServlet")
public class ServeImageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String id = (req.getParameter("id")); //hay hecha una "trampa" en EstablecimientoView, porque le paso el parametro cif pero lo llamo email, asi esta servlet vale para establecimientos y usuarios
		
		Establecimiento establecimiento = EstablecimientoDAOImplementation.getInstance().read(id);
		Inspector inspector = InspectorDAOImplementation.getInstance().read(id);
		Cliente cliente = ClienteDAOImplementation.getInstance().read(id);
		
		if (null != inspector) {
			resp.setContentLength(inspector.getImagen().length);
			resp.getOutputStream().write(inspector.getImagen());
		} else if (null != cliente) {
			resp.setContentLength(cliente.getImagen().length);
			resp.getOutputStream().write(cliente.getImagen());
		} else if (null != establecimiento) {
			if (establecimiento.getImagen().length == 0) {//comprobacion de si el establecimineto tiene imagen asociada
				req.getSession().setAttribute("tiene_imagen",false);
			} else {
				req.getSession().setAttribute("tiene_imagen",true);
				resp.setContentLength(establecimiento.getImagen().length);
				resp.getOutputStream().write(establecimiento.getImagen());	
			}
		}
	}
	
}
