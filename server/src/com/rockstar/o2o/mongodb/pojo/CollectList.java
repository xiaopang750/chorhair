package com.rockstar.o2o.mongodb.pojo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public class CollectList implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String id;
	private String collecttype;
	private String collecttime;
	private Long pkContent;
	private String collectfrom;
	private String collectorid;
	private Long pkUser;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCollecttype() {
		return collecttype;
	}
	public void setCollecttype(String collecttype) {
		this.collecttype = collecttype;
	}
	public String getCollecttime() {
		return collecttime;
	}
	public void setCollecttime(String collecttime) {
		this.collecttime = collecttime;
	}
	public Long getPkContent() {
		return pkContent;
	}
	public void setPkContent(Long pkContent) {
		this.pkContent = pkContent;
	}
	public String getCollectfrom() {
		return collectfrom;
	}
	public void setCollectfrom(String collectfrom) {
		this.collectfrom = collectfrom;
	}
	public String getCollectorid() {
		return collectorid;
	}
	public void setCollectorid(String collectorid) {
		this.collectorid = collectorid;
	}
	public Long getPkUser() {
		return pkUser;
	}
	public void setPkUser(Long pkUser) {
		this.pkUser = pkUser;
	}
	@Override
	public String toString() {
		return "CollectList [id=" + id + ", collecttype=" + collecttype
				+ ", collecttime=" + collecttime + ", pkContent=" + pkContent
				+ ", collectfrom=" + collectfrom + ", collectorid="
				+ collectorid + ", pkUser=" + pkUser + "]";
	}
	
	
}
