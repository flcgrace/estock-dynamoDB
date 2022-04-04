package com.estock.company;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
//@EnableEurekaClient
@EnableSwagger2
public class CompanyServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyServicesApplication.class, args);
	}

}
