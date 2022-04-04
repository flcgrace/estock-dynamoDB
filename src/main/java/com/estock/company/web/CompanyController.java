package com.estock.company.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.estock.company.entities.Company;
import com.estock.company.entities.CompanyExtended;
import com.estock.company.services.CompanyServiceImpl;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyServiceImpl companyService;
	
	@PostMapping("/register")
	public Company registerCompany(@RequestBody Company company) throws ConstraintViolationException {	
		return companyService.saveCompany(company);
	}
	@GetMapping("/info/{companycode}")
	public CompanyExtended getCompanyDetail(@PathVariable String companycode) {
		String result=null;
		CompanyExtended compEx=null;
		 Map < String, String > params = new HashMap < String, String > ();
	     params.put("companycode", companycode);

	        RestTemplate restTemplate = new RestTemplate();
	        try {
	         result = restTemplate.getForObject("http://localhost:8082/stock/latest/"+companycode,String.class);
	        }
	        catch(Exception e) {
	        	result="no stock to display";
	        }
	    compEx= new CompanyExtended(companyService.findCompanyByCompanyCode(companycode),result);        
	        return compEx;
	}
	
	@GetMapping("/getall")
	public List<Company> getListOfCompanies() {
		return companyService.fetchAllCompanies();
	}
	@GetMapping("/getall/companynames")
	public Map<String,String> getListOfCompanyNames() {
		return companyService.fetchAllCompanyNames();
	}	
	
	@DeleteMapping("/delete/{companycode}")
	public void deleteCompany(@PathVariable String companycode) {
		companyService.deleteCompany(companycode);
		Map < String, String > params = new HashMap < String, String > ();
        params.put("companycode", companycode);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8082/stock/delete/"+companycode);
	
	}
	@GetMapping("/validate/{companycode}")
	public Boolean validateCompanyDetail(@PathVariable String companycode) {
		 Company company=companyService.findCompanyByCompanyCode(companycode);
		 if(!Objects.isNull(company)) {
			 return true;
		 }
		 return false;
	}

}
