/**
 * Esta clase forma parte del proyecto iNspector de la asigantura ISST del GITST de la UPM (curso 2019/2020)
 * @author Jakub Piatek, Hugo Pascual, Alvaro Basante, Tian Lan y Jaime Castro
 * @version Sprint 3
 */

package es.upm.dit.isst.insp.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Lob;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase que define un establecimiento
 */

@Entity
public class Establecimiento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String cif;
	private String nombre;
	private String direccion;
	private String ciudad;
	private String rep_legal;
	private String tipo;
	private String codigo_postal;
	
	@Temporal(TemporalType.DATE)
	private Date proxima_inspeccion; 
	
	@Lob
	private byte[] imagen;
	
	//relaciones definidas en el modelo de datos
	@OneToMany(mappedBy="establecimiento_inspeccion", fetch = FetchType.EAGER)
	private List <Inspeccion> inspecciones_realizadas_rest;
	
	@OneToMany (mappedBy="establecimiento_incidencia", fetch= FetchType.EAGER)
	private Set <Incidencia> incidencias;
	
	@ManyToMany (mappedBy = "establecimientos_inspeccionados")
	private List <Inspector> inspectores_realizado_inspecciones;
	
	/**
	 * Constructor de la clase. 
	 * Al crear un establecimiento, automáticamente se establece su fecha de proxima inspeccion 
	 * para dentro de un anyo desde su creacion	
	 */
	public Establecimiento() {
		Date fecha = new Date();
		int aux = fecha.getYear()+1;
		fecha.setYear(aux);
		this.proxima_inspeccion=fecha;
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

	
	public byte[] getImagen(){
		return this.imagen;
	}
	
	public void setImagen(byte[] imagen){
		this.imagen = imagen;
	}

	
	public String getCif() {
		return cif;
	}

	
	public void setCif(String cif) {
		this.cif = cif;
	}

	
	public String getNombre() {
		return nombre;
	}

	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public String getDireccion() {
		return direccion;
	}

	
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	
	public String getCiudad() {
		return ciudad;
	}

	
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	
	public String getRep_legal() {
		return rep_legal;
	}

	
	public void setRep_legal(String rep_legal) {
		this.rep_legal = rep_legal;
	}

	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	
	public Date getProxima_inspeccion() {
		return proxima_inspeccion;
	}
	
	
	public void setProxima_inspeccion(Date proxima_inspeccion) {
		this.proxima_inspeccion = proxima_inspeccion;
	}
	
	
	public String getCodigo_postal() {
		return codigo_postal;
	}
	
	
	public void setCodigo_postal(String codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	

}
