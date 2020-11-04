package com.activityx.allei.dto;

public class ShopDto {
	private int id;	// 아이디
	private int admin;	// 관리자 아이디
	private String name;	// 업체명
	private String address;	// 주소
	private String phone;	// 연락처
	private boolean classification;	// 구분 (0:레저, 1:장비)
	private String number;	// 사업자등록번호
	private String description;	// 설명
	private String img;	// 이미지
	private String region;	// 지역
	
	public ShopDto() {
		super();
	}
	public ShopDto(int id, int admin, String name, String address, String phone, boolean classification, String number,
			String description, String img, String region) {
		super();
		this.id = id;
		this.admin = admin;
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.classification = classification;
		this.number = number;
		this.description = description;
		this.img = img;
		this.region = region;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAdmin() {
		return admin;
	}
	public void setAdmin(int admin) {
		this.admin = admin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isClassification() {
		return classification;
	}
	public void setClassification(boolean classification) {
		this.classification = classification;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	
	@Override
	public String toString() {
		return "ShopDto [id=" + id + ", admin=" + admin + ", name=" + name + ", address=" + address + ", phone=" + phone
				+ ", classification=" + classification + ", number=" + number + ", description=" + description
				+ ", img=" + img + ", region=" + region + "]";
	}
}
