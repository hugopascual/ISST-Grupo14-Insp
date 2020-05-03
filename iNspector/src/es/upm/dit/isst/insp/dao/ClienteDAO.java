/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.dao;

import java.util.Collection;
import es.upm.dit.isst.insp.model.Cliente;

/**
 * Las clases DAO son utilizadas para realizar operaciones de persistencia asociadas al modelo.
 * @see los metodos de esta interfaz se implementan en la clase ClienteDAOImplementation
 */

public interface ClienteDAO {

	public void create (Cliente cliente);
	public Cliente read (String email);
	public void update (Cliente cliente);
	public void delete (Cliente cliente);
	public Collection<Cliente> readAll ();
	public Cliente login(String email, String psd); 
}
