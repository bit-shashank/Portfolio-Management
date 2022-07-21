package com.networth.dtos;

import java.io.Serializable;

public class StockDto implements Serializable{
    private String name;
    private double currentPrice;
    private double qty;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	public double getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
    
}
