/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.dao;

import java.util.Collection;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import es.upm.dit.isst.insp.model.Inspector;

public class InspectorDAOImplementation implements InspectorDAO {

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
		public Inspector read(String email) {
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
			q.setParameter("email", email);
			q.setParameter("password", password);
			List<Inspector> inspectores = q.getResultList();
			if (inspectores.size() > 0)
				inspector = (Inspector) (q.getResultList().get(0));
			session.getTransaction().commit();
			session.close();
			return inspector;
		}
		
}
