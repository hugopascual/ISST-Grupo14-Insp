package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * Servlet para sacar toda la información relativa al establecimiento seleccionado
 */

@WebServlet("/BotonPaginaInspeccionServlet")
public class BotonPaginaInspeccionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	DateFormat df = new SimpleDateFormat("dd/MM/yy"); //decimos en que formato queremos mostrar la fecha
	Calendar date = Calendar.getInstance(); //objeto Calendar con la fecha actual. Tiene la hora, la fecha, la zona horaria y más cosas que no necesitamos
	String fecha = df.format(date.getTime());//guardaremos la fecha como string y despues para ordenar volveremos a ponerla con formato Calendar para poder hacer operaciones con ello
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.getSession().getAttribute("establecimiento");
		req.getSession().getAttribute("inspector");
		req.getSession().setAttribute("fecha_actual", fecha);
		
		getServletContext().getRequestDispatcher("/RegistrarInspeccionView.jsp").forward(req,resp);
			
	}
}