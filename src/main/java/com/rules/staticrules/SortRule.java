/**
* File  : SortRule.java
* Description          : This SortRule is  a rule class to sort the array based on list of input fields  
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Oct 24, 2016      	595251  	 Initial version
*/
package com.rules.staticrules;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rule.common.util.RuleInvokerUtil;
import com.rules.common.RuleInvokerConstants;
import com.rules.common.MultiFieldJSONComparator;
import com.rules.common.util.RuleUtil;
import com.rules.framework.BusinessRule;

/**
 * @author 595251
 *
 */
public class SortRule extends BusinessRule{

	private static final Logger logger = LoggerFactory.getLogger(SortRule.class);
	
	/* (non-Javadoc)
	 * @see com.rules.framework.IRule#execute(java.lang.Object)
	 */
	@Override
	public Object execute(Object request) {
		logger.info("SortRule.execute --->");
		JSONObject json = (JSONObject) request;
		
		JSONArray array = (JSONArray) json.get("result");
		logger.info("array.size() --->" +array.size());
		
		//get the sort field  for this rule
		List <String> sortFieldList = RuleInvokerUtil.getSortFields(this.getOptionalFields());
		String orderBy = RuleInvokerUtil.getSortOrder(this.getOptionalFields());
		
		
		logger.info("sortFieldList --->" +sortFieldList);
		List <JSONObject> newArray = new ArrayList () ;
		//if no input values to sort, don't process and return the same input
		if (RuleUtil.isEmpty(sortFieldList))
			return json;
		
		try{
			for (int i=0; i< array.size() ; i++){
				newArray.add((JSONObject) array.get(i));
			}
		}catch(Exception e){
			logger.info("Exception --->" +e.getMessage());
		}
			
		logger.info("newArray.size--->" + newArray.size());
		//sort the collection
		Collections.sort(newArray, 				
				RuleInvokerConstants.ORDER_BY_ASC.equalsIgnoreCase(orderBy)==true?
						new MultiFieldJSONComparator(sortFieldList):
							new MultiFieldJSONComparator(sortFieldList).reversed());
		logger.info("newArray--->" + newArray);
		JSONArray jarr = new JSONArray();
		jarr.addAll(newArray);
		json.put("result", jarr );
		logger.info("json--->" + json.toString());
		return json;

	}


	

}
