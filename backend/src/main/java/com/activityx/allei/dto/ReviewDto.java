package com.activityx.allei.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReviewDto {
	private int id;	// 아이디
	private int reservation;	// 예약정보 아이디
	private int rate;	// 평점
	private String content;	// 내용
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date date;	// 작성일
	
	public ReviewDto() {
		super();
	}
	public ReviewDto(int id, int reservation, int rate, String content, Date date) {
		super();
		this.id = id;
		this.reservation = reservation;
		this.rate = rate;
		this.content = content;
		this.date = date;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getReservation() {
		return reservation;
	}
	public void setReservation(int reservation) {
		this.reservation = reservation;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
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
		return "ReviewDto [id=" + id + ", reservation=" + reservation + ", rate=" + rate + ", content=" + content
				+ ", date=" + date + "]";
	}
}
