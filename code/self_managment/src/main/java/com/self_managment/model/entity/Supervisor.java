package com.self_managment.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "supervisor", catalog = "self_managment", uniqueConstraints = {
		@UniqueConstraint(columnNames = "SUPERVISOR_ID"),
		@UniqueConstraint(columnNames = "DNI") })
public class Supervisor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @Column(name = "SUPERVISOR_ID", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "DNI", unique = true, nullable = false)
	private Long dni;

	@Column(name = "NAME", nullable = false, length = 30)
	private String name;

	@Column(name = "SURNAME", nullable = false, length = 30)
	private String surname;
	
	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "CAMPAIGN_ID")
	private Campaign campaign;
	
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supervisor")
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    private List<Agent> agents;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Long getDni() {
		return dni;
	}
	
	public List<Agent> getAgents() {
		return agents;
    }
	
	public void setDni(Long dni) {
		this.dni = dni;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public Campaign getCampaign() {
		return campaign;
	}
	
	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
	public void setSupervisors(List<Agent> agents) {
		this.agents = agents;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Supervisor)) {
			return false;
		}
		Supervisor other = (Supervisor) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	@Override
    public String toString() {
		if(this.id==null)
			return "";
		return this.id.toString() + " - " + this.name;
    }

}
