package es.upm.dit.isst.tfg.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Incidencia;
import es.upm.dit.isst.tfg.model.Cliente;

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
	public List<Incidencia> getIncidenciasPendientes() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Query q = session.createQuery("SELECT incidencia.status, establecimiento.nombre, count(*) FROM incidencia" + 
				" LEFT JOIN establecimiento" + 
				" ON incidencia.establecimiento_incidencia_cif = establecimiento.cif" + 
				" WHERE incidencia.status = 'pendiente'" + 
				" GROUP BY establecimiento.nombre" + 
				" ORDER BY count(*) desc");
		List<Incidencia> incidencias = q.getResultList();
		session.getTransaction().commit();
		session.close();
		return incidencias;
	}
	
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
