package com.self_managment.model.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "qa", catalog = "self_managment")
public class QA implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "QA_ID", unique = true, nullable = false)
	private Integer id;

	@Column(name = "DATE", nullable = false)
	private Date date;

	@Column(name = "EVALUATIONS_QUANTITY", nullable = false)
	private Integer evaluationsQuantity;

	@Column(name = "POSIBLE_POINTS_QUANTITY", nullable = false)
	private Integer posiblePointsQuantity;

	@Column(name = "ACHIEVED_POINTS_QUANTITY", nullable = false)
	private Integer achievedPointsQuantity;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "DOCKET", nullable = false)
	private Agent agent;

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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getAchievedPointsQuantity() {
		return achievedPointsQuantity;
	}

	public Agent getAgent() {
		return agent;
	}

	public Date getDate() {
		return date;
	}

	public Integer getEvaluationsQuantity() {
		return evaluationsQuantity;
	}

	public Integer getId() {
		return id;
	}

	public Integer getPosiblePointsQuantity() {
		return posiblePointsQuantity;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public void setAchievedPointsQuantity(Integer achievedPointsQuantity) {
		this.achievedPointsQuantity = achievedPointsQuantity;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setEvaluationsQuantity(Integer evaluationsQuantity) {
		this.evaluationsQuantity = evaluationsQuantity;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPosiblePointsQuantity(Integer posiblePointsQuantity) {
		this.posiblePointsQuantity = posiblePointsQuantity;
	}

}
