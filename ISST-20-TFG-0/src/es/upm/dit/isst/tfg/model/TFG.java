package es.upm.dit.isst.tfg.model;

import java.io.Serializable;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class TFG implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String email;
	private String password;
	private String name;
	private String title;
	private int status;
	@Lob
	private byte[] document;
	private int grade;
	@ManyToOne
	private Professor advisor;
	
	public TFG() {
		super();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	@Override
	public String toString() {
		return "TFG [email=" + email + ", password=" + password + ", name=" + name + ", title=" + title + ", status="
				+ status + ", document=" + Arrays.toString(document) + ", grade=" + grade + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(document);
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + grade;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + status;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		TFG other = (TFG) obj;
		if (!Arrays.equals(document, other.document))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (grade != other.grade)
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
		if (status != other.status)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	public TFG(String email, String password, String name, String title, int status, byte[] document, int grade,
			Professor advisor) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.title = title;
		this.status = status;
		this.document = document;
		this.grade = grade;
		this.advisor = advisor;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Professor getAdvisor() {
		return advisor;
	}

	public void setAdvisor(Professor advisor) {
		this.advisor = advisor;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	

}
