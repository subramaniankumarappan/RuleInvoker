/**
* File  : RuleInvokerUtil.java
* Description          : This RuleInvokerUtil is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Oct 24, 2016      	595251  	 Initial version
*/
package com.rule.common.util;

import java.util.List;
import java.util.Map;

import com.rules.common.RuleInvokerConstants;
import com.rules.common.util.RuleUtil;

/**
 * @author 595251
 *
 */
public class RuleInvokerUtil {
	
	/**
	 * 
	 * @param @param optionalFields
	 * @param @return 
	 * @return List<String>
	 *
	 */
	public static List<String> getFields(Map optionalFields) {
		if (!isAvailable(optionalFields, RuleInvokerConstants.FIELDS))
			return null;
		String fields = (String) optionalFields.get(RuleInvokerConstants.FIELDS);
		if (RuleUtil.isEmpty(fields))
			return null;
		
		//return RuleUtil.getList(fields, RuleConstants.FIELDS_SEPERATOR);
		return RuleUtil.getList(fields, ",");
	}
	
	/**
	 * 
	 * @param @param optionalFields
	 * @param @return 
	 * @return int
	 *
	 */
	public static int getLimit(Map optionalFields) {
		
		
		try{
			if (isAvailable(optionalFields, RuleInvokerConstants.COUNT))
			//check for valid value
				return Integer.parseInt((String)optionalFields.get(RuleInvokerConstants.COUNT));
		}catch(Exception e){
			return -1;
		}
		
		return -1;
	}



	/**
	 * 
	 * @param @param map
	 * @param @param key
	 * @param @return 
	 * @return boolean
	 *
	 */
	public static boolean isAvailable(Map map, String key) {
		if (map == null || RuleUtil.isEmpty(map.get(key)))
			return false;
		
		return true;
	}

	/**
	 * @param @param optionalFields
	 * @param @return 
	 * @return String
	 *
	 */
	public static String getOrderBy(Map optionalFields) {
		
		String orderBy = RuleInvokerConstants.ORDER_BY_ASC;		
		if (!isAvailable(optionalFields, RuleInvokerConstants.ORDER_BY))
			return orderBy;
		orderBy = (String) optionalFields.get(RuleInvokerConstants.ORDER_BY);
		if (RuleUtil.isEmpty(orderBy))
			orderBy = RuleInvokerConstants.ORDER_BY_ASC;
		
		return orderBy;
	}
	

}
