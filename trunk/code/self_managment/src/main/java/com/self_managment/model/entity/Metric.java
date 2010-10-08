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
    
    @Column(name = "METRIC_UNIT", unique = false, nullable = false)
    private String unit;
    
    @Column(name = "OPTIM_SIGN", unique = false, nullable = false)
    private String optimSign;
    
    @Column(name = "OBJETIVE_SIGN", unique = false, nullable = false)
    private String objetiveSign;
    
    @Column(name = "MINIMUM_SIGN", unique = false, nullable = false)
    private String minimumSign;
    
    @Column(name = "UNSATISFACTORY_SIGN", unique = false, nullable = false)
    private String unsatisfactorySign;
    
    @Column(name = "MIN_VALUE", unique = false, nullable = false)
    private Double minValue;
    
    @Column(name = "MAX_VALUE", unique = false, nullable = false)
    private Double maxValue;

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
    
    public String getUnit() {
	return unit;
    }

    public void setUnit(String unit) {
	this.unit = unit;
    }
    
    public String getOptimSign() {
	return optimSign;
    }

    public void setOptimSign(String optimSign) {
	this.optimSign = optimSign;
    }

    public String getObjetiveSign() {
	return objetiveSign;
    }

    public void setObjetiveSign(String objetiveSign) {
	this.objetiveSign = objetiveSign;
    }
    
    public String getMinimumSign() {
	return minimumSign;
    }

    public void setMinimumSign(String minimumSign) {
	this.minimumSign = minimumSign;
    }
    
    public String getUnsatisfactorySign() {
	return unsatisfactorySign;
    }

    public void setUnsatisfactorySign(String unsatisfactorySign) {
	this.unsatisfactorySign = unsatisfactorySign;
    }
    
	public void setMinValue(Double minValue) {
		this.minValue = minValue;
	}

	public Double getMinValue() {
		return minValue;
	}
	
	public void setMaxValue(Double maxValue) {
		this.maxValue = maxValue;
	}

	public Double getMaxValue() {
		return maxValue;
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