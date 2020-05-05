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

import es.upm.dit.isst.insp.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.insp.dao.InspeccionDAOImplementation;
import es.upm.dit.isst.insp.dao.InspectorDAOImplementation;
import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspeccion;
import es.upm.dit.isst.insp.model.Inspector;
import es.upm.dit.isst.insp.model.Incidencia;

/**
 * Servlet que se encarga de registrar una inspeccion despues de recopilar todos los datos que el inspector ha introducido en el formulario.
 * 
 * El registro de una inspeccion supone el cambio de estado de todas las incidencias con fecha anterior a la de la inspeccion. Todas estas 
 * incidencias pasan a tener estado 'Revisada'.
 * 
 * Ademas, tambien se establece una nueva fecha de proxima inspeccion en funcion de la nota obtenida en la inspeccion que se esta registrando. 
 * Si la nota es 'Favorable' se programa la proxima inspeccion para dentro de 12 meses; si es 'Favorable condicionado', para dentro de 8 meses;
 * y si es 'Desfavorbale', la proxima inspeccion se producira dentro de 3 meses.
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
		
		//obtengo la fecha de la inspeccion con el formato correspondiente
		try {
			fecha_insp = new SimpleDateFormat("yyyy-MM-dd").parse(req.getParameter("fecha_insp"));
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
    	
    	InspeccionDAOImplementation.getInstance().create(inspeccion);
    	
    	//obtengo la inspeccion mas reciente del establecimiento
    	Inspeccion ultima_inspeccion = InspeccionDAOImplementation.getInstance().ultimaInspeccion(establecimiento);
		req.getSession().setAttribute("ultima_inspeccion", ultima_inspeccion);
    	
		//actualizo la lista de inspecciones para que salga la ultima inspeccion
    	List<Inspeccion> inspecciones = InspeccionDAOImplementation.getInstance().readAllInspecciones_Establ(establecimiento);    	
		req.getSession().setAttribute("inspecciones", inspecciones);
		
		String colorNota = colorNota(ultima_inspeccion);
		req.getSession().setAttribute("colorNota", colorNota);
		
		//TODAS LAS INCIDENCIAS CON FECHA ANTERIOR A LA FECHA DE INSPECCION PASAN A ESTADO 'revisada'
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
	
	/**
	 * Metodo auxiliar que se encarga de sumar una cantidad de meses a una fecha en concreto. 
	 * Para realizar operaciones con la fecha es necesario cambiar a la clase Calendar
	 * @param fecha base
	 * @param numero de meses que se quiere sumar a la fecha base
	 * @return fecha futura
	 */
	private Date sumarMeses(Date fecha, int meses){
		 Calendar calendar = Calendar.getInstance();
		 calendar.setTime(fecha); // Configuramos la fecha que se recibe
		 calendar.add(Calendar.MONTH, meses);  // numero de meses a sumar
		 Date nueva_fecha = calendar.getTime();
		 return nueva_fecha; // Devuelve el objeto Date con los nuevos días añadidos
	}
	
	/**
	 * Metodo auxiliar que define el color en que se mostrara la nota en funcion de la nota de la inspeccion.
	 * @param inspeccion cuya nota se quiere mostrar en color
	 * @return string que define el color utilizado para mostrar la nota
	 */
	private String colorNota(Inspeccion inspeccion) {
		String color= null;
		String nota = inspeccion.getNota();
		if (nota.equals("Favorable")) {
			color = "#00A135"; //verde
		} else if (nota.equals("Favorable condicionado")) {
			color = "#FAB521"; //amarillo
		} else if (nota.equals("Desfavorable")) {
			color = "#FF0000"; //rojo
		}
		return "color:"+color+";";
	}
	
}