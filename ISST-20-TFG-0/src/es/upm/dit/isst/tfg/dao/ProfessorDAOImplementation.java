package es.upm.dit.isst.tfg.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import es.upm.dit.isst.tfg.model.Professor;

/*
 * DAO son clases utilizadas para realizar operaciones de persistencia 
 * asociadas al modelo, es decir, utilizadas para escribir o leer de la 
 * base de datos
 */

public class ProfessorDAOImplementation implements ProfessorDAO {

	//Esta clase debe seguir el patron de diseno Singleton
		private static ProfessorDAOImplementation instancia = null;
		private static ProfessorDAOImplementation ProfessorDAO;
		
		private ProfessorDAOImplementation() {
		}
		
		
		public static ProfessorDAOImplementation getInstance() {
			if (null==instancia)
				instancia = new ProfessorDAOImplementation();
			return instancia;
			/*if (ProfessorDAO==null)
				ProfessorDAO = new ProfessorDAOImplementation(ProfessorDAO);
			return ProfessorDAO;*/
		}

		@SuppressWarnings("unchecked")
		@Override
		public void create(Professor professor) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.save(professor);
			session.getTransaction().commit();
			session.close();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Professor read(String email) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Professor professor = session.get(Professor.class, email);
			session.getTransaction().commit();
			session.close();
			return professor;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void update(Professor professor) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.saveOrUpdate(professor);
			session.getTransaction().commit();
			session.close();	
		}

		@SuppressWarnings("unchecked")
		@Override
		public void delete(Professor professor) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.delete(professor);
			session.getTransaction().commit();
			session.close();
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Professor> readAll() {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			List<Professor> list = session.createQuery("from Professor").list();
			session.getTransaction().commit();
			session.close();
			return list;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Professor login(String email, String password) {
			Session session = SessionFactoryService.get().openSession();
			Professor professor =null;
			session.beginTransaction();
			Query q = session.createQuery("select p from Professor p where p.email = :email and p.password = :password");
			q.setParameter("email", email); //cambia email en la query por email del parametro
			q.setParameter("password", password);//cambia password en la query por password de parametro
			List<Professor> profs = q.getResultList();
			if (profs.size() > 0)
				professor = (Professor) (q.getResultList().get(0));
			session.getTransaction().commit();
			session.close();
			return professor;
		}
		
}
