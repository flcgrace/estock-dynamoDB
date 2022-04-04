package com.estock.company.entities;

public class CompanyExtended {
	
	private String latestStock;
	private Company company;
	public CompanyExtended(Company comp, String stock) {
		latestStock=stock;
		company=comp;
	}
	public void setLatestStock(String stock) {
		latestStock=stock;
	}
	public String getLatestStock() {
		return latestStock;
	}
	public void setCompany(Company comp) {
		company=comp;
	}
	public Company getCompany() {
		return company;
	}

}
