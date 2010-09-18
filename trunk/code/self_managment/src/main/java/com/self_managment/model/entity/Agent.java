package com.self_managment.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "agent", catalog = "self_managment", uniqueConstraints = { @UniqueConstraint(columnNames = "DNI") })
public class Agent implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    // @GeneratedValue(strategy = IDENTITY)
    @Column(name = "DOCKET", unique = true, nullable = false)
    private Integer docket;

    @Column(name = "DNI", unique = true, nullable = false)
    private Long dni;

    @Column(name = "NAME", nullable = false, length = 30)
    private String name;

    @Column(name = "SURNAME", nullable = false, length = 30)
    private String surname;

    @Column(name = "STATUS", nullable = false, length = 10)
    private String status;

    @Column(name = "ENTRY_DATE", nullable = false)
    private Date entryDate;

    public Agent() {
	super();
    }

    public Integer getDocket() {
	return docket;
    }

    public void setDocket(Integer docket) {
	this.docket = docket;
    }

    public Long getDni() {
	return dni;
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

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Date getEntryDate() {
	return entryDate;
    }

    public void setEntryDate(Date entryDate) {
	this.entryDate = entryDate;
    }

}
