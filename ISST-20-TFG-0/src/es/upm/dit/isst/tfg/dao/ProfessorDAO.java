package es.upm.dit.isst.tfg.dao;

import java.util.Collection;
import es.upm.dit.isst.tfg.model.Professor;

public interface ProfessorDAO {

/*
 * Las interfaces deben contener las funciones basicas de persistencia
 * CRUD (Create, Read, Update, Delete), lectura de todos los objetos,
 * y metodo para comprobar la autenticacion.
 */
	public void create (Professor professor);
	public Professor read (String email);
	public void update (Professor professor);
	public void delete (Professor professor);
	public Collection<Professor> readAll ();
	public Professor login (String email, String
	psd);
	
}