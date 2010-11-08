package com.self_managment.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "campaign", catalog = "self_managment", uniqueConstraints = {
	@UniqueConstraint(columnNames = "CAMPAIGN_ID"),
	@UniqueConstraint(columnNames = "CAMPAIGN_NAME") })
public class Campaign implements java.io.Serializable {
    public enum CampaignType {
	INBOUND, OUTBOUND;
    }

    public Campaign() {
	unsatisfactoryValue = 0.0;
    }

    private static final long serialVersionUID = 1L;

    @Id
    // @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CAMPAIGN_ID", unique = true, nullable = false)
    private Integer id;

    // @Column(name = "CAMPAIGN_CODE", unique = true, nullable = false, length =
    // 10)
    // private String code;

    @Column(name = "CAMPAIGN_NAME", unique = true, nullable = false, length = 20)
    private String name;

    @Column(name = "CAMPAIGN_DESCRIPTION", nullable = true)
    private String description;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private CampaignType type;

    @Column(name = "OPTIM_VALUE", nullable = false)
    private Double optimValue;

    @Column(name = "OBJETIVE_VALUE", nullable = false)
    private Double objetiveValue;

    @Column(name = "MINIMUM_VALUE", nullable = false)
    private Double minimumValue;

    @Column(name = "UNSATISFACTORY_VALUE", nullable = false)
    private Double unsatisfactoryValue;

    @Column(name = "START_DATE", nullable = false)
    private Date startDate;

    @Column(name = "END_DATE", nullable = true)
    private Date endDate;

    // @ManyToMany(cascade = CascadeType.PERSIST)
    // @JoinTable(name = "campaign_agent", joinColumns = @JoinColumn(name =
    // "CAMPAIGN_ID"), inverseJoinColumns = @JoinColumn(name = "AGENT_ID"))
    @OneToMany(mappedBy = "campaign")
    private List<Agent> agents;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "campaign")
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE })
    private List<Supervisor> supervisors;

    // @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pk.campaign")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.campaign", cascade = {
	    CascadeType.PERSIST, CascadeType.MERGE })
    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE,
	    org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    private List<CampaignMetric> campaignMetric;

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	final Campaign other = (Campaign) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    public List<Agent> getAgents() {
	return agents;
    }

    public List<Supervisor> getSupervisors() {
	return supervisors;
    }

    public List<CampaignMetric> getCampaignMetric() {
	return campaignMetric;
    }

    // public String getCode() {
    // return code;
    // }

    public Integer getId() {
	return id;
    }

    public Double getMinimumValue() {
	return minimumValue;
    }

    public String getName() {
	return name;
    }

    public Double getObjetiveValue() {
	return objetiveValue;
    }

    /*
     * public List<Metric> getMetrics() { return metrics; }
     * 
     * public void setMetrics(List<Metric> metrics) { this.metrics = metrics; }
     */

    public Double getOptimValue() {
	return optimValue;
    }

    public CampaignType getType() {
	return type;
    }

    public Double getUnsatisfactoryValue() {
	return unsatisfactoryValue;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
	return result;
    }

    public void setAgents(List<Agent> agents) {
	this.agents = agents;
    }

    public void setSupervisors(List<Supervisor> supervisors) {
	this.supervisors = supervisors;
    }

    public void setCampaignMetric(List<CampaignMetric> campaignMetric) {
	this.campaignMetric = campaignMetric;
    }

    // public void setCode(String code) {
    // this.code = code;
    // }

    public void setId(Integer id) {
	this.id = id;
    }

    public void setMinimumValue(Double minimumValue) {
	this.minimumValue = minimumValue;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public String getDescription() {
	return description;
    }

    public void setObjetiveValue(Double objetiveValue) {
	this.objetiveValue = objetiveValue;
    }

    public void setOptimValue(Double optimValue) {
	this.optimValue = optimValue;
    }

    public void setType(CampaignType type) {
	this.type = type;
    }

    public void setStartDate(Date startDate) {
	this.startDate = startDate;
    }

    public Date getStartDate() {
	return startDate;
    }

    public Date getEndDate() {
	return endDate;
    }

    public void setEndDate(Date endDate) {
	this.endDate = endDate;
    }

    @Override
    public String toString() {
	if (this.id == null)
	    return "";
	return this.id.toString() + " - " + this.name;
    }

    @Transient
    public Double getHourValue(Agent agent, Date dateFrom, Date dateTo) {
	int level = 4;
	for (CampaignMetric cm : campaignMetric) {
	    int currentLevel = cm.getLevelProjected(agent, dateFrom, dateTo);
	    level = currentLevel > level ? level : currentLevel; // get min
	}

	if (level == 3)
	    return optimValue;
	if (level == 2)
	    return objetiveValue;
	if (level == 1)
	    return minimumValue;

	return unsatisfactoryValue;

    }
}
