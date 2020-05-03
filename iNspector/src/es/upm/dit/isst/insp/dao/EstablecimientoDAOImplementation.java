/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.dao;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;

import es.upm.dit.isst.insp.model.Establecimiento;

public class EstablecimientoDAOImplementation implements EstablecimientoDAO {

	private static EstablecimientoDAOImplementation instancia = null;
	
	Date fecha_hoy = new Date();
	String fecha_hoy_simple= new SimpleDateFormat("yyyy-MM-dd").format(fecha_hoy);
	
	private EstablecimientoDAOImplementation() {
	}
	
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
	public Establecimiento read(String cif) {
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
	

	/**
	 * @return todos los establecimientos con fecha de proxima inspeccion posterior a la fecha del dia de hoy
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Establecimiento> readAllOrderInspeccion() {
		Session session = SessionFactoryService.get().openSession();
		session.beginTransaction();
		Query q = session.createQuery("from Establecimiento order by proxima_inspeccion asc");
		List<Establecimiento> list = q.getResultList();
		session.getTransaction().commit();
		session.close();
		return list;
	}
	

}
