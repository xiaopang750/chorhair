package com.rockstar.o2o.pojo;

// Generated 2015-6-4 11:44:47 by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * MessageGroupMember generated by hbm2java
 */
@Entity
@Table(name = "message_group_member")
public class MessageGroupMember implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long pkGroupMember;
	private Long pkGroup;
	private String membertype;
	private String membername;
	private Long pkMember;
	private String memberphone;
	private String ts;
	private Short dr;

	public MessageGroupMember() {
	}

	public MessageGroupMember(long pkGroupMember) {
		this.pkGroupMember = pkGroupMember;
	}

	public MessageGroupMember(long pkGroupMember, Long pkGroup,
			String membertype, String membername, Long pkMember,
			String memberphone, String ts, Short dr) {
		this.pkGroupMember = pkGroupMember;
		this.pkGroup = pkGroup;
		this.membertype = membertype;
		this.membername = membername;
		this.pkMember = pkMember;
		this.memberphone = memberphone;
		this.ts = ts;
		this.dr = dr;
	}

	@Id
	@GeneratedValue
	@Column(name = "pk_group_member", unique = true, nullable = false)
	public long getPkGroupMember() {
		return this.pkGroupMember;
	}

	public void setPkGroupMember(long pkGroupMember) {
		this.pkGroupMember = pkGroupMember;
	}

	@Column(name = "pk_group")
	public Long getPkGroup() {
		return this.pkGroup;
	}

	public void setPkGroup(Long pkGroup) {
		this.pkGroup = pkGroup;
	}

	@Column(name = "membertype", length = 50)
	public String getMembertype() {
		return this.membertype;
	}

	public void setMembertype(String membertype) {
		this.membertype = membertype;
	}

	@Column(name = "membername", length = 50)
	public String getMembername() {
		return this.membername;
	}

	public void setMembername(String membername) {
		this.membername = membername;
	}

	@Column(name = "pk_member")
	public Long getPkMember() {
		return this.pkMember;
	}

	public void setPkMember(Long pkMember) {
		this.pkMember = pkMember;
	}

	@Column(name = "memberphone", length = 11)
	public String getMemberphone() {
		return this.memberphone;
	}

	public void setMemberphone(String memberphone) {
		this.memberphone = memberphone;
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
