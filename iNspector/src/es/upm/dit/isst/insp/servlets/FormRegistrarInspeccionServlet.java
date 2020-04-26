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

import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.dao.InspectorDAOImplementation;
import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspeccion;
import es.upm.dit.isst.insp.model.Inspector;
import es.upm.dit.isst.insp.model.Incidencia;

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

		//REGISTRO DE UNA NUEVA INSPECCION EN LA BASE DE DATOS
		
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
    	
    	Inspeccion ultima_inspeccion = InspeccionDAOImplementation.getInstance().ultimaInspeccion(establecimiento);//obtengo la inspeccion mas reciente del establecimiento
		req.getSession().setAttribute("ultima_inspeccion", ultima_inspeccion);
    	
    	List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento);    	//actualizar la lista de inspecciones para que salga la ultima inspeccion
		req.getSession().setAttribute("inspecciones", inspecciones);
		
		//TODAS LAS INCIDENCIAS CON FECHA ANTERIOR A LA FECHA DE INSPECCION PASAN A ESTADO ´revisada'
		
		List<Incidencia> incidencias_no_revisadas = IncidenciaDAOImplementation.getInstance().readAllIncidenciaAntesDeFecha(establecimiento,fecha_insp);
		
		for(Incidencia incidencia : incidencias_no_revisadas) {
			incidencia.setStatus("revisada");
			IncidenciaDAOImplementation.getInstance().update(incidencia);
		}
		
		//ACTUALIZACION DE LA FECHA DE LA PROXIMA INSPECCION DEL ESTABLECIMIENTO SEGUN LA NOTA DE LA INSPECCION REGISTRADA
		
		Date nueva_fecha_insp = fecha_insp;
		if (nota.equals("Favorable")) {
			nueva_fecha_insp = sumarMeses(fecha_insp,12);
		} else if (nota.equals("Favorable condicionado")) {
			nueva_fecha_insp = sumarMeses(fecha_insp,8);
		} else if (nota.equals("Desfavorable")) {
			nueva_fecha_insp = sumarMeses(fecha_insp,3);
		}
		
		establecimiento.setProxima_inspeccion(nueva_fecha_insp);
		EstablecimientoDAOImplementation.getInstance().update(establecimiento);
		
		//leer de nuevo el objeto Establecimiento correspondiente para que la fecha aparezca con el formato correcto
		Establecimiento establec = EstablecimientoDAOImplementation.getInstance().read(establecimiento.getCif());
		req.getSession().setAttribute("establecimiento", establec);
		
    	getServletContext().getRequestDispatcher("/EstablecimientoView.jsp").forward(req,resp);//después de registrar la inspeccion vuelve a la pagina del establecimiento	
	}
	
	private Date sumarMeses(Date fecha, int meses){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(fecha); // Configuramos la fecha que se recibe
		 calendar.add(Calendar.MONTH, meses);  // numero de meses a sumar
		 Date nueva_fecha = calendar.getTime();
		 return nueva_fecha; // Devuelve el objeto Date con los nuevos días añadidos
	}
	
}