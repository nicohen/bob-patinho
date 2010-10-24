package com.self_managment.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class QAPk implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "DOCKET", nullable = false)
    private Agent agent;

    @Column(name = "DATE", nullable = false)
    private Date date;

    @Override
    public boolean equals(Object obj) {
	if (this == obj)
	    return true;
	if (obj == null)
	    return false;
	if (getClass() != obj.getClass())
	    return false;
	QAPk other = (QAPk) obj;
	if (agent == null) {
	    if (other.agent != null)
		return false;
	} else if (!agent.equals(other.agent))
	    return false;
	if (date == null) {
	    if (other.date != null)
		return false;
	} else if (!date.equals(other.date))
	    return false;
	return true;
    }

    public Agent getAgent() {
	return agent;
    }

    public Date getDate() {
	return date;
    }

    @Override
    public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((agent == null) ? 0 : agent.hashCode());
	result = prime * result + ((date == null) ? 0 : date.hashCode());
	return result;
    }

    public void setAgent(Agent agent) {
	this.agent = agent;
    }

    public void setDate(Date date) {
	this.date = date;
    }

}
