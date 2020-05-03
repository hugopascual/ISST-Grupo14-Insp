/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

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

/**
 * Servlet que obtiene todos los datos relativos al establecimiento que son necesarios en la pagina de registro de una inspeccion
 */

@WebServlet("/BotonPaginaInspeccionServlet")
public class BotonPaginaInspeccionServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//String con la fecha del dia actual. Este string se utilizara para limitar los calendarios en los que el inspector debe marcar la fecha 
		String fecha_hoy = fechaHoy();
		
		req.getSession().getAttribute("establecimiento");
		req.getSession().getAttribute("inspector");
		req.getSession().setAttribute("fecha_hoy", fecha_hoy);
		
		getServletContext().getRequestDispatcher("/RegistrarInspeccionView.jsp").forward(req,resp);
			
	}
	
	/**
	 * Metodo auxiliar que devuelve un string con la fecha del dia actual con formato yyyy-MM-dd. 
	 * @return string con la fecha de hoy
	 */
	private String fechaHoy() {
		Date fecha_hoy =new Date();
		String hoy = new SimpleDateFormat("yyyy-MM-dd").format(fecha_hoy);
		return hoy;
	}
}