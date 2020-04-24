package es.upm.dit.isst.insp.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.insp.model.Cliente;

public class ClienteDAOImplementation implements ClienteDAO {

	//Esta clase debe seguir el patron de diseno Singleton
		private static ClienteDAOImplementation instancia = null;
		
		private ClienteDAOImplementation() {
		}
		
		
		public static ClienteDAOImplementation getInstance() {
			if (null==instancia)
				instancia = new ClienteDAOImplementation();
			return instancia;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void create(Cliente cliente) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.save(cliente);
			session.getTransaction().commit();
			session.close();
		}

		@SuppressWarnings("unchecked")
		@Override
		public Cliente read(String email) {//hay que ver como leer el identificador del inspector
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Cliente cliente = session.get(Cliente.class, email);
			session.getTransaction().commit();
			session.close();
			return cliente;
		}

		@SuppressWarnings("unchecked")
		@Override
		public void update(Cliente cliente) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.saveOrUpdate(cliente);
			session.getTransaction().commit();
			session.close();	
		}

		@SuppressWarnings("unchecked")
		@Override
		public void delete(Cliente cliente) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			session.delete(cliente);
			session.getTransaction().commit();
			session.close();
			
		}

		@SuppressWarnings("unchecked")
		@Override
		public List<Cliente> readAll() {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			List<Cliente> list = session.createQuery("from Cliente").list();
			session.getTransaction().commit();
			session.close();
			return list;
		}

		@SuppressWarnings("unchecked")
		@Override
		public Cliente login(String email, String password) {
			Session session = SessionFactoryService.get().openSession();
			Cliente cliente =null;
			session.beginTransaction();
			Query q = session.createQuery("select c from Cliente c where c.email = :email and c.password = :password");
			q.setParameter("email", email); //cambia email en la query por email del parametro
			q.setParameter("password", password);//cambia password en la query por password de parametro
			List<Cliente> clientes = q.getResultList();
			if (clientes.size() > 0)
				cliente = (Cliente) (q.getResultList().get(0));
			session.getTransaction().commit();
			session.close();
			return cliente;
		}
		
}

