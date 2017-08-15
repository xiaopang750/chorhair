package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserGroup entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user_group")
public class UserGroup implements java.io.Serializable {

	// Fields

	private Long pkGroup;
	private String groupcode;
	private String groupname;
	private String groupnote;
	private String groupobject;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public UserGroup() {
	}

	/** full constructor */
	public UserGroup(String groupcode, String groupname, String groupnote,String groupobject,
			String ts, Short dr) {
		this.groupcode = groupcode;
		this.groupname = groupname;
		this.groupnote = groupnote;
		this.groupobject = groupobject;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_group", unique = true, nullable = false)
	public Long getPkGroup() {
		return this.pkGroup;
	}

	public void setPkGroup(Long pkGroup) {
		this.pkGroup = pkGroup;
	}

	@Column(name = "groupcode", length = 30)
	public String getGroupcode() {
		return this.groupcode;
	}

	public void setGroupcode(String groupcode) {
		this.groupcode = groupcode;
	}

	@Column(name = "groupname", length = 30)
	public String getGroupname() {
		return this.groupname;
	}

	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}

	@Column(name = "groupnote")
	public String getGroupnote() {
		return this.groupnote;
	}

	public void setGroupnote(String groupnote) {
		this.groupnote = groupnote;
	}
	@Column(name = "groupobject")
	public String getGroupobject() {
		return groupobject;
	}

	public void setGroupobject(String groupobject) {
		this.groupobject = groupobject;
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