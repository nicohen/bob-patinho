package com.self_managment.model.entity;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "campaign_metric")
@AssociationOverrides( {
	@AssociationOverride(name = "pk.campaign", joinColumns = @JoinColumn(name = "CAMPAIGN_ID")),
	@AssociationOverride(name = "pk.metric", joinColumns = @JoinColumn(name = "METRIC_ID")) })
public class CampaignMetric implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private CampaignMetricPk pk = new CampaignMetricPk();

    @Column(name = "OPTIM", nullable = false)
    private Double optim;

    @Column(name = "OBJECTIVE", nullable = false)
    private Double objective;

    @Column(name = "MINIMUM", nullable = false)
    private Double minimum;

    @Column(name = "UNSATISFACTORY", nullable = false)
    private Double unsatisfactory;

    public CampaignMetricPk getPk() {
	return pk;
    }

    public void setPk(CampaignMetricPk pk) {
	this.pk = pk;
    }

    @Transient
    public Campaign getCampaign() {
	return pk.getCampaign();
    }

    public void setCampaign(Campaign campaign) {
	pk.setCampaign(campaign);
    }

    @Transient
    public Metric getMetric() {
	return pk.getMetric();
    }

    public void setMetric(Metric metric) {
	pk.setMetric(metric);
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
	final CampaignMetric other = (CampaignMetric) obj;
	if (pk == null) {
	    if (other.pk != null)
		return false;
	} else if (!pk.equals(other.pk))
	    return false;
	return true;
    }

    public Double getOptim() {
	return optim;
    }

    public void setOptim(Double optim) {
	this.optim = optim;
    }

    public Double getObjective() {
	return objective;
    }

    public void setObjective(Double objective) {
	this.objective = objective;
    }

    public Double getMinimum() {
	return minimum;
    }

    public void setMinimum(Double minimum) {
	this.minimum = minimum;
    }

    public Double getUnsatisfactory() {
	return unsatisfactory;
    }

    public void setUnsatisfactory(Double unsatisfactory) {
	this.unsatisfactory = unsatisfactory;
    }

}
