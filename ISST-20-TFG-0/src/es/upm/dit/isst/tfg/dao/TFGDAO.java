package es.upm.dit.isst.tfg.dao;

import java.util.Collection;
import es.upm.dit.isst.tfg.model.TFG;

public interface TFGDAO {
/*
 * Las interfaces deben contener las funciones basicas de persistencia
 * CRUD (Create, Read, Update, Delete), lectura de todos los objetos,
 * y metodo para comprobar la autenticacion.
 */
	
	public void create (TFG tfg);
	public TFG read (String email);
	public void update (TFG tfg);
	public void delete (TFG tfg);
	public Collection<TFG> readAll ();//devuelve una lista de todos los tfgs
	public TFG login (String email, String psd);
	
}
