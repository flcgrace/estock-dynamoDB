package com.estock.company.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.ItemCollection;
import com.estock.company.entities.Company;
import com.estock.company.exception.CompanyNotFoundException;
import com.estock.company.repository.CompanyDao;

@Service
public class CompanyServiceImpl implements CompanyServices {
	@Autowired
	private CompanyDao companyDao;

	@Override
	public List<Company> fetchAllCompanies() {
		List<Company> companyList= companyDao.getAllCompany();
		return companyList;
	}
	@Override
	public Map<String,String> fetchAllCompanyNames() {
		List<Company> companyList= companyDao.getAllCompany();
		Map<String,String> nameMap=new HashMap<>();
		//Iterator<Item> namesIterator= companyList.iterator();
		for(Company comp:companyList) {
			nameMap.put(comp.getCompanyname(), comp.getCompanyCode());
		}
    // Traversing elements
//    while (namesIterator.hasNext()) {
//       String compname=namesIterator.next().get("companyname").toString();
//       String compcode=namesIterator.next().get("companyCode").toString();
//       System.out.println(compname);
//       nameMap.put(compname,compcode);
//    }
		return nameMap;
	}

	@Override
	public Company findCompanyByCompanyCode(String companyCode) {
		try {
			
			if(Optional.ofNullable(companyDao.getCompanyByCompanyCode(companyCode)).isEmpty()) {
				throw new CompanyNotFoundException("No Companies Found!!");
			}
		return companyDao.getCompanyByCompanyCode(companyCode);
		}
		catch(ConstraintViolationException ex){
		throw new ConstraintViolationException(ex.getMessage() + "manual " + ExceptionUtils.getRootCauseMessage(ex), ex.getSQLException(),
			    ex.getConstraintName());
		}
		
	}

	@Override
	public Company saveCompany(Company company) {
		return companyDao.saveCompany(company);
		
	}

	@Override
	public void deleteCompany(String companyCode) {
		String deletedcomp=companyDao.deleteCompanyByCompanyCode(companyCode);
	}

}
