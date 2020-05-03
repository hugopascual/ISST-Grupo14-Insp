/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;


import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.model.Incidencia;

/*
 * Servlet que devuelve la imagen asoaciada a una incidencia
 */

@WebServlet("/ServeImageServletInt")
public class ServeImageServletInt extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		String id = (req.getParameter("id")); 
		
		int id_int= Integer.parseInt(id);
		
		Incidencia incidencia = IncidenciaDAOImplementation.getInstance().read(id_int);
		
		if (null != incidencia) {
				resp.setContentLength(incidencia.getImagen().length);
				resp.getOutputStream().write(incidencia.getImagen());	
			}
		}
}
