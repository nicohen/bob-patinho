package com.self_managment.model.entity;

import javax.persistence.ManyToOne;

public class CampaignMetricPk implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @ManyToOne
    private Campaign campaign;

    @ManyToOne
    private Metric metric;

    public Campaign getCampaign() {
	return campaign;
    }

    public void setCampaign(Campaign campaign) {
	this.campaign = campaign;
    }

    public Metric getMetric() {
	return metric;
    }

    public void setMetric(Metric metric) {
	this.metric = metric;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result
		+ ((campaign == null) ? 0 : campaign.hashCode());
	result = prime * result + ((metric == null) ? 0 : metric.hashCode());
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
	final CampaignMetricPk other = (CampaignMetricPk) obj;
	if (campaign == null) {
	    if (other.campaign != null)
		return false;
	} else if (!campaign.equals(other.campaign))
	    return false;
	if (metric == null) {
	    if (other.metric != null)
		return false;
	} else if (!metric.equals(other.metric))
	    return false;
	return true;
    }

}
