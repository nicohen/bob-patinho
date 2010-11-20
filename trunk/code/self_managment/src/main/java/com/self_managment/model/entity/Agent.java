package com.self_managment.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
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

    @Column(name = "WORKING_DAY", nullable = false, length = 30)
    private String workingDay;

    @Column(name = "GROSS_SALARY", nullable = false)
    private Double grossSalary;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "SUPERVISOR_ID", nullable = false)
    private Supervisor supervisor;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "CAMPAIGN_ID")
    // @JoinTable(name = "campaign_agent", joinColumns = @JoinColumn(name =
    // "AGENT_ID"), inverseJoinColumns = @JoinColumn(name = "CAMPAIGN_ID"))
    private Campaign campaign;

    public Agent() {
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
	Agent other = (Agent) obj;
	if (docket == null) {
	    if (other.docket != null)
		return false;
	} else if (!docket.equals(other.docket))
	    return false;
	return true;
    }

    public Campaign getCampaign() {
	return campaign;
    }

    public Long getDni() {
	return dni;
    }

    public Integer getDocket() {
	return docket;
    }

    public Date getEntryDate() {
	return entryDate;
    }

    public Double getGrossSalary() {
	return grossSalary;
    }

    public String getName() {
	return name;
    }

    public String getStatus() {
	return status;
    }

    public Supervisor getSupervisor() {
	return supervisor;
    }

    public String getSurname() {
	return surname;
    }

    public String getWorkingDay() {
	return workingDay;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((docket == null) ? 0 : docket.hashCode());
	return result;
    }

    public void setCampaign(Campaign campaign) {
	this.campaign = campaign;
    }

    public void setDni(Long dni) {
	this.dni = dni;
    }

    public void setDocket(Integer docket) {
	this.docket = docket;
    }

    public void setEntryDate(Date entryDate) {
	this.entryDate = entryDate;
    }

    public void setGrossSalary(Double grossSalary) {
	this.grossSalary = grossSalary;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public void setSupervisor(Supervisor supervisor) {
	this.supervisor = supervisor;
    }

    public void setSurname(String surname) {
	this.surname = surname;
    }

    public void setWorkingDay(String workingDay) {
	this.workingDay = workingDay;
    }

    @Transient
    public Double getHourValue(Date dateFrom, Date dateTo) {
	return campaign != null ? campaign.getHourValue(this.getDocket(), dateFrom, dateTo)
		: 0d;

    }
    
    public int getWorkDayHours()
    {
		int workDayHours = 9;
		if((this.getWorkingDay()!=null)&&(this.getWorkingDay().equals("PTE")))
			workDayHours = 7;
		return workDayHours;
    }
    
    @Override
    public String toString() {
	if (this.docket == null)
	    return "";
	return this.surname + ", " + this.name.toString();
    }

}
