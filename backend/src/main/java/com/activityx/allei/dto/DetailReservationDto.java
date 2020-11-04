package com.activityx.allei.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DetailReservationDto {
	private int id;	// 아이디
	private int reservation;	// 예약번호
	private int product;	// 상품번호
	private int num;	// 수량
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date start;	// 시작날짜
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date end;	// 끝날짜
	
	public DetailReservationDto() {
		super();
	}
	public DetailReservationDto(int id, int reservation, int product, int num, Date start, Date end) {
		super();
		this.id = id;
		this.reservation = reservation;
		this.product = product;
		this.num = num;
		this.start = start;
		this.end = end;
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
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Date getStart() {
		return start;
	}
	public void setStart(Date start) {
		this.start = start;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	
	@Override
	public String toString() {
		return "DetailReservationDto [id=" + id + ", reservation=" + reservation + ", product=" + product + ", num="
				+ num + ", start=" + start + ", end=" + end + "]";
	}
}
