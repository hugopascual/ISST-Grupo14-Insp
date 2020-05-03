/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que representa una incidencia
 */

@Entity
public class Incidencia implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String gravedad;
	private String descripcion;
	
	@Temporal(TemporalType.DATE)
	private Date fecha;
	private String status;
	
	@Lob
	private byte[] imagen;
	
	//relaciones definidas en el modelo de datos
	@ManyToOne
	private Establecimiento establecimiento_incidencia;
	
	@ManyToOne
	private Cliente cliente_incidencia;
	
	
	
	public Incidencia() {
		this.status="pendiente";
	}
	
	public Cliente getCliente_incidencia() {
		return cliente_incidencia;
	}
	
	public void setCliente_incidencia(Cliente cliente) {
		this.cliente_incidencia=cliente;
	}
	
	public Establecimiento getEstablecimiento_incidencia() {
		return establecimiento_incidencia;
	}
	
	public void setEstablecimiento_incidencia(Establecimiento establecimiento) {
		this.establecimiento_incidencia=establecimiento;
	}
	
	
	public byte[] getImagen() {
		return imagen;
	}
	
	public void setImagen(byte[] imagen) {
		this.imagen=imagen;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public void changeStatus() {
		if(this.status == null)
			this.status="pendiente";
		
		if(this.status == "pendiente")
			this.status="revisada";
	}

	
	public int getId() {
		return id;
	}

	
	public void setId(int id) {
		this.id = id;
	}

	
	public String getGravedad() {
		return gravedad;
	}

	
	public void setGravedad(String gravedad) {
		this.gravedad = gravedad;
	}

	
	public String getDescripcion() {
		return descripcion;
	}

	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	
	public Date getFecha() {
		return fecha;
	}

	
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((gravedad == null) ? 0 : gravedad.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Incidencia other = (Incidencia) obj;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (gravedad == null) {
			if (other.gravedad != null)
				return false;
		} else if (!gravedad.equals(other.gravedad))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Incidencia [id=" + id + ", gravedad=" + gravedad + ", descripcion=" + descripcion + ", fecha=" + fecha
				+ "]";
	}
	
}
