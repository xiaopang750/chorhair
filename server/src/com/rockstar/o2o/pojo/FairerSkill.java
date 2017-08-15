package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * FairerSkill entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "fairer_skill")
public class FairerSkill implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkSkill;
	private Long pkFairer;
	private String skillname;
	private String skillrank;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public FairerSkill() {
	}

	/** full constructor */
	public FairerSkill(Long pkFairer, String skillname, String skillrank,
			String ts, Short dr) {
		this.pkFairer = pkFairer;
		this.skillname = skillname;
		this.skillrank = skillrank;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_skill", unique = true, nullable = false)
	public Long getPkSkill() {
		return this.pkSkill;
	}

	public void setPkSkill(Long pkSkill) {
		this.pkSkill = pkSkill;
	}

	@Column(name = "pk_fairer")
	public Long getPkFairer() {
		return this.pkFairer;
	}

	public void setPkFairer(Long pkFairer) {
		this.pkFairer = pkFairer;
	}

	@Column(name = "skillname", length = 100)
	public String getSkillname() {
		return this.skillname;
	}

	public void setSkillname(String skillname) {
		this.skillname = skillname;
	}

	@Column(name = "skillrank", length = 100)
	public String getSkillrank() {
		return this.skillrank;
	}

	public void setSkillrank(String skillrank) {
		this.skillrank = skillrank;
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