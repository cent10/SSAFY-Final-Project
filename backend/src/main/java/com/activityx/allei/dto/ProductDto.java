package com.activityx.allei.dto;

public class ProductDto {
	private int id;	// 아이디
	private int shop;	// 업체 아이디
	private String name;	// 상품명
	private String description;	// 설명
	private int price;	// 가격
	private int num;	// 일일판매수량
	
	public ProductDto() {
		super();
	}
	public ProductDto(int id, int shop, String name, String description, int price, int num) {
		super();
		this.id = id;
		this.shop = shop;
		this.name = name;
		this.description = description;
		this.price = price;
		this.num = num;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "ProductDto [id=" + id + ", shop=" + shop + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", num=" + num + "]";
	}
}
