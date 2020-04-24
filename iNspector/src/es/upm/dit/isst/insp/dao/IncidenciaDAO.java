package es.upm.dit.isst.insp.dao;

import java.util.Collection;
import java.util.List;

import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Incidencia;

public interface IncidenciaDAO {
	public void create (Incidencia incidencia);
	public Incidencia read (int id);
	public void update (Incidencia incidencia);
	public void delete (Incidencia incidencia);
	public Collection<Incidencia> readAll ();
	public Collection<Incidencia> readAllIncidencias_Establ(Establecimiento establecimiento);
	public Collection<Incidencia> readAllIncidencias_Cliente(Cliente cliente);
	public List<Incidencia> readAllIncidencias_EstablStatus(Establecimiento establecimiento, String status);
	public List<Incidencia> getIncidenciasPendientes();
	public int getIncidenciasPendientes(Establecimiento establecimiento);
}
