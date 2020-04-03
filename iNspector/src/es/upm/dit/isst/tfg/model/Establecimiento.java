package es.upm.dit.isst.tfg.model;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Establecimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String cif; // tendremos que implementar una funcion que compruebe si es valido
	private String nombre;
	private String direccion;
	private String ciudad;
	private String rep_legal;
	private String tipo; //tipo puede ser: bar, restaurante, cafeteria, hotel, otro
	
	@OneToMany(mappedBy="establecimiento_inspeccion", fetch = FetchType.EAGER)
	private List <Inspeccion> inspecciones_realizadas_rest;
	
	@OneToMany (mappedBy="establecimiento_incidencia", fetch= FetchType.EAGER)
	private Set <Incidencia> incidencias;
	
	@ManyToMany (mappedBy = "establecimientos_inspeccionados")
	private List <Inspector> inspectores_realizado_inspecciones;
	
	public Establecimiento() {
	}
	
	@Override
	public String toString() {
		return "Establecimiento [cif=" + cif + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad
				+ ", rep_legal=" + rep_legal + ", tipo=" + tipo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cif == null) ? 0 : cif.hashCode());
		result = prime * result + ((ciudad == null) ? 0 : ciudad.hashCode());
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((rep_legal == null) ? 0 : rep_legal.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Establecimiento other = (Establecimiento) obj;
		if (cif == null) {
			if (other.cif != null)
				return false;
		} else if (!cif.equals(other.cif))
			return false;
		if (ciudad == null) {
			if (other.ciudad != null)
				return false;
		} else if (!ciudad.equals(other.ciudad))
			return false;
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (rep_legal == null) {
			if (other.rep_legal != null)
				return false;
		} else if (!rep_legal.equals(other.rep_legal))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}


	/**
	 * @return the cif
	 */
	public String getCif() {
		return cif;
	}

	/**
	 * @param cif the cif to set
	 */
	public void setCif(String cif) {
		this.cif = cif;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the direccion
	 */
	public String getDireccion() {
		return direccion;
	}

	/**
	 * @param direccion the direccion to set
	 */
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/**
	 * @return the ciudad
	 */
	public String getCiudad() {
		return ciudad;
	}

	/**
	 * @param ciudad the ciudad to set
	 */
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/**
	 * @return the rep_legal
	 */
	public String getRep_legal() {
		return rep_legal;
	}

	/**
	 * @param rep_legal the rep_legal to set
	 */
	public void setRep_legal(String rep_legal) {
		this.rep_legal = rep_legal;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @param tipo the tipo to set
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
