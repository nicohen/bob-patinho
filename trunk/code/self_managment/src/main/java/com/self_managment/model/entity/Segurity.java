package com.self_managment.model.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "segurity", catalog = "self_managment", uniqueConstraints = { @UniqueConstraint(columnNames = "USER") })
public class Segurity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	// @GeneratedValue(strategy = IDENTITY)
	@Column(name = "CLAVE", unique = false, nullable = false)
	private String clave;

	@Column(name = "USER", unique = true, nullable = false)
	private String user;

	
	public Segurity() {
		super();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Segurity other = (Segurity) obj;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	

	public String getUser() {
		return user;
	}

	public String getClave() {
		return clave;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	

	public void setUser(String user) {
		this.user = user;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	
}