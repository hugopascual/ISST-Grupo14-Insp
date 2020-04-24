package es.upm.dit.isst.insp.dao;

import java.util.Collection;

import es.upm.dit.isst.insp.model.Establecimiento;

public interface EstablecimientoDAO {
/*
 * Las interfaces deben contener las funciones basicas de persistencia
 * CRUD (Create, Read, Update, Delete), lectura de todos los objetos,
 * y metodo para comprobar la autenticacion.
 */
	
	public void create (Establecimiento establecimiento);
	public Establecimiento read (String email);
	public void update (Establecimiento establecimiento);
	public void delete (Establecimiento establecimiento);
	public Collection<Establecimiento> readAll ();//devuelve una lista de todos los tfgs
	//public Establecimiento login(String email, String psd);// el establecimiento no necesita entrar en la pagina
	
}
