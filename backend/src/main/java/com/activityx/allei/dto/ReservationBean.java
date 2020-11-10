package com.activityx.allei.dto;

import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ReservationBean {
	private int id;
	private int user;
	private int shop;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date date;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date start;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
	private Date end;
	private ArrayList<Integer> products;
	private ArrayList<Integer> nums;

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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public ArrayList<Integer> getProducts() {
		return products;
	}
	public void setProducts(ArrayList<Integer> products) {
		this.products = products;
	}
	public ArrayList<Integer> getNums() {
		return nums;
	}
	public void setNums(ArrayList<Integer> nums) {
		this.nums = nums;
	}

}
