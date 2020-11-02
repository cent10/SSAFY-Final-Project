package com.activityx.allei.dto;

public class User {
	private int id;
	private String email;
	private String name;
	private String domain;
	private long ukey;

	public long getUkey() {
		return ukey;
	}

	public void setUkey(long ukey) {
		this.ukey = ukey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

}
