package com.self_managment.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tts", catalog = "self_managment")
public class TTS implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private TTSPk pk = new TTSPk();

	@Column(name = "SCHEDULE_ENTERED", nullable = false)
	private String scheduleEntered;

	@Column(name = "SCHEDULE_GONE_OUT", nullable = false)
	private String scheduleGoneOut;

	@Column(name = "BULGING_DATE", nullable = false)
	private Date bulgingDate;

	public TTS() {
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
		TTS other = (TTS) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	public String getScheduleEntered() {
		return scheduleEntered;
	}

	@Transient
	public Agent getAgent() {
		return pk.getAgent();
	}

	@Transient
	public Date getDate() {
		return pk.getDate();
	}

	public String getScheduleGoneOut() {
		return scheduleGoneOut;
	}

	public TTSPk getPk() {
		return pk;
	}

	public Date getBulgingDate() {
		return bulgingDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	public void setScheduleEntered(String scheduleEntered) {
		this.scheduleEntered = scheduleEntered;
	}

	public void setAgent(Agent agent) {
		pk.setAgent(agent);
	}

	public void setDate(Date date) {
		pk.setDate(date);
	}

	public void setScheduleGoneOut(String scheduleGoneOut) {
		this.scheduleGoneOut = scheduleGoneOut;
	}

	public void setPk(TTSPk pk) {
		this.pk = pk;
	}

	public void setBulgingDate(Date bulgingDate) {
		this.bulgingDate = bulgingDate;
	}

}