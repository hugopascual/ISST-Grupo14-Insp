package es.upm.dit.isst.tfg.servlets;

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

import es.upm.dit.isst.tfg.dao.ClienteDAOImplementation;
import es.upm.dit.isst.tfg.dao.InspectorDAOImplementation;
import es.upm.dit.isst.tfg.model.Inspector;
import es.upm.dit.isst.tfg.model.Cliente;

/*
 * Servlet para subir fotos de perfil de un usuario
 */

@WebServlet("/FotoPerfilServlet")
@MultipartConfig
public class FotoPerfilServlet extends HttpServlet {
	
		private static final long serialVersionUID = 1L;

		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			
			req.getSession().setAttribute("imagen", true);
			
			String email = req.getParameter("email");
			
			Inspector inspector = InspectorDAOImplementation.getInstance().read(email);
			Cliente cliente = ClienteDAOImplementation.getInstance().read(email);
			
			Part filePart = req.getPart("image");
			InputStream fileContent = filePart.getInputStream();
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			byte[] buffer = new byte[10240];
			for (int length = 0; (length = fileContent.read(buffer)) > 0;) output.write(buffer, 0, length);
			
			if (null != inspector) {
				inspector.setImagen(output.toByteArray());//adjunto la imagen al inspector correspondiente
				InspectorDAOImplementation.getInstance().update(inspector);
			} else if (null != cliente) {
				cliente.setImagen(output.toByteArray());//adjunto la imagen al inspector correspondiente
				ClienteDAOImplementation.getInstance().update(cliente);
			}
			
			resp.sendRedirect(req.getContextPath() + "/FormPerfilUsuarioServlet?email=" + email);
		}
	}