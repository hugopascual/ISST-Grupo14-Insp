package es.upm.dit.isst.tfg.model;

import java.io.Serializable;
import java.util.Arrays;
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

@Entity
public class Inspeccion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.DATE)
	private Date fecha_insp;//tenemos que ver como manejar las fechas. Probablemente haya que meterlo en la bbdd como string y despues al leerlo formatearlo a Calendar otra vez
	
	private String nota;
	private String descripcion;
	
	@Lob
	private byte[] archivo;
	
	@ManyToOne
	private Inspector inspector_realiza_inspeccion;
	
	@ManyToOne
	private Establecimiento establecimiento_inspeccion;
	
	
	public Inspeccion() {
	}


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}


	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}


	/**
	 * @return the fecha_insp
	 */
	public Date getFecha_insp() {
		return fecha_insp;
	}


	/**
	 * @param fecha_insp the fecha_insp to set
	 */
	public void setFecha_insp(Date fecha_insp) {
		this.fecha_insp = fecha_insp;
	}


	/**
	 * @return the nota
	 */
	public String getNota() {
		return nota;
	}


	/**
	 * @param nota the nota to set
	 */
	public void setNota(String nota) {
		this.nota = nota;
	}


	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}


	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	/**
	 * @return the archivo
	 */
	public byte[] getArchivo() {
		return archivo;
	}


	/**
	 * @param archivo the archivo to set
	 */
	public void setArchivo(byte[] archivo) {
		this.archivo = archivo;
	}


	/**
	 * @return the inspector_realiza_inspeccion
	 */
	public Inspector getInspector_realiza_inspeccion() {
		return inspector_realiza_inspeccion;
	}


	/**
	 * @param inspector_realiza_inspeccion the inspector_realiza_inspeccion to set
	 */
	public void setInspector_realiza_inspeccion(Inspector inspector_realiza_inspeccion) {
		this.inspector_realiza_inspeccion = inspector_realiza_inspeccion;
	}


	/**
	 * @return the establecimiento_inspeccion
	 */
	public Establecimiento getEstablecimiento_inspeccion() {
		return establecimiento_inspeccion;
	}


	/**
	 * @param establecimiento_inspeccion the establecimiento_inspeccion to set
	 */
	public void setEstablecimiento_inspeccion(Establecimiento establecimiento_inspeccion) {
		this.establecimiento_inspeccion = establecimiento_inspeccion;
	}


	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(archivo);
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((establecimiento_inspeccion == null) ? 0 : establecimiento_inspeccion.hashCode());
		result = prime * result + ((fecha_insp == null) ? 0 : fecha_insp.hashCode());
		result = prime * result + id;
		result = prime * result
				+ ((inspector_realiza_inspeccion == null) ? 0 : inspector_realiza_inspeccion.hashCode());
		result = prime * result + ((nota == null) ? 0 : nota.hashCode());
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
		Inspeccion other = (Inspeccion) obj;
		if (!Arrays.equals(archivo, other.archivo))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (establecimiento_inspeccion == null) {
			if (other.establecimiento_inspeccion != null)
				return false;
		} else if (!establecimiento_inspeccion.equals(other.establecimiento_inspeccion))
			return false;
		if (fecha_insp == null) {
			if (other.fecha_insp != null)
				return false;
		} else if (!fecha_insp.equals(other.fecha_insp))
			return false;
		if (id != other.id)
			return false;
		if (inspector_realiza_inspeccion == null) {
			if (other.inspector_realiza_inspeccion != null)
				return false;
		} else if (!inspector_realiza_inspeccion.equals(other.inspector_realiza_inspeccion))
			return false;
		if (nota == null) {
			if (other.nota != null)
				return false;
		} else if (!nota.equals(other.nota))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Inspeccion [id=" + id + ", fecha_insp=" + fecha_insp + ", nota=" + nota + ", descripcion=" + descripcion
				+ ", archivo=" + Arrays.toString(archivo) + ", inspector_realiza_inspeccion="
				+ inspector_realiza_inspeccion + ", establecimiento_inspeccion=" + establecimiento_inspeccion + "]";
	}
	
	
	
}