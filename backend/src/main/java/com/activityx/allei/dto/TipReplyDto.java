package com.activityx.allei.dto;

import java.util.Date;

public class TipReplyDto {
	
	private int id;
	private int replier;
	private String name;
	private int tip;
	private String content;
	private Date date;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReplier() {
		return replier;
	}
	public void setReplier(int replier) {
		this.replier = replier;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getTip() {
		return tip;
	}
	public void setTip(int tip) {
		this.tip = tip;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
