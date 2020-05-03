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

import es.upm.dit.isst.insp.model.Establecimiento;
import es.upm.dit.isst.insp.model.Inspeccion;
import es.upm.dit.isst.insp.model.Inspector;

public class InspeccionDAOImplementation implements InspeccionDAO {

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

		/**
		 * @return todas las inspeccione registradas en el sistema
		 */
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

		
		/**
		 * 2param establecimiento del que se quiere conocer las inspecciones
		 * @return todas las inspecciones realizadas en un establecimiento
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<Inspeccion> readAllInspecciones_Establ(Establecimiento establecimiento) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Query q = session.createQuery("select i from Inspeccion i where i.establecimiento_inspeccion = :establecimiento order by i.fecha_insp desc");
			q.setParameter("establecimiento", establecimiento);
			List<Inspeccion> inspecciones = q.getResultList();
			session.getTransaction().commit();
			session.close();
			return inspecciones;
		}
		
		/**
		 * @param inspector que ha realizado las inspecciones
		 * @return todas las inspeccion que ha realizado un inspector
		 */
		@SuppressWarnings("unchecked")
		@Override
		public List<Inspeccion> readAllInspecciones_Insp(Inspector inspector) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			Query q = session.createQuery("select i from Inspeccion i where i.inspector_realiza_inspeccion = :inspector order by i.fecha_insp desc");
			q.setParameter("inspector", inspector);
			List<Inspeccion> inspecciones = q.getResultList();
			session.getTransaction().commit();
			session.close();
			return inspecciones;
		}
		
		/**
		 * @param establecimiento del que se quiere conocer la ultima inspeccion
		 * @return la ultima inspeccion que se ha registrado en el establecimiento
		 */
		@SuppressWarnings("unchecked")
		@Override
		public Inspeccion ultimaInspeccion(Establecimiento establecimiento) {
			Session session = SessionFactoryService.get().openSession();
			session.beginTransaction();
			
			Inspeccion inspeccion;

			Query q1 = session.createQuery("select max(i.fecha_insp) from Inspeccion i where i.establecimiento_inspeccion= :establ"); 
			q1.setParameter("establ", establecimiento);
			Object fecha = q1.getSingleResult(); //fecha de la ultima inspeccion del establecimiento
			
			Query q2 = session.createQuery("select i from Inspeccion i where i.establecimiento_inspeccion = :establ and i.fecha_insp = :fecha ");//ultima inspeccion del establecimiento
			q2.setParameter("establ", establecimiento);
			q2.setParameter("fecha", fecha);
			
			inspeccion = (Inspeccion) q2.getSingleResult();//ultima inspeccion en el establecimiento
			session.getTransaction().commit();
			session.close();
			return inspeccion;			
		}
		
}
