package es.upm.dit.isst.insp.dao;

import java.util.Collection;

import es.upm.dit.isst.insp.model.Inspector;

public interface InspectorDAO {

/*
 * Las interfaces deben contener las funciones basicas de persistencia
 * CRUD (Create, Read, Update, Delete), lectura de todos los objetos,
 * y metodo para comprobar la autenticacion.
 */
	public void create (Inspector inspector);
	public Inspector read (String email);
	public void update (Inspector inspector);
	public void delete (Inspector inspector);
	public Collection<Inspector> readAll ();
	public Inspector login (String email, String
	psd);
	
}