package es.upm.dit.isst.tfg.dao;

import java.util.Collection;
import java.util.List;

import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Inspeccion;
import es.upm.dit.isst.tfg.model.Inspector;


public interface InspeccionDAO {

/*
 * Las interfaces deben contener las funciones basicas de persistencia
 * CRUD (Create, Read, Update, Delete), lectura de todos los objetos,
 * y metodo para comprobar la autenticacion.
 */
	public void create (Inspeccion inspeccion);
	public Inspeccion read (int id);
	public void update (Inspeccion inspeccion);
	public void delete (Inspeccion inspeccion);
	public Collection<Inspeccion> readAll ();
	public Collection<Inspeccion> readAllInspecciones_Establ(Establecimiento establecimiento);
	public Collection<Inspeccion> readAllInspecciones_Insp(Inspector inspector);
	//public Inspeccion login (String email, String psd);
	
}