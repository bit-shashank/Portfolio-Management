package com.networth.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AssertSaleResponse {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private boolean saleStatus;
	private String networth;

	public AssertSaleResponse(long id, boolean saleStatus, String networth) {
		super();
		this.id = id;
		this.saleStatus = saleStatus;
		this.networth = networth;
	}

	public AssertSaleResponse() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isSaleStatus() {
		return saleStatus;
	}

	public void setSaleStatus(boolean saleStatus) {
		this.saleStatus = saleStatus;
	}

	public String getNetworth() {
		return networth;
	}

	public void setNetworth(String networth) {
		this.networth = networth;
	}

}
