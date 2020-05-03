/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.insp.model.Cliente;
import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Incidencia;

public class IncidenciaDAOImplementation implements IncidenciaDAO {
	
	private static IncidenciaDAOImplementation instancia = null;
	
	private IncidenciaDAOImplementation() {
	}
	
	public static IncidenciaDAOImplementation getInstance() {
		if (null==instancia)
			instancia = new IncidenciaDAOImplementation();
		return instancia;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void create(Incidencia incidencia) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(incidencia);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Incidencia read(int id) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Incidencia incidencia = session.get(Incidencia.class, id);
		session.getTransaction().commit();
		session.close();
		return incidencia;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Incidencia incidencia) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(incidencia);
		session.getTransaction().commit();
		session.close();	
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Incidencia incidencia) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(incidencia);
		session.getTransaction().commit();
		session.close();
		
	}

	
	/**
	 * @return todas las incidencias registradas en el sistema
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Incidencia> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Incidencia> list = session.createQuery("from Incidencia").list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	
	/**
	 * @param establecimiento del que se quieren conocer las incidencias
	 * @return todas las incidencias asociadas a un establecimiento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Incidencia> readAllIncidencias_Establ(Establecimiento establecimiento) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Query q = session.createQuery("select i from Incidencia i where i.establecimiento_incidencia = :establecimiento order by i.fecha desc");
		q.setParameter("establecimiento", establecimiento); //cambia cif en la query por cif del parametro
		List<Incidencia> incidencias = q.getResultList();
		session.getTransaction().commit();
		session.close();
		return incidencias;
	}
	
	/**
	 * @param establecimiento del que se quieren conocer las incidencias
	 * @param status de las incidencias que se quieren conocer
	 * @return todas las incidencias con un determinado status y asociadas a un establecimiento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Incidencia> readAllIncidencias_EstablStatus(Establecimiento establecimiento, String status) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Query q = session.createQuery("select i from Incidencia i where i.establecimiento_incidencia = :establecimiento and i.status = :status order by i.fecha desc");
		q.setParameter("establecimiento", establecimiento);
		q.setParameter("status", status);
		List<Incidencia> incidencias = q.getResultList();//devuelve lista de incidencias en el establecimiento segun el status pasado como parametro
		session.getTransaction().commit();
		session.close();
		return incidencias;
	}
	
	/**
	 * @param cliente del que se quieren conocer las incidencias
	 * @return todas las incidencias reportadas por un cliente
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Incidencia> readAllIncidencias_Cliente(Cliente cliente) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Query q = session.createQuery("select i from Incidencia i where i.cliente_incidencia = :cliente order by i.fecha desc");
		q.setParameter("cliente", cliente); //cambia cliente en la query por cliente del parametro
		List<Incidencia> incidencias = q.getResultList();
		session.getTransaction().commit();
		session.close();
		return incidencias;
	}
	
	/**
	 * @return todas las incidencias del sistema con status 'pendiente'
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int getIncidenciasPendientes() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Query q = session.createQuery("SELECT count(i) from Incidencia i where i.status='pendiente'");
		int num_incidencias_pendientes = ((Number) q.getSingleResult()).intValue();
		session.getTransaction().commit();
		session.close();
		return num_incidencias_pendientes;
	}
	
	/**
	 * @param establecimiento del que se quieren conocer el numero de incidencias
	 * @return el numero de incidencias pendientes de un determinado establecimiento
	 */
	@SuppressWarnings("unchecked")
	@Override
	public int getIncidenciasPendientes(Establecimiento establecimiento) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Query q = session.createQuery("SELECT count(i) from Incidencia i where i.status='pendiente' and i.establecimiento_incidencia = :establecimiento  ");
		q.setParameter("establecimiento", establecimiento);
		int num_incidencias_pendientes = ((Number) q.getSingleResult()).intValue();
		session.getTransaction().commit();
		session.close();
		return num_incidencias_pendientes;
	}
		
		
	/**
	 * @return el numero de establecimientos que tienenal menos una incidencia pendiente
	 */
		@SuppressWarnings("unchecked")
		@Override
		public int getNumEstablecimientosConIncidenciasPendientes() {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Query q = session.createQuery("select distinct i.establecimiento_incidencia from Incidencia i where i.status='pendiente'");
			List<String> incidencias = q.getResultList();
			int num_establecimientos = incidencias.size();
			session.getTransaction().commit();
			session.close();
			return num_establecimientos;
		}
		
		/**
		 * @param establecimiento del que se quieren conocer las incidencias
		 * @param fecha antes de la que se produjeron las incidencias
		 * @return todas las incidencias reportadas en un establecimiento anteriores a una fecha determinada
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<Incidencia> readAllIncidenciaAntesDeFecha (Establecimiento establecimiento, Date fecha){
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Query q = session.createQuery("select i from Incidencia i where i.status='pendiente' and i.establecimiento_incidencia= :establecimiento and i.fecha <= :fecha");
			q.setParameter("establecimiento", establecimiento);
			q.setParameter("fecha", fecha);
			List<Incidencia> incidencias = q.getResultList();
			session.getTransaction().commit();
			session.close();
			return incidencias;
		}
	
}
