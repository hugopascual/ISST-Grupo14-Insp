/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */
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
import es.upm.dit.isst.insp.validacion.ClaseValidadora;

/**
 * Servlet que se encarga de recoger los datos del formulario de creacion de un establecimiento y respaldarlos en la base de datos
 * Es necesario manejar la excepcion que puede saltar al intentar registrar dos establecimientos con la misma clave primaria (CIF)
 */

@WebServlet("/FormCreaEstablecimientoServlet")
@MultipartConfig
public class FormCreaEstablecimientoServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			String cif = req.getParameter("cif");
			
			//el CIF introducido no es valido
			if (!ClaseValidadora.compruebaCIF(cif)) {
				req.getSession().setAttribute("cif_no_valido", true);
				getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
			} else {
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
				
				//al crear el establecimiento en la BBDD, si ya exite uno con el mismo id, salta una excepcion ya que se vulnera la unicidad de claves
				try{
					EstablecimientoDAOImplementation.getInstance().create(establecimiento);
					
					List<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
					establecimientos.addAll((List<Establecimiento>) req.getSession().getAttribute("establecimientos")); 
					establecimientos.add (establecimiento);//actualizo la lista de establecimientos
					
					req.getSession().setAttribute("establecimientos", establecimientos);
					req.getSession().setAttribute("error_establ", false);
					req.getSession().setAttribute("error_insp", false);
					req.getSession().setAttribute("cif_no_valido", false);
					
					getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
					
				} catch (Exception e) {
					
					req.getSession().setAttribute("error_establ", true);//variable que hara que salte alerta de establecimiento repetido
					req.getSession().setAttribute("error_insp", false);
					req.getSession().setAttribute("cif_no_valido", false);
					getServletContext().getRequestDispatcher("/Admin.jsp").forward(req,resp);
					
				}
			}
			
		}
	}