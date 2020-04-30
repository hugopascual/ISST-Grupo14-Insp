package es.upm.dit.isst.insp.servlets;

import java.io.IOException;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import es.upm.dit.isst.insp.dao.EstablecimientoDAOImplementation;
import es.upm.dit.isst.insp.model.Establecimiento;

/*
 * Con el metodo doGet lee los parametros, crea un objeto Profesor respaldado en la base de datos
 * y actualiza la lista de profesores en la sesion de manera que Admin.jsp pueda mostrar en cada 
 * momento la lista de profesores dados de alta en la aplicacion
 */

@WebServlet("/FormCreaEstablecimientoServlet")
@MultipartConfig
public class FormCreaEstablecimientoServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String cif = req.getParameter("cif");
			String nombre = req.getParameter("nombre");
			String direccion = req.getParameter("direccion");
			String ciudad = req.getParameter("ciudad");
			String rep_legal = req.getParameter("rep_legal");
			String tipo = req.getParameter("tipo");
			String codigo_postal = req.getParameter("codigo_postal");
			Part filePart = req.getPart("image");
			
			Establecimiento establecimiento = new Establecimiento();
			
			establecimiento.setCif(cif);
			establecimiento.setNombre(nombre);
			establecimiento.setDireccion(direccion);
			establecimiento.setCiudad(ciudad);
			establecimiento.setRep_legal(rep_legal);
			establecimiento.setTipo(tipo);
			establecimiento.setCodigo_postal(codigo_postal);
			
			InputStream fileContent = filePart.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[10240];
			for (int length = 0; (length = fileContent.read(buffer)) > 0;) output.write(buffer, 0, length);
			establecimiento.setImagen(output.toByteArray());
			
			try{EstablecimientoDAOImplementation.getInstance().create(establecimiento);//respalda el establecimiento en la base de datos
				List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
				establecimientos.addAll((List<Establecimiento>) req.getSession().getAttribute("establecimientos"));//lee los establecimientos ya existentes 
				establecimientos.add (establecimiento);//anade el nuevo establecimiento
				req.getSession().setAttribute("establecimientos", establecimientos);//actualiza el atributo de la sesion con los establecimientos
				getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			} catch (Exception e) {
				req.getSession().setAttribute("error_establ", true);
				req.getSession().setAttribute("error_insp", false);
				getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
				
			}
			
			
		}
	}