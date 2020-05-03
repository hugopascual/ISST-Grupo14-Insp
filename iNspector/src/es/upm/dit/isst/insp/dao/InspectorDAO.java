/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.dao;

import java.util.Collection;

import es.upm.dit.isst.insp.model.Inspector;

/**
 * Las clases DAO son utilizadas para realizar operaciones de persistencia asociadas al modelo.
 * @see los metodos de esta interfaz se implementan en la clase InspectorDAOImplementation
 */
public interface InspectorDAO {

	public void create (Inspector inspector);
	public Inspector read (String email);
	public void update (Inspector inspector);
	public void delete (Inspector inspector);
	public Collection<Inspector> readAll ();
	public Inspector login (String email, String
	psd);
	
}