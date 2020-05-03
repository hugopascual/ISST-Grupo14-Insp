/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.dao;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Incidencia;

/**
 * Las clases DAO son utilizadas para realizar operaciones de persistencia asociadas al modelo.
 * @see los metodos de esta interfaz se implementan en la clase IncidenciaDAOImplementation
 */

public interface IncidenciaDAO {
	public void create (Incidencia incidencia);
	public Incidencia read (int id);
	public void update (Incidencia incidencia);
	public void delete (Incidencia incidencia);
	public Collection<Incidencia> readAll ();
	public Collection<Incidencia> readAllIncidencias_Establ(Establecimiento establecimiento);
	public Collection<Incidencia> readAllIncidencias_Cliente(Cliente cliente);
	public List<Incidencia> readAllIncidencias_EstablStatus(Establecimiento establecimiento, String status);
	public int getIncidenciasPendientes();
	public int getIncidenciasPendientes(Establecimiento establecimiento);
	public int getNumEstablecimientosConIncidenciasPendientes();
	public List<Incidencia> readAllIncidenciaAntesDeFecha (Establecimiento establecimiento, Date fecha);
}
