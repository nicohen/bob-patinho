package com.self_managment.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "summary", catalog = "self_managment")
public class Summary implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private SummaryPk pk = new SummaryPk();

	@Column(name = "QUANTITY_OF_CALLS", nullable = false)
	private Integer quantityOfCalls;

	@Column(name = "IN_CALL", nullable = false)
	private Integer inCall;

	@Column(name = "TIME_IN_WAIT", nullable = false)
	private Integer timeInWait;
	
	@Column(name = "TRANSFERRED_CALLS", nullable = false)
	private Integer transferredCalls;
	
	@Column(name = "LOGGEADO", nullable = false)
	private Integer loggeado;
	
	@Column(name = "READY_FOR_CALL", nullable = false)
	private Integer readyForCall;
	
	@Column(name = "AFTER_FOR_CALL", nullable = false)
	private Integer afterForCall;

	public Summary() {
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
		Summary other = (Summary) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	public Integer getQuantityOfCalls() {
		return quantityOfCalls;
	}

	@Transient
	public Agent getAgent() {
		return pk.getAgent();
	}

	@Transient
	public Date getDate() {
		return pk.getDate();
	}

	public Integer getInCall() {
		return inCall;
	}

	public SummaryPk getPk() {
		return pk;
	}

	public Integer getTimeInWait() {
		return timeInWait;
	}
	
	public Integer getTransferredCalls() {
		return transferredCalls;
	}
	
	public Integer getLoggeado() {
		return loggeado;
	}
	
	public Integer getReadyForCall() {
		return readyForCall;
	}
	
	public Integer getAfterForCall() {
		return afterForCall;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	public void setQuantityOfCalls(Integer quantityOfCalls) {
		this.quantityOfCalls = quantityOfCalls;
	}

	public void setAgent(Agent agent) {
		pk.setAgent(agent);
	}

	public void setDate(Date date) {
		pk.setDate(date);
	}

	public void setInCall(Integer incall) {
		this.inCall = incall;
	}
	
	public void setTimeInWait(Integer timeInWait) {
		this.timeInWait = timeInWait;
	}

	public void setPk(SummaryPk pk) {
		this.pk = pk;
	}

	public void setTransferredCalls(Integer transferredCalls) {
		this.transferredCalls = transferredCalls;
	}
	
	public void setLoggeado(Integer loggeado) {
		this.loggeado = loggeado;
	}
	
	public void setReadyForCall(Integer readyForCall) {
		this.readyForCall = readyForCall;
	}
	
	public void setAfterForCall(Integer afterForCall) {
		this.afterForCall = afterForCall;
	}
	
	

}