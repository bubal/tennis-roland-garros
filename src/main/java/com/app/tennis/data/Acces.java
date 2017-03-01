package com.app.tennis.data;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="acces")
@NamedQueries({
	@NamedQuery(name = "Acces.findAll", query = "select p from Acces p"),
	@NamedQuery(name = "Acces.findObj", query = "select p from Acces p where p.login = :login")
})
public class Acces {

	@Id 
	@Column(name = "id_acces")
	@GeneratedValue
	private int id;
	private String login;
	private String password;

	@Transient
	private String error;
	@Transient
	private boolean exist;
	@Transient
	private boolean acces;


	public Acces() {
		super();
	}


	public Acces(String login) {
		super();
		this.id=0;
		this.acces=false;
		this.exist=false;
		this.error=null;
		this.login = login;
	}


	public boolean isAcces() {
		return acces;
	}

	public boolean isAcces(String password) {
		this.acces=false;
		if (exist && password.equals(this.password) && login!=null ){
			this.acces = true;
		}
		return acces;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public boolean isExist() {
		return exist;
	}

	public void setExist(boolean exist) {
		this.exist = exist;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Acces [id=" + id + ", login=" + login + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
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
		Acces other = (Acces) obj;
		if (id != other.id)
			return false;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}


}
