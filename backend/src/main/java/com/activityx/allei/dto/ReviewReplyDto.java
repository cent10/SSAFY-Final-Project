package com.activityx.allei.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReviewReplyDto {
	private int id;
	private int replier;
	private int review;
	private String content;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date date;	// 작성일
	
	public ReviewReplyDto() {
		super();
	}
	public ReviewReplyDto(int id, int replier, int review, String content, Date date) {
		super();
		this.id = id;
		this.replier = replier;
		this.review = review;
		this.content = content;
		this.date = date;
	}
	
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
	public int getReview() {
		return review;
	}
	public void setReview(int review) {
		this.review = review;
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
	
	@Override
	public String toString() {
		return "ReviewReplyDto [id=" + id + ", replier=" + replier + ", review=" + review + ", content=" + content
				+ ", date=" + date + "]";
	}
}
