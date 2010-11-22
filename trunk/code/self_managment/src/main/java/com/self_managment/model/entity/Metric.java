package com.self_managment.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.jfree.data.statistics.Statistics;
import org.springframework.context.ApplicationContext;

import com.self_managment.model.metric.MetricStrategy;
import com.self_managment.util.AppContext;
import com.self_managment.util.DateUtils;

@Entity
@Table(name = "metric", catalog = "self_managment", uniqueConstraints = { @UniqueConstraint(columnNames = "METRIC_CODE") })
public class Metric implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
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

    @Column(name = "OPTIM_MIN_VALUE", unique = false, nullable = false)
    private Double optimMinValue;

    @Column(name = "OPTIM_MAX_VALUE", unique = false, nullable = false)
    private Double optimMaxValue;
    
    @Column(name = "OBJETIVE_MIN_VALUE", unique = false, nullable = false)
    private Double objetiveMinValue;

    @Column(name = "OBJETIVE_MAX_VALUE", unique = false, nullable = false)
    private Double objetiveMaxValue;
    
    @Column(name = "MINIMUM_MIN_VALUE", unique = false, nullable = false)
    private Double minimumMinValue;

    @Column(name = "MINIMUM_MAX_VALUE", unique = false, nullable = false)
    private Double minimumMaxValue;
    
    @Column(name = "UNSATISFACTORY_MIN_VALUE", unique = false, nullable = false)
    private Double unsatisfactoryMinValue;

    @Column(name = "UNSATISFACTORY_MAX_VALUE", unique = false, nullable = false)
    private Double unsatisfactoryMaxValue;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pk.metric")
    private List<CampaignMetric> campaignMetric;

    public Metric() {
	super();
    }

    @Transient
    public Number execute(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	ApplicationContext appContext = AppContext.getApplicationContext();

	MetricStrategy metric = (MetricStrategy) appContext.getBean(code);

	return metric.execute(campaignId, supervisorId, docket, dateFrom,
		dateTo);
    }

    @Transient
    public Boolean isAccumulated() {
	if ("%".equals(unit) || "AVG_TALK_TM".equals(code)) {
	    return Boolean.FALSE;
	}
	return Boolean.TRUE;
    }

    @Transient
    public Number executeProjected(Integer campaignId, Integer supervisorId,
	    Integer docket, Date dateFrom, Date dateTo) {
	if (com.self_managment.util.DateUtils.isOldPeriod(dateFrom))
	    return execute(campaignId, supervisorId, docket, dateFrom, dateTo);

	Date today = new Date();

	if (dateFrom.after(today)) // future
	    return 0;

	int dayCount = com.self_managment.util.DateUtils.getDay(today)
		- com.self_managment.util.DateUtils.getDay(dateFrom) + 1;

	Integer[] xData = new Integer[dayCount];
	Number[] yData = new Number[dayCount];
	Date firstDay = DateUtils.getFirstDay(today);
	while (dateFrom.before(today)) {
	    int day = com.self_managment.util.DateUtils.getDay(dateFrom);
	    xData[day - 1] = day;
	    yData[day - 1] = execute(campaignId, supervisorId, docket, firstDay,
		    dateFrom).doubleValue();

	    dateFrom = org.apache.commons.lang.time.DateUtils.addDays(dateFrom,
		    1);
	}

	double[] linearFit = Statistics.getLinearFit(xData, yData);

	// Y = aX + b
	return linearFit[1] * com.self_managment.util.DateUtils.getDay(dateTo)
		+ linearFit[0];
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

    public void setOptimMinValue(Double optimMinValue) {
	this.optimMinValue = optimMinValue;
    }

    public Double getOptimMinValue() {
	return optimMinValue;
    }
    
    public void setOptimMaxValue(Double optimMaxValue) {
	this.optimMaxValue = optimMaxValue;
    }

    public Double getOptimMaxValue() {
	return optimMaxValue;
    }
    
    public void setObjetiveMinValue(Double objetiveMinValue) {
	this.objetiveMinValue = objetiveMinValue;
    }
    
    public Double getObjetiveMinValue() {
	return objetiveMinValue;
    }

    public void setObjetiveMaxValue(Double objetiveMaxValue) {
	this.objetiveMaxValue = objetiveMaxValue;
    }
    
    public Double getObjetiveMaxValue() {
	return objetiveMaxValue;
    }

    public Double getMinimumMinValue() {
	return minimumMinValue;
    }
    
	public void setMinimumMinValue(Double minimumMinValue) {
	this.minimumMinValue = minimumMinValue;
    }
	
    public Double getMinimumMaxValue() {
	return minimumMaxValue;
    }
    
	public void setMinimumMaxValue(Double minimumMaxValue) {
	this.minimumMaxValue = minimumMaxValue;
    }

    public Double getUnsatisfactoryMinValue() {
	return unsatisfactoryMinValue;
    }

    public void setUnsatisfactoryMinValue(Double unsatisfactoryMinValue) {
	this.unsatisfactoryMinValue = unsatisfactoryMinValue;
    }
    
    public Double getUnsatisfactoryMaxValue() {
	return unsatisfactoryMaxValue;
    }

    public void setUnsatisfactoryMaxValue(Double unsatisfactoryMaxValue) {
	this.unsatisfactoryMaxValue = unsatisfactoryMaxValue;
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
