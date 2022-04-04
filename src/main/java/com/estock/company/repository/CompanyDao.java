package com.estock.company.repository;




import com.estock.company.entities.Company;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CompanyDao {
	
//	@Query("{companyCode:'?0'}")
//	Company findCompanyByCode(String companyCode);
//	
//	
////	@Query()
////	List<Company> findAll();
//	Long deleteByCompanyCode(String companyCode);
//	public long count();
	@Autowired
    private DynamoDBMapper dynamoDBMapper;

    public Company saveCompany(Company company) {
        dynamoDBMapper.save(company);
        return company;
    }

    public Company getCompanyByCompanyCode(String companyCode) {
        return dynamoDBMapper.load(Company.class, companyCode);
    }

    public String deleteCompanyByCompanyCode(String companyCode) {
        dynamoDBMapper.delete(dynamoDBMapper.load(Company.class, companyCode));
        return "Comapany Code : "+ companyCode +" Deleted!";
    }
    public List<Company> getAllCompany(){
    	DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
    	List < Company > compList = dynamoDBMapper.scan(Company.class, scanExpression);
    	return compList;
    }
	
}
