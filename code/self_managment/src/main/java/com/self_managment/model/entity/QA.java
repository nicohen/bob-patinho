package com.self_managment.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "qa", catalog = "self_managment")
public class QA implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private QAPk pk = new QAPk();

	@Column(name = "EVALUATIONS_QUANTITY", nullable = false)
	private Integer evaluationsQuantity;

	@Column(name = "POSIBLE_POINTS_QUANTITY", nullable = false)
	private Integer posiblePointsQuantity;

	@Column(name = "ACHIEVED_POINTS_QUANTITY", nullable = false)
	private Integer achievedPointsQuantity;

	public QA() {
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
		QA other = (QA) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

	public Integer getAchievedPointsQuantity() {
		return achievedPointsQuantity;
	}

	@Transient
	public Agent getAgent() {
		return pk.getAgent();
	}

	@Transient
	public Date getDate() {
		return pk.getDate();
	}

	public Integer getEvaluationsQuantity() {
		return evaluationsQuantity;
	}

	public QAPk getPk() {
		return pk;
	}

	public Integer getPosiblePointsQuantity() {
		return posiblePointsQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
		return result;
	}

	public void setAchievedPointsQuantity(Integer achievedPointsQuantity) {
		this.achievedPointsQuantity = achievedPointsQuantity;
	}

	public void setAgent(Agent agent) {
		pk.setAgent(agent);
	}

	public void setDate(Date date) {
		pk.setDate(date);
	}

	public void setEvaluationsQuantity(Integer evaluationsQuantity) {
		this.evaluationsQuantity = evaluationsQuantity;
	}

	public void setPk(QAPk pk) {
		this.pk = pk;
	}

	public void setPosiblePointsQuantity(Integer posiblePointsQuantity) {
		this.posiblePointsQuantity = posiblePointsQuantity;
	}

}
