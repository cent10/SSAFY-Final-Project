package com.activityx.allei.dto;

public class ReservationDto {
	private int id;	// 아이디
	private int user;	// 사용자 아이디
	private int shop;	// 업체 아이디

	public ReservationDto() {
		super();
	}
	public ReservationDto(int id, int user, int shop) {
		super();
		this.id = id;
		this.user = user;
		this.shop = shop;
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
	public int getShop() {
		return shop;
	}
	public void setShop(int shop) {
		this.shop = shop;
	}
	
	@Override
	public String toString() {
		return "ReservationDto [id=" + id + ", user=" + user + ", shop=" + shop + "]";
	}
}
