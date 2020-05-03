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

import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.dao.InspectorDAOImplementation;
import es.upm.dit.isst.insp.model.Inspeccion;
import es.upm.dit.isst.insp.model.Inspector;

/**
 * Servlet que se encarga de las tareas necesarias para eliminar un inspector
 * 
 * Antes de eliminar un inspector es necesario asociar todas sus inspecciones a otro inspector, para que no 
 * se pierdan. Esto es necesario porque la clave primaria del inspector es clave foranea de la inspeccion.
 * 
 * Para realizar esto, se asignan las inspecciones del inspector a eliminar a un "inspector falso".
 */

@WebServlet("/BotonEliminaInspectorServlet")
public class BotonEliminaInspectorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//obtengo el inspector falso
		Inspector inspector_falso = InspectorDAOImplementation.getInstance().read("inspector_falso");
		
		//a través del indentificador (email) obtengo el inspector correspondiente
		Inspector inspector = InspectorDAOImplementation.getInstance().read(req.getParameter("email_inspector"));
		
		//lista de todas las inspecciones realizadas por el inspector que queremos eliminar
		List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Insp(inspector); 
		
		//asigno todas las inspecciones del inspector al inspector falso
		for (Inspeccion inspeccioni : inspecciones) {
			inspeccioni.setInspector_realiza_inspeccion(inspector_falso);
			InspeccionDAOImplementation.getInstance().update(inspeccioni);
		}
		
		//elimina el inspector de la base de datos
		InspectorDAOImplementation.getInstance().delete(inspector);
		
		//actualizo la lista de inspectores
		List<Inspector> inspectores = (List<Inspector>) InspectorDAOImplementation.getInstance().readAll();
		
		req.getSession().setAttribute("inspectores", inspectores);
		getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
	}
}