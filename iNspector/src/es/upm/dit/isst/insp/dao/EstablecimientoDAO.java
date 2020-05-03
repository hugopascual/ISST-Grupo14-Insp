/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.dao;

import java.util.Collection;
import java.util.List;

import es.upm.dit.isst.insp.model.Establecimiento;

/**
 * Las clases DAO son utilizadas para realizar operaciones de persistencia asociadas al modelo.
 * @see los metodos de esta interfaz se implementan en la clase EstablecimientoDAOImplementation
 */

public interface EstablecimientoDAO {
	
	public void create (Establecimiento establecimiento);
	public Establecimiento read (String email);
	public void update (Establecimiento establecimiento);
	public void delete (Establecimiento establecimiento);
	public Collection<Establecimiento> readAll ();
	public List<Establecimiento> readAllOrderInspeccion();
	
}
