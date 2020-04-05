package es.upm.dit.isst.tfg.servlets;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.isst.tfg.dao.InspeccionDAOImplementation;
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
		
//		DateFormat df = new SimpleDateFormat("dd/MM/yy"); //decimos en que formato queremos mostrar la fecha
//		Calendar date = Calendar.getInstance(); //objeto Calendar actual. Tiene la hora, la fecha, la zona horaria y más cosas que no necesitamos
//	    String fecha_insp = df.format(date.getTime());//guardaremos la fecha como string
		
		String fecha_insp = req.getParameter("date"); //este req.Parameter("date") solo puede sacar strings
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
    	
    	req.getSession().setAttribute("inspeccion", inspeccion);
    	getServletContext().getRequestDispatcher("/EstablecimientoView.jsp").forward(req,resp);//después de registrar la inspeccion vuelve a la pagina del establecimiento
			
	}
}