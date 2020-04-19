package es.upm.dit.isst.tfg.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.tfg.model.Establecimiento;
import es.upm.dit.isst.tfg.model.Inspeccion;
import es.upm.dit.isst.tfg.model.Inspector;

/*
 * DAO son clases utilizadas para realizar operaciones de persistencia 
 * asociadas al modelo, es decir, utilizadas para escribir o leer de la 
 * base de datos
 */

public class InspeccionDAOImplementation implements InspeccionDAO {

	//Esta clase debe seguir el patron de diseno Singleton
		private static InspeccionDAOImplementation instancia = null;
		
		private InspeccionDAOImplementation() {
		}
		
		
		public static InspeccionDAOImplementation getInstance() {
			if (null==instancia)
				instancia = new InspeccionDAOImplementation();
			return instancia;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void create(Inspeccion inspeccion) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.save(inspeccion);
			session.getTransaction().commit();
			session.close();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Inspeccion read(int id) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Inspeccion inspeccion = session.get(Inspeccion.class, id);
			session.getTransaction().commit();
			session.close();
			return inspeccion;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void update(Inspeccion inspeccion) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.saveOrUpdate(inspeccion);
			session.getTransaction().commit();
			session.close();	
		}

		@SuppressWarnings("unchecked")
		@Override
		public void delete(Inspeccion inspeccion) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.delete(inspeccion);
			session.getTransaction().commit();
			session.close();
			
		}

		//Obtiene TODAS las inspecciones del sistema
		@SuppressWarnings("unchecked")
		@Override
		public List<Inspeccion> readAll() {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			List<Inspeccion> list = session.createQuery("from Inspeccion").list();
			session.getTransaction().commit();
			session.close();
			return list;
		}

		
		//Obtiene las inspecciones segun el restaurante
		@SuppressWarnings("unchecked")
		@Override
		public List<Inspeccion> readAllInspecciones_Establ(Establecimiento establecimiento) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Query q = session.createQuery("select i from Inspeccion i where i.establecimiento_inspeccion = :establecimiento order by i.fecha_insp desc");
			q.setParameter("establecimiento", establecimiento); //cambia cif en la query por cif del parametro
			List<Inspeccion> inspecciones = q.getResultList();
			session.getTransaction().commit();
			session.close();
			return inspecciones;
		}
		
		//Obtiene las inspecciones segun el inspector que las ha realizado
		@SuppressWarnings("unchecked")
		@Override
		public List<Inspeccion> readAllInspecciones_Insp(Inspector inspector) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Query q = session.createQuery("select i from Inspeccion i where i.inspector_realiza_inspeccion = :inspector order by i.fecha_insp desc");
			q.setParameter("inspector", inspector); //cambia inspector en la query por inspector del parametro
			List<Inspeccion> inspecciones = q.getResultList();
			session.getTransaction().commit();
			session.close();
			return inspecciones;
		}
		
		//Obtiene la ultima inspeccion realizada en un establecimiento
		public Inspeccion ultimaInspeccion(Establecimiento establecimiento) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			
			Inspeccion inspeccion;

			Query q1 = session.createQuery("select max(i.fecha_insp) from Inspeccion i where i.establecimiento_inspeccion= :establ"); //la fecha de la ultima inspeccion del establecimiento
			q1.setParameter("establ", establecimiento);
			Object fecha = q1.getSingleResult();
			
			Query q2 = session.createQuery("select i from Inspeccion i where i.establecimiento_inspeccion = :establ and i.fecha_insp = :fecha ");//ultima inspeccion del establecimiento
			q2.setParameter("establ", establecimiento);
			q2.setParameter("fecha", fecha);
			
			inspeccion = (Inspeccion) q2.getSingleResult();
			session.getTransaction().commit();
			session.close();
			return inspeccion;			
		}
		
		
		

//		@SuppressWarnings("unchecked")
//		@Override
//		public Inspeccion login(String email, String password) {
//			Session session = SessionFactoryService.get().openSession();
//			Inspector inspector =null;
//			session.beginTransaction();
//			Query q = session.createQuery("select i from Inspector i where i.email = :email and i.password = :password");
//			q.setParameter("email", email); //cambia email en la query por email del parametro
//			q.setParameter("password", password);//cambia password en la query por password de parametro
//			List<Inspector> inspectores = q.getResultList();
//			if (inspectores.size() > 0)
//				inspector = (Inspector) (q.getResultList().get(0));
//			session.getTransaction().commit();
//			session.close();
//			return inspector;
//		}
		
}
