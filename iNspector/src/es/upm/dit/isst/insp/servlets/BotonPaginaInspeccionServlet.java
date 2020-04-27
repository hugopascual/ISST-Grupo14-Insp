package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * Servlet para sacar toda la información relativa al establecimiento seleccionado para el registro de la inspeccion
 */

@WebServlet("/BotonPaginaInspeccionServlet")
public class BotonPaginaInspeccionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Date fecha = new Date();//fecha de hoy
		
		req.getSession().getAttribute("establecimiento");
		req.getSession().getAttribute("inspector");
		req.getSession().setAttribute("fecha", fecha);
		
		getServletContext().getRequestDispatcher("/RegistrarInspeccionView.jsp").forward(req,resp);
			
	}
}