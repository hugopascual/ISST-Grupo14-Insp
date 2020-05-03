/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */
package es.upm.dit.isst.insp.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.taglibs.standard.extra.spath.ParseException;

import es.upm.dit.isst.insp.dao.ClienteDAOImplementation;
import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Incidencia;

/**
 * Servlet que se enacarga de guardar la informacion de la incidencia reportada por un cliente
 */

@WebServlet("/FormReportarServlet")
@MultipartConfig
public class FormReportarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Establecimiento establecimiento = (Establecimiento) req.getSession().getAttribute("establecimiento");//establecimiento en el que se reporta
		Cliente cliente= (Cliente) req.getSession().getAttribute("cliente");//cliente que reporta la incidencia

		Date fecha = null;
		
		try {
			fecha = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("fecha_incidencia")); //getParameter siempre devuelve string
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		String gravedad = req.getParameter("gravedad");
		String descripcion = req.getParameter("descripcion");
		
		Incidencia incidencia = new Incidencia();
		
		incidencia.setFecha(fecha);
		incidencia.setGravedad(gravedad);
		incidencia.setDescripcion(descripcion);
		incidencia.setCliente_incidencia(cliente);
		incidencia.setEstablecimiento_incidencia(establecimiento);
		
    	Part filePart = req.getPart("image");
    	InputStream fileContent = filePart.getInputStream();
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
    	
    	byte[] buffer = new byte[10240];
    	for (int length = 0; (length = fileContent.read(buffer)) > 0;)
    		output.write(buffer, 0, length);
    	incidencia.setImagen(output.toByteArray());
    	
    	IncidenciaDAOImplementation.getInstance().create(incidencia);
	
    	getServletContext().getRequestDispatcher("/EstablecimientoView.jsp").forward(req,resp);
			
	}
}