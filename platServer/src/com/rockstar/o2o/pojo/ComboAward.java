package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ComboAward entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "combo_award")
public class ComboAward implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkComboAward;
	private Long pkCombo;
	private Long pkCustomeraward;
	private String combofrom;
	private String awardname;
	private String awardvalue;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public ComboAward() {
	}

	/** full constructor */
	public ComboAward(Long pkCombo, Long pkCustomeraward, String combofrom,
			String awardname, String awardvalue, String ts, Short dr) {
		this.pkCombo = pkCombo;
		this.pkCustomeraward = pkCustomeraward;
		this.combofrom = combofrom;
		this.awardname = awardname;
		this.awardvalue = awardvalue;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_combo_award", unique = true, nullable = false)
	public Long getPkComboAward() {
		return this.pkComboAward;
	}

	public void setPkComboAward(Long pkComboAward) {
		this.pkComboAward = pkComboAward;
	}

	@Column(name = "pk_combo")
	public Long getPkCombo() {
		return this.pkCombo;
	}

	public void setPkCombo(Long pkCombo) {
		this.pkCombo = pkCombo;
	}

	@Column(name = "pk_customeraward")
	public Long getPkCustomeraward() {
		return this.pkCustomeraward;
	}

	public void setPkCustomeraward(Long pkCustomeraward) {
		this.pkCustomeraward = pkCustomeraward;
	}

	@Column(name = "combofrom", length = 30)
	public String getCombofrom() {
		return this.combofrom;
	}

	public void setCombofrom(String combofrom) {
		this.combofrom = combofrom;
	}

	@Column(name = "awardname", length = 200)
	public String getAwardname() {
		return this.awardname;
	}

	public void setAwardname(String awardname) {
		this.awardname = awardname;
	}

	@Column(name = "awardvalue", length = 200)
	public String getAwardvalue() {
		return this.awardvalue;
	}

	public void setAwardvalue(String awardvalue) {
		this.awardvalue = awardvalue;
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