package es.upm.dit.isst.insp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.model.Inspeccion;

@WebServlet("/ServeFileServlet")
public class ServeFileServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String id_string = (req.getParameter("inspeccion_id"));//getparameter devuelve siempre strings
		int id;
		
		try {
			id = Integer.parseInt(id_string); //parseamos a id a int
		} catch(NumberFormatException e) {
			   id = 999999999;
		}
		
		Inspeccion inspeccion = InspeccionDAOImplementation.getInstance().read(id);
		resp.setContentType("application/pdf");
		resp.setHeader("Content-Disposition",String.format("attachment; filename=\"%s\"", "informe_de_inspeccion.pdf"));
		resp.setContentLength(inspeccion.getArchivo().length);
		resp.getOutputStream().write(inspeccion.getArchivo());

    }

}
