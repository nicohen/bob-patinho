package com.self_managment.model.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "CAMPAIGN_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "CAMPAIGN_CODE", unique = true, nullable = false, length = 10)
    private String code;

    @Column(name = "CAMPAIGN_NAME", unique = true, nullable = false, length = 20)
    private String name;

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

}
