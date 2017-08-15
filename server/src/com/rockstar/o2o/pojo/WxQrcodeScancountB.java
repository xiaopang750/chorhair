package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * WxQrcodeScancountB entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "wx_qrcode_scancount_b")
public class WxQrcodeScancountB implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	private Long pkQrcodeScancountB;
	private Long pkScancountid;
	private String scanUserid;
	private String ts;
	private Short dr;
	private String isOlduser;
	// Constructors

	/** default constructor */
	public WxQrcodeScancountB() {
	}

	/** full constructor */
	public WxQrcodeScancountB(Long pkScancountid, String scanUserid,
			String ts, Short dr,String isOlduser) {
		this.pkScancountid = pkScancountid;
		this.scanUserid = scanUserid;
		this.ts = ts;
		this.dr = dr;
		this.isOlduser = isOlduser;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_qrcode_scancount_b", unique = true, nullable = false)
	public Long getPkQrcodeScancountB() {
		return this.pkQrcodeScancountB;
	}

	public void setPkQrcodeScancountB(Long pkQrcodeScancountB) {
		this.pkQrcodeScancountB = pkQrcodeScancountB;
	}

	@Column(name = "pk_scancountid")
	public Long getPkScancountid() {
		return this.pkScancountid;
	}

	public void setPkScancountid(Long pkScancountid) {
		this.pkScancountid = pkScancountid;
	}

	@Column(name = "scan_userid", unique = true)
	public String getScanUserid() {
		return this.scanUserid;
	}

	public void setScanUserid(String scanUserid) {
		this.scanUserid = scanUserid;
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
	@Column(name = "is_olduser")
	public String getIsOlduser() {
		return isOlduser;
	}

	public void setIsOlduser(String isOlduser) {
		this.isOlduser = isOlduser;
	}

}