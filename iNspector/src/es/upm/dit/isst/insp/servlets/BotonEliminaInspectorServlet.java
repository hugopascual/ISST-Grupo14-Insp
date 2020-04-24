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

/*
 * Servlet para la accion de eliminar un inspector
 */

@WebServlet("/BotonEliminaInspectorServlet")
public class BotonEliminaInspectorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*Antes de eliminar un inspector necesitamos un "inspector falso" al que se le asigne las inspecciones que ha realizado el inspector que queremos eliminar
		ya que si no, no se puede eliminar al inspector porque se produce una Violación de una restricción de Integridad Referencial al borrar una 
		FOREIGN KEY de la inspección.
		La id del inspector falso es inspector_falso */
		
		Inspector inspector_falso = InspectorDAOImplementation.getInstance().read("inspector_falso");//obtengo el inspector falso
			
		Inspector inspector = InspectorDAOImplementation.getInstance().read(req.getParameter("email_inspector"));//a través del indentificador del inspector (email) obtengo el inspector correspondiente
		
		List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Insp(inspector); //lista de todas las inspecciones realizadas por el inspector que queremos eliminar
		
		//bucle que cambia el inspector al inspector_falso para todas las inspecciones del inspector
		for (Inspeccion inspeccioni : inspecciones) {
			inspeccioni.setInspector_realiza_inspeccion(inspector_falso);
			InspeccionDAOImplementation.getInstance().update(inspeccioni);
		}
		
		InspectorDAOImplementation.getInstance().delete(inspector);//elimina el inspector de la base de datos
		
		List<Inspector> inspectores = (List<Inspector>) InspectorDAOImplementation.getInstance().readAll();//lee la lista de inspectores de la base de datos
		req.getSession().setAttribute("inspectores", inspectores);//actualiza el atributo de la sesion con los inspectores

		getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			
	}
}