package com.activityx.allei.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class NoticeDto {
	private int id;	// 아이디
	private int user;	// 사용자 아이디
	private String title;	// 제목
	private String content;	// 내용
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date date;	// 작성일
	private int hits;	// 조회수
	
	public NoticeDto() {
		super();
	}
	public NoticeDto(int id, int user, String title, String content, Date date, int hits) {
		super();
		this.id = id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.date = date;
		this.hits = hits;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser() {
		return user;
	}
	public void setUser(int user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public int getHits() {
		return hits;
	}
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	@Override
	public String toString() {
		return "NoticeDto [id=" + id + ", user=" + user + ", title=" + title + ", content=" + content + ", date=" + date
				+ ", hits=" + hits + "]";
	}
}
