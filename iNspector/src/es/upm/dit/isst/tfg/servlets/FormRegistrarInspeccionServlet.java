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

import es.upm.dit.isst.tfg.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.tfg.dao.InspectorDAOImplementation;
import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Inspeccion;
import es.upm.dit.isst.tfg.model.Inspector;

/*
 * Servlet para guardar la informacion de la inspeccion
 */

@WebServlet("/FormRegistrarInspeccionServlet")
@MultipartConfig
public class FormRegistrarInspeccionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Establecimiento establecimiento = (Establecimiento) req.getSession().getAttribute("establecimiento");//establecimiento inspeccionado
		Inspector inspector = (Inspector) req.getSession().getAttribute("inspector");//inspector que registra la inspeccion

		Date fecha_insp = null;
		
		try {
			fecha_insp = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("fecha_insp")); //getParameter siempre devuelve string
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}

		String nota = req.getParameter("nota");
		String descripcion = req.getParameter("descripcion");
		
		Inspeccion inspeccion = new Inspeccion();
		
		inspeccion.setFecha_insp(fecha_insp);
		inspeccion.setNota(nota);
		inspeccion.setDescripcion(descripcion);
		inspeccion.setInspector_realiza_inspeccion(inspector);
		inspeccion.setEstablecimiento_inspeccion(establecimiento);
		
    	Part filePart = req.getPart("file");
    	InputStream fileContent = filePart.getInputStream();
    	ByteArrayOutputStream output = new ByteArrayOutputStream();
    	
    	byte[] buffer = new byte[1024];
    	for (int length = 0; (length = fileContent.read(buffer)) > 0;)
    		output.write(buffer, 0, length);
    	inspeccion.setArchivo(output.toByteArray());
    	
    	InspeccionDAOImplementation.getInstance().create(inspeccion);//respalda la inspeccion en la base de datos
    	
    	//obtengo la inspeccion mas reciente del establecimiento
    	Inspeccion ultima_inspeccion = InspeccionDAOImplementation.getInstance().ultimaInspeccion(establecimiento);
		req.getSession().setAttribute("ultima_inspeccion", ultima_inspeccion);
    	
    	//actualizar la lista de inspecciones para que salga la ultima inspeccion
    	List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento);
		req.getSession().setAttribute("inspecciones", inspecciones);
	
    	getServletContext().getRequestDispatcher("/EstablecimientoView.jsp").forward(req,resp);//después de registrar la inspeccion vuelve a la pagina del establecimiento
			
	}
}