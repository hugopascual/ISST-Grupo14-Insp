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

import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.model.Inspeccion;

/**
 * Servlet que se encarga de obtener y descargar el fichero de la inspeccion de un establecimiento
 */

@WebServlet("/ServeFileServlet")
public class ServeFileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String cif = req.getParameter("establecimiento_cif");
		String fecha_inspeccion = req.getParameter("fecha_inspeccion");
		String id_string = (req.getParameter("inspeccion_id"));
		
		int id;
		
		try {
			id = Integer.parseInt(id_string); //parseamos a id a int
		} catch(NumberFormatException e) {
			   id = 999999999;
		}
		
		Inspeccion inspeccion = InspeccionDAOImplementation.getInstance().read(id);
		resp.setContentType("application/pdf");
		//el nombre del archivo de la inspeccion se compone de la fecha de la inspeccion y el cif del establecimiento
		resp.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", fecha_inspeccion+"_"+cif+"_informe.pdf"));
		resp.setContentLength(inspeccion.getArchivo().length);
		resp.getOutputStream().write(inspeccion.getArchivo());

    }

}
