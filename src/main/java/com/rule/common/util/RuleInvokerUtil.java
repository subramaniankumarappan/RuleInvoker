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
import com.rules.common.constants.RuleConstants;
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
	public static List<String> getSortFields(Map optionalFields) {
		return getFields(optionalFields, RuleConstants.SORT_FIELDS);
		
	}
	
    /**
     * 
     * @param @param optionalFields
     * @param @return 
     * @return List<String>
     *
     */
	public static List<String> getSelectFields(Map optionalFields) {
		return getFields(optionalFields, RuleConstants.SELECT_FIELDS);
		
	}
	/**
	 * 
	 * @param @param optionalFields
	 * @param @return 
	 * @return List<String>
	 *
	 */
	private static List<String> getFields(Map optionalFields, String key) {
		if (!isAvailable(optionalFields, key))
			return null;
		String fields = (String) optionalFields.get(key);
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
	public static int getSize(Map optionalFields) {
		
		
		try{
			if (isAvailable(optionalFields, RuleConstants.SIZE))
			//check for valid value
				return Integer.parseInt((String)optionalFields.get(RuleConstants.SIZE));
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
	public static String getSortOrder(Map optionalFields) {
		
		String orderBy = RuleInvokerConstants.ORDER_BY_ASC;		
		if (!isAvailable(optionalFields, RuleConstants.SORT_ORDER))
			return orderBy;
		orderBy = (String) optionalFields.get(RuleConstants.SORT_ORDER);
		if (RuleUtil.isEmpty(orderBy))
			orderBy = RuleInvokerConstants.ORDER_BY_ASC;
		
		return orderBy;
	}
	

}
