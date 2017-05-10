package com.sample.model;

import java.util.Date;

public class PromotionModel {
	
	private int partnerId;
	private long duration;
	private String adContent;
	private Date promotionDate;
	private String status;
	
	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public String getAdContent() {
		return adContent;
	}
	public void setAdContent(String adContent) {
		this.adContent = adContent;
	}
	public Date getPromotionDate() {
		return promotionDate;
	}
	public void setPromotionDate(Date promotionDate) {
		this.promotionDate = promotionDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
