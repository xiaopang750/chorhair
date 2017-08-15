package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CustomerOrderSpitslot entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "customer_order_spitslot")
public class CustomerOrderSpitslot implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkSpitslot;
	private Long pkOrder;
	private Long pkBeautyrecord;
	private Long spitslotorid;
	private String spitslotor;
	private String spitslottime;
	private String spitslotcontent;
	private Long replymanid;
	private String replyman;
	private String replytime;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public CustomerOrderSpitslot() {
	}

	/** full constructor */
	public CustomerOrderSpitslot(Long pkOrder, Long pkBeautyrecord,
			Long spitslotorid, String spitslotor, String spitslottime,
			String spitslotcontent, Long replymanid, String replyman,
			String replytime, String ts, Short dr) {
		this.pkOrder = pkOrder;
		this.pkBeautyrecord = pkBeautyrecord;
		this.spitslotorid = spitslotorid;
		this.spitslotor = spitslotor;
		this.spitslottime = spitslottime;
		this.spitslotcontent = spitslotcontent;
		this.replymanid = replymanid;
		this.replyman = replyman;
		this.replytime = replytime;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_spitslot", unique = true, nullable = false)
	public Long getPkSpitslot() {
		return this.pkSpitslot;
	}

	public void setPkSpitslot(Long pkSpitslot) {
		this.pkSpitslot = pkSpitslot;
	}

	@Column(name = "pk_order")
	public Long getPkOrder() {
		return this.pkOrder;
	}

	public void setPkOrder(Long pkOrder) {
		this.pkOrder = pkOrder;
	}

	@Column(name = "pk_beautyrecord")
	public Long getPkBeautyrecord() {
		return this.pkBeautyrecord;
	}

	public void setPkBeautyrecord(Long pkBeautyrecord) {
		this.pkBeautyrecord = pkBeautyrecord;
	}

	@Column(name = "spitslotorid")
	public Long getSpitslotorid() {
		return this.spitslotorid;
	}

	public void setSpitslotorid(Long spitslotorid) {
		this.spitslotorid = spitslotorid;
	}

	@Column(name = "spitslotor", length = 30)
	public String getSpitslotor() {
		return this.spitslotor;
	}

	public void setSpitslotor(String spitslotor) {
		this.spitslotor = spitslotor;
	}

	@Column(name = "spitslottime", length = 19)
	public String getSpitslottime() {
		return this.spitslottime;
	}

	public void setSpitslottime(String spitslottime) {
		this.spitslottime = spitslottime;
	}

	@Column(name = "spitslotcontent")
	public String getSpitslotcontent() {
		return this.spitslotcontent;
	}

	public void setSpitslotcontent(String spitslotcontent) {
		this.spitslotcontent = spitslotcontent;
	}

	@Column(name = "replymanid")
	public Long getReplymanid() {
		return this.replymanid;
	}

	public void setReplymanid(Long replymanid) {
		this.replymanid = replymanid;
	}

	@Column(name = "replyman", length = 30)
	public String getReplyman() {
		return this.replyman;
	}

	public void setReplyman(String replyman) {
		this.replyman = replyman;
	}

	@Column(name = "replytime", length = 19)
	public String getReplytime() {
		return this.replytime;
	}

	public void setReplytime(String replytime) {
		this.replytime = replytime;
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