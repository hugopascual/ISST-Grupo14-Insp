package es.upm.dit.isst.tfg.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Professor implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	private String email;
	private String password;
	private String name;
	
	public Professor() {
		super();
	}
	
	@OneToMany
	private Collection<TFG> advisedTFGs;

	@Override
	public String toString() {
		return "Professor [email=" + email + ", password=" + password + ", name=" + name + ", advisedTFGs="
				+ advisedTFGs + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((advisedTFGs == null) ? 0 : advisedTFGs.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		Professor other = (Professor) obj;
		if (advisedTFGs == null) {
			if (other.advisedTFGs != null)
				return false;
		} else if (!advisedTFGs.equals(other.advisedTFGs))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	/**
	 * @param email
	 * @param password
	 * @param name
	 * @param advisedTFGs
	 */
	public Professor(String email, String password, String name, ArrayList<TFG> advisedTFGs) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.advisedTFGs = advisedTFGs;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the advisedTFGs
	 */
	public Collection<TFG> getAdvisedTFGs() {
		return advisedTFGs;
	}

	/**
	 * @param advisedTFGs the advisedTFGs to set
	 */
	public void setAdvisedTFGs(ArrayList<TFG> advisedTFGs) {
		this.advisedTFGs = advisedTFGs;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	
}
