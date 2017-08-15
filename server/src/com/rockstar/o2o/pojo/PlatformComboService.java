package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * PlatformComboService entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "platform_combo_service")
public class PlatformComboService implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkComboService;
	private Long pkCombo;
	private Long pkService;
	private String servicename;
	private Double serviceprice;
	private Integer servicecount;
	private String servicecontent;
	private String servicenote;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public PlatformComboService() {
	}

	/** full constructor */
	public PlatformComboService(Long pkCombo, Long pkService,
			String servicename, Double serviceprice, Integer servicecount,
			String servicecontent, String servicenote, String ts, Short dr) {
		this.pkCombo = pkCombo;
		this.pkService = pkService;
		this.servicename = servicename;
		this.serviceprice = serviceprice;
		this.servicecount = servicecount;
		this.servicecontent = servicecontent;
		this.servicenote = servicenote;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_combo_service", unique = true, nullable = false)
	public Long getPkComboService() {
		return this.pkComboService;
	}

	public void setPkComboService(Long pkComboService) {
		this.pkComboService = pkComboService;
	}

	@Column(name = "pk_combo")
	public Long getPkCombo() {
		return this.pkCombo;
	}

	public void setPkCombo(Long pkCombo) {
		this.pkCombo = pkCombo;
	}

	@Column(name = "pk_service")
	public Long getPkService() {
		return this.pkService;
	}

	public void setPkService(Long pkService) {
		this.pkService = pkService;
	}

	@Column(name = "servicename", length = 100)
	public String getServicename() {
		return this.servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	@Column(name = "serviceprice", precision = 20)
	public Double getServiceprice() {
		return this.serviceprice;
	}

	public void setServiceprice(Double serviceprice) {
		this.serviceprice = serviceprice;
	}

	@Column(name = "servicecount")
	public Integer getServicecount() {
		return this.servicecount;
	}

	public void setServicecount(Integer servicecount) {
		this.servicecount = servicecount;
	}

	@Column(name = "servicecontent")
	public String getServicecontent() {
		return this.servicecontent;
	}

	public void setServicecontent(String servicecontent) {
		this.servicecontent = servicecontent;
	}

	@Column(name = "servicenote")
	public String getServicenote() {
		return this.servicenote;
	}

	public void setServicenote(String servicenote) {
		this.servicenote = servicenote;
	}

	@Column(name = "ts", length = 19)
	public String getTs() {
		return this.ts;
	}

	public void setTs(String ts) {
		this.ts = ts;
	}

	@Column(name = "dr")
	public Short getDr() {
		return this.dr;
	}

	public void setDr(Short dr) {
		this.dr = dr;
	}

}