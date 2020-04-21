package es.upm.dit.isst.tfg.dao;

import java.util.Collection;

import es.upm.dit.isst.tfg.model.Cliente;
import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Incidencia;

public interface IncidenciaDAO {
	public void create (Incidencia incidencia);
	public Incidencia read (int id);
	public void update (Incidencia incidencia);
	public void delete (Incidencia incidencia);
	public Collection<Incidencia> readAll ();
	public Collection<Incidencia> readAllIncidencias_Establ(Establecimiento establecimiento);
	public Collection<Incidencia> readAllIncidencias_Cliente(Cliente cliente);
}
