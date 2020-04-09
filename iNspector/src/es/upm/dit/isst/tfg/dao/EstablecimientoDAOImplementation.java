package es.upm.dit.isst.tfg.dao;

import java.util.Collection;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import es.upm.dit.isst.tfg.model.Establecimiento;

/*
 * DAO son clases utilizadas para realizar operaciones de persistencia 
 * asociadas al modelo, es decir, utilizadas para escribir o leer de la 
 * base de datos
 */

public class EstablecimientoDAOImplementation implements EstablecimientoDAO {

	//Esta clase debe seguir el patron de diseno Singleton
	private static EstablecimientoDAOImplementation instancia = null;
	
	private EstablecimientoDAOImplementation() {
	}
	
	//con esto nos aseguramos de que solo haya creado un objeto de este tipo
	public static EstablecimientoDAOImplementation getInstance() {
		if (null==instancia)
			instancia = new EstablecimientoDAOImplementation();
		return instancia;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void create(Establecimiento establecimiento) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.save(establecimiento);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Establecimiento read(String cif) {//hay que ver que identifica al establecimiento
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Establecimiento establecimiento= session.get(Establecimiento.class, cif);
		session.getTransaction().commit();
		session.close();
		return establecimiento;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Establecimiento establecimiento) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.saveOrUpdate(establecimiento);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(Establecimiento establecimiento) {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		session.delete(establecimiento);
		session.getTransaction().commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Establecimiento> readAll() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		List<Establecimiento> list = session.createQuery("from Establecimiento").list();
		session.getTransaction().commit();
		session.close();
		return list;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public Establecimiento login(String cif, String password) {//los restaurantes van a necesitar entrar en la app?
//		Session session = SessionFactoryService.get().openSession();
//		Establecimiento establecimiento = null;
//		session.beginTransaction();
//		Query q = session.createQuery("select e from Establecimiento e where e.cif = :cif");
//		//Query q = session.createQuery("select e from Establecimiento e where e.cif = :cif and e.password = :password");
//		q.setParameter("cif", cif);
//		//q.setParameter("password", password);
//		List<Establecimiento> establecimientos = q.getResultList();
//		if (establecimientos.size() > 0)
//			establecimiento = (Establecimiento)(q.getResultList().get(0));
//		session.getTransaction().commit();
//		session.close();
//		return establecimiento;
//	}

}
