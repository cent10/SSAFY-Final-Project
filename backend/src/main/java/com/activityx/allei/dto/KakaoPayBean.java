package com.activityx.allei.dto;

public class KakaoPayBean {
	
	private int id;	// 주문 번호
	private int shop;	// 업체 번호
	private String name;	// 상품명
	private int quantity;	// 수량
	private int amount;	// 결제 가격

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getShop() {
		return shop;
	}
	public void setShop(int shop) {
		this.shop = shop;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}

}
