package com.rules;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;


/**
* File  : RuleServiceApplication.java
* Description          : This  RuleServiceApplication class is  
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/
@EnableDiscoveryClient
@EnableEurekaClient
@SpringBootApplication
public class RuleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RuleServiceApplication.class, args);
	}
}
