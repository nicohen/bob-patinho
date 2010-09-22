package com.self_managment.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "campaign", catalog = "self_managment", uniqueConstraints = {
	@UniqueConstraint(columnNames = "CAMPAIGN_CODE"),
	@UniqueConstraint(columnNames = "CAMPAIGN_NAME") })
public class Campaign implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    public enum CampaignType {
	INBOUND, OUTBOUND;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CAMPAIGN_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "CAMPAIGN_CODE", unique = true, nullable = false, length = 10)
    private String code;

    @Column(name = "CAMPAIGN_NAME", unique = true, nullable = false, length = 20)
    private String name;

    @Column(name = "TYPE", nullable = false)
    @Enumerated(EnumType.STRING)
    private CampaignType type;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "campaign_agent", joinColumns = @JoinColumn(name = "CAMPAIGN_ID"), inverseJoinColumns = @JoinColumn(name = "AGENT_ID"))
    private List<Agent> agents;

    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name = "campaign_metric", joinColumns = @JoinColumn(name = "CAMPAIGN_ID"), inverseJoinColumns = @JoinColumn(name = "METRIC_ID"))
    private List<Metric> metrics;

    public Integer getId() {
	return id;
    }

    public void setId(Integer id) {
	this.id = id;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public List<Agent> getAgents() {
	return agents;
    }

    public void setAgents(List<Agent> agents) {
	this.agents = agents;
    }

    public List<Metric> getMetrics() {
	return metrics;
    }

    public void setMetrics(List<Metric> metrics) {
	this.metrics = metrics;
    }

    public CampaignType getType() {
	return type;
    }

    public void setType(CampaignType type) {
	this.type = type;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((id == null) ? 0 : id.hashCode());
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
	final Campaign other = (Campaign) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

}
