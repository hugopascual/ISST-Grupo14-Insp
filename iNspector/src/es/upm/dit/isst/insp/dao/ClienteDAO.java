package es.upm.dit.isst.insp.dao;

import java.util.Collection;

import es.upm.dit.isst.insp.model.Cliente;

public interface ClienteDAO {

	public void create (Cliente cliente);
	public Cliente read (String email);
	public void update (Cliente cliente);
	public void delete (Cliente cliente);
	public Collection<Cliente> readAll ();
	public Cliente login(String email, String psd);// tenemos que decidir si se loguea con email o usuario 
}
