package es.upm.dit.isst.tfg.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.upm.dit.isst.tfg.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.tfg.dao.IncidenciaDAOImplementation;
import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Incidencia;

/**
 * Servlet implementation class BotonIncidenciasPendientesServlet
 */
@WebServlet("/BotonIncidenciasPendientesServlet")
public class BotonIncidenciasPendientesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BotonIncidenciasPendientesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		List<Incidencia> incidencias = (List<Incidencia>) IncidenciaDAOImplementation.getInstance().getIncidenciasPendientes();
		req.getSession().setAttribute("incidencias", incidencias);
		getServletContext().getRequestDispatcher("/IncidenciasPendientes.jsp").forward(req,res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
