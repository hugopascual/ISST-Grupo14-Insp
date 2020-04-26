package es.upm.dit.isst.insp.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.insp.model.Establecimiento;

/*
 * DAO son clases utilizadas para realizar operaciones de persistencia 
 * asociadas al modelo, es decir, utilizadas para escribir o leer de la 
 * base de datos
 */

public class EstablecimientoDAOImplementation implements EstablecimientoDAO {

	//Esta clase debe seguir el patron de diseno Singleton
	private static EstablecimientoDAOImplementation instancia = null;
	
	Date fecha_hoy = new Date();
	String fecha_hoy_simple= new SimpleDateFormat("yyyy-MM-dd").format(fecha_hoy);
	
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
	
	//saca todos los establecimientos con fecha proxima inspeccion posterior a la fecha del dia de hoy
	@SuppressWarnings("unchecked")
	public List<Establecimiento> readAllOrderInspeccion() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Establecimiento order by proxima_inspeccion asc");
		//Query q = session.createQuery("from Establecimiento where proxima_inspeccion >= :fecha order by proxima_inspeccion asc");
		//q.setParameter("fecha", fecha_hoy);
		List<Establecimiento> list = q.getResultList();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	

}
