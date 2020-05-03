/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.dao;

import java.util.Collection;
import java.util.List;

import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspeccion;
import es.upm.dit.isst.insp.model.Inspector;

/**
 * Las clases DAO son utilizadas para realizar operaciones de persistencia asociadas al modelo.
 * @see los metodos de esta interfaz se implementan en la clase InspeccionDAOImplementation
 */

public interface InspeccionDAO {

	public void create (Inspeccion inspeccion);
	public Inspeccion read (int id);
	public void update (Inspeccion inspeccion);
	public void delete (Inspeccion inspeccion);
	public Collection<Inspeccion> readAll ();
	public Collection<Inspeccion> readAllInspecciones_Establ(Establecimiento establecimiento);
	public Collection<Inspeccion> readAllInspecciones_Insp(Inspector inspector);
	public Inspeccion ultimaInspeccion(Establecimiento establecimiento);
	
}