package com.rules.framework;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


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
