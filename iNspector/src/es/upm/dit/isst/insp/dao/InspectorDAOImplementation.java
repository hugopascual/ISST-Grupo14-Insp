package es.upm.dit.isst.insp.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.insp.model.Inspector;

/*
 * DAO son clases utilizadas para realizar operaciones de persistencia 
 * asociadas al modelo, es decir, utilizadas para escribir o leer de la 
 * base de datos
 */

public class InspectorDAOImplementation implements InspectorDAO {

	//Esta clase debe seguir el patron de diseno Singleton
		private static InspectorDAOImplementation instancia = null;
		
		private InspectorDAOImplementation() {
		}
		
		
		public static InspectorDAOImplementation getInstance() {
			if (null==instancia)
				instancia = new InspectorDAOImplementation();
			return instancia;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void create(Inspector inspector) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.save(inspector);
			session.getTransaction().commit();
			session.close();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Inspector read(String email) {//hay que ver como leer el identificador del inspector
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Inspector inspector = session.get(Inspector.class, email);
			session.getTransaction().commit();
			session.close();
			return inspector;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void update(Inspector inspector) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.saveOrUpdate(inspector);
			session.getTransaction().commit();
			session.close();	
		}

		@SuppressWarnings("unchecked")
		@Override
		public void delete(Inspector inspector) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.delete(inspector);
			session.getTransaction().commit();
			session.close();
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Inspector> readAll() {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			List<Inspector> list = session.createQuery("from Inspector").list();
			session.getTransaction().commit();
			session.close();
			return list;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Inspector login(String email, String password) {
			Session session = SessionFactoryService.get().openSession();
			Inspector inspector =null;
			session.beginTransaction();
			Query q = session.createQuery("select i from Inspector i where i.email = :email and i.password = :password");
			q.setParameter("email", email); //cambia email en la query por email del parametro
			q.setParameter("password", password);//cambia password en la query por password de parametro
			List<Inspector> inspectores = q.getResultList();
			if (inspectores.size() > 0)
				inspector = (Inspector) (q.getResultList().get(0));
			session.getTransaction().commit();
			session.close();
			return inspector;
		}
		
}
