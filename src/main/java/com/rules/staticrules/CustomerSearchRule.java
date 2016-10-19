package com.rules.staticrules;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rules.framework.BusinessRule;

/**
* File  : CustomerSearchRule.java
* Description          : This CustomerSearchRule class is a static rule to filter 
*                        the input customer array.  
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/

public class CustomerSearchRule extends BusinessRule{

	private static final Logger logger = LoggerFactory.getLogger(CustomerSearchRule.class);
	
	@Override
	public Object execute(Object request) {
		logger.info("CustomerSearchHandler.execute --->");
		JSONObject json = (JSONObject) request;
		
		JSONArray array = (JSONArray) json.get("person");
		for (int i=0; i< array.size(); i++)
			logger.info("PERSON[" +i + "]--->" + array.get(i));
		
		return array.get(0);
	}

}
