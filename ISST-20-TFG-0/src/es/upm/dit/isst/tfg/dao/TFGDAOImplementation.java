package es.upm.dit.isst.tfg.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import es.upm.dit.isst.tfg.model.TFG;

/*
 * DAO son clases utilizadas para realizar operaciones de persistencia 
 * asociadas al modelo, es decir, utilizadas para escribir o leer de la 
 * base de datos
 */

public class TFGDAOImplementation implements TFGDAO {

	//Esta clase debe seguir el patron de diseno Singleton
	private static TFGDAOImplementation instancia = null;
	private static TFGDAOImplementation TFGDAO;
	
	private TFGDAOImplementation() {
	}
	
	//con esto nos aseguramos de que solo haya creado un objeto de este tipo
	public static TFGDAOImplementation getInstance() {
		if (null==instancia)
			instancia = new TFGDAOImplementation();
		return instancia;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void create(TFG tfg) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(tfg);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public TFG read(String email) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		TFG tfg = session.get(TFG.class, email);
		session.getTransaction().commit();
		session.close();
		return tfg;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(TFG tfg) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(tfg);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(TFG tfg) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(tfg);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TFG> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<TFG> list = session.createQuery("from TFG").list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public TFG login(String email, String password) {
		Session session = SessionFactoryService.get().openSession();
		TFG tfg = null;
		session.beginTransaction();
		Query q = session.createQuery("select t from TFG t where t.email = :email and t.password = :password");
		q.setParameter("email", email);
		q.setParameter("password", password);
		List<TFG> tfgs = q.getResultList();
		if (tfgs.size() > 0)
			tfg = (TFG)(q.getResultList().get(0));
		session.getTransaction().commit();
		session.close();
		return tfg;
	}

}
