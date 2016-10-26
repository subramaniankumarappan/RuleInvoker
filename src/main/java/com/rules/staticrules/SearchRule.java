/**
* File  : ForecastRule.java
* Description          : This ForecastRule is   
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

import com.rules.framework.BusinessRule;

/**
 * @author 595251
 *
 */
public class ForecastRule extends BusinessRule{

	private static final Logger logger = LoggerFactory.getLogger(ForecastRule.class);
	
	@Override
	public Object execute(Object request) {
		logger.info("ForecastRule.execute --->");
		JSONObject json = (JSONObject) request;
		
		JSONArray array = (JSONArray) json.get("result");
		List <JSONObject> newArray = new ArrayList () ;
		//newArray.add(json.get("success"));
		logger.info("array.size() --->" +array.size());
		Long custnbr = new Long (118191);
		Long arrLong = null;
		try{
		for (int i=0; i< array.size(); i++){
			
			arrLong = (Long)((JSONObject) array.get(i)).get("cust_nbr");
			logger.info("i= " +i +", arrLong --->" +arrLong);
			if (custnbr.doubleValue() == arrLong.doubleValue())
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
