package es.upm.dit.isst.tfg.model;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Inspeccion implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id 
	private int id; //tenemos que ver como hacer para generarlo de manera automatica
	private Calendar fecha_insp;//tenemos que ver como manejar las fechas 
	private String higiene_instalacion;
	private String estado_instalaciones;
	private String area_prep_alim;
	private String estado_alimentos;
	private String higiene_empleados;
	private String nota;
	private String descripcion;

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
	public Calendar getFecha_insp() {
		return fecha_insp;
	}

	/**
	 * @param fecha_insp the fecha_insp to set
	 */
	public void setFecha_insp(Calendar fecha_insp) {
		this.fecha_insp = fecha_insp;
	}

	/**
	 * @return the higiene_instalacion
	 */
	public String getHigiene_instalacion() {
		return higiene_instalacion;
	}

	/**
	 * @param higiene_instalacion the higiene_instalacion to set
	 */
	public void setHigiene_instalacion(String higiene_instalacion) {
		this.higiene_instalacion = higiene_instalacion;
	}

	/**
	 * @return the estado_instalaciones
	 */
	public String getEstado_instalaciones() {
		return estado_instalaciones;
	}

	/**
	 * @param estado_instalaciones the estado_instalaciones to set
	 */
	public void setEstado_instalaciones(String estado_instalaciones) {
		this.estado_instalaciones = estado_instalaciones;
	}

	/**
	 * @return the area_prep_alim
	 */
	public String getArea_prep_alim() {
		return area_prep_alim;
	}

	/**
	 * @param area_prep_alim the area_prep_alim to set
	 */
	public void setArea_prep_alim(String area_prep_alim) {
		this.area_prep_alim = area_prep_alim;
	}

	/**
	 * @return the estado_alimentos
	 */
	public String getEstado_alimentos() {
		return estado_alimentos;
	}

	/**
	 * @param estado_alimentos the estado_alimentos to set
	 */
	public void setEstado_alimentos(String estado_alimentos) {
		this.estado_alimentos = estado_alimentos;
	}

	/**
	 * @return the higiene_empleados
	 */
	public String getHigiene_empleados() {
		return higiene_empleados;
	}

	/**
	 * @param higiene_empleados the higiene_empleados to set
	 */
	public void setHigiene_empleados(String higiene_empleados) {
		this.higiene_empleados = higiene_empleados;
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
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area_prep_alim == null) ? 0 : area_prep_alim.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((estado_alimentos == null) ? 0 : estado_alimentos.hashCode());
		result = prime * result + ((estado_instalaciones == null) ? 0 : estado_instalaciones.hashCode());
		result = prime * result + ((fecha_insp == null) ? 0 : fecha_insp.hashCode());
		result = prime * result + ((higiene_empleados == null) ? 0 : higiene_empleados.hashCode());
		result = prime * result + ((higiene_instalacion == null) ? 0 : higiene_instalacion.hashCode());
		result = prime * result + id;
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
		if (area_prep_alim == null) {
			if (other.area_prep_alim != null)
				return false;
		} else if (!area_prep_alim.equals(other.area_prep_alim))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (estado_alimentos == null) {
			if (other.estado_alimentos != null)
				return false;
		} else if (!estado_alimentos.equals(other.estado_alimentos))
			return false;
		if (estado_instalaciones == null) {
			if (other.estado_instalaciones != null)
				return false;
		} else if (!estado_instalaciones.equals(other.estado_instalaciones))
			return false;
		if (fecha_insp == null) {
			if (other.fecha_insp != null)
				return false;
		} else if (!fecha_insp.equals(other.fecha_insp))
			return false;
		if (higiene_empleados == null) {
			if (other.higiene_empleados != null)
				return false;
		} else if (!higiene_empleados.equals(other.higiene_empleados))
			return false;
		if (higiene_instalacion == null) {
			if (other.higiene_instalacion != null)
				return false;
		} else if (!higiene_instalacion.equals(other.higiene_instalacion))
			return false;
		if (id != other.id)
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
		return "Inspeccion [id=" + id + ", fecha_insp=" + fecha_insp + ", higiene_instalacion=" + higiene_instalacion
				+ ", estado_instalaciones=" + estado_instalaciones + ", area_prep_alim=" + area_prep_alim
				+ ", estado_alimentos=" + estado_alimentos + ", higiene_empleados=" + higiene_empleados + ", nota="
				+ nota + ", descripcion=" + descripcion + "]";
	}

}
