package es.upm.dit.isst.tfg.servlets;

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

import es.upm.dit.isst.tfg.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.tfg.dao.ClienteDAOImplementation;
import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Incidencia;
import es.upm.dit.isst.tfg.model.Cliente;

/*
 * Servlet para guardar la informacion de la incidencia reportada por un cliente
 */

@WebServlet("/FormReportarServlet")
@MultipartConfig
public class FormReportarServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Establecimiento establecimiento = (Establecimiento) req.getSession().getAttribute("establecimiento");//establecimiento inspeccionado
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
    	
    	//obtengo la inspeccion mas reciente del establecimiento
    	//Inspeccion ultima_inspeccion = InspeccionDAOImplementation.getInstance().ultimaInspeccion(establecimiento);
		//req.getSession().setAttribute("ultima_inspeccion", ultima_inspeccion);
    	
    	//actualizar la lista de inspecciones para que salga la ultima inspeccion
    	//List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento);
		//req.getSession().setAttribute("inspecciones", inspecciones);
	
    	getServletContext().getRequestDispatcher("/EstablecimientoView.jsp").forward(req,resp);//después de reportar la incidencia vuelve a la pagina del establecimiento
			
	}
}