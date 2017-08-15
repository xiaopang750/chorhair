package com.rockstar.o2o.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * UserFile entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "user_file")
public class UserFile implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long pkFile;
	private Long pkUser;
	private String usergroup;
	private String bemodel;
	private String filename;
	private String filepath;
	private String thumbnail;
	private String filesize;
	private String updatetime;
	private String status;
	private String currentversion;
	private String ts;
	private Short dr;

	// Constructors

	/** default constructor */
	public UserFile() {
	}

	/** full constructor */
	public UserFile(Long pkUser, String usergroup, String bemodel,
			String filename, String filepath, String thumbnail,
			String filesize, String updatetime, String status,
			String currentversion, String ts, Short dr) {
		this.pkUser = pkUser;
		this.usergroup = usergroup;
		this.bemodel = bemodel;
		this.filename = filename;
		this.filepath = filepath;
		this.thumbnail = thumbnail;
		this.filesize = filesize;
		this.updatetime = updatetime;
		this.status = status;
		this.currentversion = currentversion;
		this.ts = ts;
		this.dr = dr;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "pk_file", unique = true, nullable = false)
	public Long getPkFile() {
		return this.pkFile;
	}

	public void setPkFile(Long pkFile) {
		this.pkFile = pkFile;
	}

	@Column(name = "pk_user")
	public Long getPkUser() {
		return this.pkUser;
	}

	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}

	@Column(name = "usergroup", length = 30)
	public String getUsergroup() {
		return this.usergroup;
	}

	public void setUsergroup(String usergroup) {
		this.usergroup = usergroup;
	}

	@Column(name = "bemodel", length = 100)
	public String getBemodel() {
		return this.bemodel;
	}

	public void setBemodel(String bemodel) {
		this.bemodel = bemodel;
	}

	@Column(name = "filename")
	public String getFilename() {
		return this.filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Column(name = "filepath")
	public String getFilepath() {
		return this.filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Column(name = "thumbnail")
	public String getThumbnail() {
		return this.thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Column(name = "filesize", length = 20)
	public String getFilesize() {
		return this.filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	@Column(name = "updatetime", length = 19)
	public String getUpdatetime() {
		return this.updatetime;
	}

	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}

	@Column(name = "status", length = 20)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "currentversion", length = 10)
	public String getCurrentversion() {
		return this.currentversion;
	}

	public void setCurrentversion(String currentversion) {
		this.currentversion = currentversion;
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