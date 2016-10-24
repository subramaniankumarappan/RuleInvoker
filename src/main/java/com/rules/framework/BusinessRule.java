package com.rules.framework;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rules.common.RuleInvokerConstants;
import com.rules.common.util.RuleUtil;


/**
* File  : BusinessRule.java
* Description          : This BusinessRule class is a abstract rule which has 
* 						 empty implementations for pre and post processes.
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/

public abstract class BusinessRule implements IRule {

	private static final Logger logger = LoggerFactory.getLogger(BusinessRule.class);
	
	private Map OptionalFields;
	
	
	
	public Map getOptionalFields() {
		return OptionalFields;
	}


	public void setOptionalFields(Map optionalFields) {
		OptionalFields = optionalFields;
		logger.info("BusinessRule.setOptionalFields() --->" +this.getOptionalFields());
		
	}


	@Override
	public Object executePreprocess(Object request) {
		// TODO Auto-generated method stub
		logger.info("RuleHandler.executePreprocess--->");
		return request;
	}

	
	@Override
	public Object executePostprocess(Object request) {
		// TODO Auto-generated method stub
		logger.info("RuleHandler.executePostprocess--->");
		return request;
	}

	


}
