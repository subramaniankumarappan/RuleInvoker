/**
* File  : CountRule.java
* Description          : This CountRule is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Oct 17, 2016      	595251  	 Initial version
*/
package com.rules.staticrules;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rules.framework.BusinessRule;

/**
 * @author 595251
 *
 */
public class CountRule extends BusinessRule{

	private static final Logger logger = LoggerFactory.getLogger(CountRule.class);

	/* (non-Javadoc)
	 * @see com.rules.framework.IRule#execute(java.lang.Object)
	 */
	@Override
	public Object execute(Object request) {
		logger.info("CountRule.execute --->");
		JSONObject json = (JSONObject) request;
		
		JSONArray array = (JSONArray) json.get("result");
		
		if(array.size() > 1)
			json.put("result", array.get(0) );
		
		logger.info("CountRule.json --->" +json.toString());
		return json;
	}
	

}
