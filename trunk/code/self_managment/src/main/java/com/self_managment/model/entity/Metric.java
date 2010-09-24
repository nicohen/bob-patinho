package com.self_managment.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "metric", catalog = "self_managment", uniqueConstraints = { @UniqueConstraint(columnNames = "METRIC_CODE") })
public class Metric implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    // @GeneratedValue(strategy = IDENTITY)
    @Column(name = "METRIC_ID", unique = true, nullable = false)
    private Integer id;

    @Column(name = "METRIC_CODE", unique = true, nullable = false)
    private String code;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pk.metric")
    private List<CampaignMetric> campaignMetric;

    public Metric() {
	super();
    }

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

    public List<CampaignMetric> getCampaignMetric() {
	return campaignMetric;
    }

    public void setCampaignMetric(List<CampaignMetric> campaignMetric) {
	this.campaignMetric = campaignMetric;
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
	final Metric other = (Metric) obj;
	if (id == null) {
	    if (other.id != null)
		return false;
	} else if (!id.equals(other.id))
	    return false;
	return true;
    }

    @Override
    public String toString() {
	return code;
    }

}
