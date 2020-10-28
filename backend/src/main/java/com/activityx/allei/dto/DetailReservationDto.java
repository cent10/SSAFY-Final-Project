package com.activityx.allei.dto;

public class DetailReservationDto {
	private int id;	// 아이디
	private int reservation;	// 예약번호
	private int product;	// 상품번호
	private int num;	// 수량
	
	public DetailReservationDto() {
		super();
	}
	public DetailReservationDto(int id, int reservation, int product, int num) {
		super();
		this.id = id;
		this.reservation = reservation;
		this.product = product;
		this.num = num;
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
	
	@Override
	public String toString() {
		return "DetailReservationDto [id=" + id + ", reservation=" + reservation + ", product=" + product + ", num="
				+ num + "]";
	}
}
