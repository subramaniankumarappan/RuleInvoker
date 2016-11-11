/**
* File  : SearchRule.java
* Description          : This SearchRule is   a Rule class to find any objects with matches 
*                        the given pattern for the specified field.
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Oct 17, 2016      	595251  	 Initial version
*/
package com.rules.staticrules;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rule.common.util.RuleInvokerUtil;
import com.rules.common.RuleInvokerConstants;
import com.rules.common.constants.RuleConstants;
import com.rules.framework.BusinessRule;

/**
 * @author 595251
 *
 */
public class SearchRule extends BusinessRule{

	private static final Logger logger = LoggerFactory.getLogger(SearchRule.class);
	
	@Override
	public Object execute(Object request) {
		logger.info("SearchRule.execute --->");
		JSONObject json = (JSONObject) request;
		
		JSONArray array = (JSONArray) json.get("result");
		//newArray.add(json.get("success"));
		logger.info("array.size() --->" +array.size());
		
		//if field or expression or both are not set, return without processing.
		if (!RuleInvokerUtil.isAvailable(this.getOptionalFields(), RuleConstants.SEARCH_FIELD)
				|| !RuleInvokerUtil.isAvailable(this.getOptionalFields(), RuleConstants.EXPRESSION))
			return json;
		
		logger.info("Perform search");
		List <JSONObject> newArray = new ArrayList () ;
		String value, regexp;
		Pattern pattern;
		Matcher m;
		try{
			for (int i=0; i< array.size(); i++){
				
				 // String to be scanned to find the pattern.
			     value = ((JSONObject) array.get(i)).get
			    		  				(this.getOptionalFields().get(RuleConstants.SEARCH_FIELD)).toString();
			     regexp = (String) this.getOptionalFields().get(RuleConstants.EXPRESSION);
			      // Create a Pattern object
			     pattern = Pattern.compile(regexp);
	
			      // Now create matcher object.
			     m = pattern.matcher(value);
				  //if pattern find or matches, add that object
				 if (m.find() || m.matches())
					newArray.add((JSONObject)array.get(i));
								
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
		//return newArray.toString();
	}
	
}
