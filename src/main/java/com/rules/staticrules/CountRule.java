/**
* File  : CountRule.java
* Description          : This CountRule is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Oct 17, 2016      	595251  	 Initial version
*/
package com.rules.staticrules;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rules.common.RuleInvokerConstants;
import com.rules.common.util.RuleUtil;
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
		logger.info("array.size() --->" +array.size());
		
		//get the limit set for this rule
		int limit = getLimit();
		//if no limit set, set the array size
		limit = limit>0?limit:array.size();
		
		List <JSONObject> newArray = new ArrayList () ;

		//if no input values to limit, don't process and return the same input
		if (limit == array.size())
			return json;
		
		try{
		for (int i=0; i< array.size() & i < limit; i++){
			newArray.add((JSONObject) array.get(i));
		}
		}catch(Exception e){
			logger.info("Exception --->" +e.getMessage());
		}
			
		logger.info("newArray.size--->" + newArray.size());
		logger.info("newArray--->" + newArray);
		JSONArray jarr = new JSONArray();
		jarr.addAll(newArray);
		json.put("result", jarr );
		logger.info("json--->" + json.toString());
		return json;

	}


	/**
	 * @param @return 
	 * @return int
	 *
	 */
	private int getLimit() {
		
		
		try{
			if (isLimit())
			//check for valid value
				return Integer.parseInt((String)this.getOptionalFields().get(RuleInvokerConstants.COUNT));
		}catch(Exception e){
			return -1;
		}
		
		return -1;
	}


	/**
	 * @param @return 
	 * @return boolean
	 *
	 */
	private boolean isLimit() {
		
		if (this.getOptionalFields() == null || RuleUtil.isEmpty(this.getOptionalFields().get(RuleInvokerConstants.COUNT)))
			return false;
		
		return true;
	}
	

	
}
