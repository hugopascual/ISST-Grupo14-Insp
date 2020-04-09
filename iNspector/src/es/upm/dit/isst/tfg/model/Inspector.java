package es.upm.dit.isst.tfg.model;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Inspector implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id; 
	private String nombre;
	private String apellido_1;
	private String apellido_2;
	private String usuario;
	private String password;
	@Id
	private String email;
	
	@OneToMany (mappedBy = "inspector_realiza_inspeccion", fetch = FetchType.EAGER)
	private List <Inspeccion> inspecciones_realizadas_inspector;
	
	
	@ManyToMany
	@JoinTable (
			name="Establecimientos_inspectores",
			joinColumns = @JoinColumn(name = "email"),
			inverseJoinColumns = @JoinColumn(name = "cif"))
	private List <Establecimiento> establecimientos_inspeccionados;
	
	@Lob
	private byte[] imagen;
	
	public Inspector() {
		super();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellido_1 == null) ? 0 : apellido_1.hashCode());
		result = prime * result + ((apellido_2 == null) ? 0 : apellido_2.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + id;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}
	
	@Override
	public String toString() {
		return "Inspector [id=" + id + ", nombre=" + nombre + ", apellido_1=" + apellido_1 + ", apellido_2="
				+ apellido_2 + ", usuario=" + usuario + ", password=" + password + ", email=" + email + "]";
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inspector other = (Inspector) obj;
		if (apellido_1 == null) {
			if (other.apellido_1 != null)
				return false;
		} else if (!apellido_1.equals(other.apellido_1))
			return false;
		if (apellido_2 == null) {
			if (other.apellido_2 != null)
				return false;
		} else if (!apellido_2.equals(other.apellido_2))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id != other.id)
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	public byte[] getImagen(){
		return this.imagen;
	}
	
	public void setImagen(byte[] imagen){
		this.imagen = imagen;
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
	 * @return the apellido_1
	 */
	public String getApellido_1() {
		return apellido_1;
	}
	/**
	 * @param apellido_1 the apellido_1 to set
	 */
	public void setApellido_1(String apellido_1) {
		this.apellido_1 = apellido_1;
	}
	/**
	 * @return the apellido_2
	 */
	public String getApellido_2() {
		return apellido_2;
	}
	/**
	 * @param apellido_2 the apellido_2 to set
	 */
	public void setApellido_2(String apellido_2) {
		this.apellido_2 = apellido_2;
	}
	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}
	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
