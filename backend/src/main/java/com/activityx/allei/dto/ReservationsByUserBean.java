package com.activityx.allei.dto;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationsByUserBean {
	private int id;	//예약번호
	private int user;	//사용자번호
	private int shop;	//업체 번호
	private String shopName;	//업체 이름
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date date;	//예약 시간
	private ArrayList<ProductDto> products;
	private ArrayList<Integer> nums;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date start;
	@JsonFormat(pattern="yyyy-MM-dd", timezone = "Asia/Seoul")
	private Date end;

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
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public ArrayList<ProductDto> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<ProductDto> products) {
		this.products = products;
	}
	public ArrayList<Integer> getNums() {
		return nums;
	}
	public void setNums(ArrayList<Integer> nums) {
		this.nums = nums;
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

}
