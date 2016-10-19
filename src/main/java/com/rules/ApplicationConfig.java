package com.rules;

import javax.inject.Named;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
* File  : ApplicationConfig.java
* Description          : This  class is config class for Rule Invoker 
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/

@Configuration
public class ApplicationConfig {
@Named
static class JerseyConfig  extends ResourceConfig {
	public JerseyConfig () {
		this.packages("com.rules.rest");
	}
}
@Bean
public RestTemplate restTemplate() {
	RestTemplate restTemplate = new RestTemplate();
	return restTemplate;
	}
}
