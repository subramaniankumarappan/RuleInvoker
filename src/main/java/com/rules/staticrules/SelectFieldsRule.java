/**
* File  : SelectFieldsRule.java
* Description          : This SelectFieldsRule is   
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Oct 21, 2016      	595251  	 Initial version
*/
package com.rules.staticrules;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rules.common.RuleInvokerConstants;
import com.rules.common.constants.RuleConstants;
import com.rules.common.util.RuleUtil;
import com.rules.framework.BusinessRule;

/**
 * @author 595251
 *
 */
public class SelectFieldsRule extends BusinessRule{

	private static final Logger logger = LoggerFactory.getLogger(SelectFieldsRule.class);

	/* (non-Javadoc)
	 * @see com.rules.framework.IRule#execute(java.lang.Object)
	 */
	@Override
	public Object execute(Object request) {
		logger.info("SelectFieldsRule.execute --->");
		JSONObject json = (JSONObject) request;
		JSONArray array = (JSONArray) json.get("result");
		
		//newArray.add(json.get("success"));
		logger.info("array.size() --->" +array.size());
		/*
		List <String> fieldsList = new ArrayList ();
		fieldsList.add("cust_nbr");
		fieldsList.add("gond_seq_nbr");
		fieldsList.add("row_inact_dt");
		fieldsList.add("xyz");
		*/
		//get list of fields
		List <String> fieldsList = getFields();
		//if no fields set, return the same input without processing
		if (fieldsList == null || fieldsList.size() == 0)
			return json;
		
		List <JSONObject> newArray = new ArrayList () ;
		JSONObject jsonObject = null;
		JSONObject arrObj = null;
		Object obj = null;
		try{
		for (int i=0; i< array.size(); i++){
			
			jsonObject = new JSONObject();
			arrObj = (JSONObject) array.get(i);
			
			for(int j=0; j< fieldsList.size() ; j++){
				if(arrObj.containsKey((Object)fieldsList.get(j)))
					jsonObject.put(fieldsList.get(j), arrObj.get(fieldsList.get(j)));
				else
					logger.info("i= " +i +", No Data for --->" +fieldsList.get(j));
			}
			newArray.add(jsonObject);
										
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

	/**
	 * @param @return 
	 * @return List<String>
	 *
	 */
	private List<String> getFields() {
		if (!isFields())
			return null;
		String fields = (String) this.getOptionalFields().get(RuleInvokerConstants.FIELDS);
		if (RuleUtil.isEmpty(fields))
			return null;
		
		//return RuleUtil.getList(fields, RuleConstants.FIELDS_SEPERATOR);
		return RuleUtil.getList(fields, ",");
	}
	
	
	/**
	 * @param @return 
	 * @return boolean
	 *
	 */
	private boolean isFields() {
		
		if (this.getOptionalFields() == null || RuleUtil.isEmpty(this.getOptionalFields().get(RuleInvokerConstants.FIELDS)))
			return false;
		
		return true;
	}
	

}
