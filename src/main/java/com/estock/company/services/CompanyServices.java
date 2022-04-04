package com.estock.company.services;

import java.util.List;
import java.util.Map;

import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.estock.company.entities.Company;

public interface CompanyServices {

	public List<Company> fetchAllCompanies();
	public Map<String,String> fetchAllCompanyNames();
	public Company findCompanyByCompanyCode(String companyCode);
	public Company saveCompany(Company company);
	public void deleteCompany(String companyCode);
}
