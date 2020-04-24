package es.upm.dit.isst.insp.dao;

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

	//Obtiene TODAS las incidencias registradas en el sistema
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

	
	//Obtiene las incidencia segun el restaurante
	//este metodo se puede modificar para que las incidencias devueltas sean solo las de status sea el que queramos mostrar - hay que anadirlo en la query
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
	
	//Obtiene las incidencia segun el restaurante y segun su status
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
	
	//Obtiene las inspecciones segun el cliente que las ha realizado
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
	
	//Obtiene el número de indicencias pendientes segun establecimiento y ordenadas por cantidad
	@SuppressWarnings("unchecked")
	@Override
	public List<Incidencia> getIncidenciasPendientes() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Query q = session.createQuery("SELECT i from Incidencia i where i.status='pendiente'");
		List<Incidencia> incidencias = q.getResultList();
		session.getTransaction().commit();
		session.close();
		return incidencias;
	}
	
	//Obtiene el número de indicencias pendientes de un establecimiento
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
		
	
//	@SuppressWarnings("unchecked")
//	public List<Incidencia> getIncidenciasPendientes_Establecimiento(Establecimiento establecimiento) {
//		Session session = SessionFactoryService.get().openSession();
//		session.beginTransaction();
//		Query q = session.createQuery("select i from Incidencia i where i.status='pendiente' and ");
//		List<Incidencia> incidencias = q.getResultList();
//		session.getTransaction().commit();
//		session.close();
//		return incidencias;
//	}
	
	//Obtiene la ultima inspeccion realizada en un establecimiento
//	public Inspeccion ultimaInspeccion(Establecimiento establecimiento) {
//		Session session = SessionFactoryService.get().openSession();
//		session.beginTransaction();
//		
//		Inspeccion inspeccion;
//
//		Query q1 = session.createQuery("select max(i.fecha_insp) from Inspeccion i where i.establecimiento_inspeccion= :establ"); //la fecha de la ultima inspeccion del establecimiento
//		q1.setParameter("establ", establecimiento);
//		Object fecha = q1.getSingleResult();
//		
//		Query q2 = session.createQuery("select i from Inspeccion i where i.establecimiento_inspeccion = :establ and i.fecha_insp = :fecha ");//ultima inspeccion del establecimiento
//		q2.setParameter("establ", establecimiento);
//		q2.setParameter("fecha", fecha);
//		
//		inspeccion = (Inspeccion) q2.getSingleResult();
//		session.getTransaction().commit();
//		session.close();
//		return inspeccion;			
//   }
	
	
	// TODO readAllBeforeDateAndPendiente

}
