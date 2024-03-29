package com.networth.dtos;

public class FundDto {
	private Long id;
    private String name;
    private double currentNav;
    private double qty;
	
    public FundDto() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCurrentNav() {
		return currentNav;
	}

	public void setCurrentNav(double currentNav) {
		this.currentNav = currentNav;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
	
}
