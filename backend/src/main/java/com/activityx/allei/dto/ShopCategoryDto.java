package com.activityx.allei.dto;

public class ShopCategoryDto {
	private int id;
	private int shop;
	private int code;
	
	public ShopCategoryDto() {
		super();
	}
	public ShopCategoryDto(int id, int shop, int code) {
		super();
		this.id = id;
		this.shop = shop;
		this.code = code;
	}
	
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
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	
	@Override
	public String toString() {
		return "ShopCategoryDto [id=" + id + ", shop=" + shop + ", code=" + code + "]";
	}
}
