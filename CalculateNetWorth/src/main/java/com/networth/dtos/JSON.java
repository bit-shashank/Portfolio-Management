package com.networth.dtos;

public class JSON {
	
	private String token;
	private Long id;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JSON(String token) {
		super();
		this.token = token;
	}

	public JSON() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
