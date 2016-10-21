/**
* File  : RuleInvoker.java
* Description          : This RuleInvoker is a class which is servicing the rest class.  
* Revision History :
* Version      Date            	Author       Reason
* 0.1          Oct 12, 2016      	595251  	 Initial version
*/
package com.rules.controller;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rules.common.RuleInvokerConstants;
import com.rules.common.constants.RuleConstants;
import com.rules.common.pojo.Rule;
import com.rules.data.dao.postgres.RuleMetadataPGDAO;
import com.rules.data.factory.DAOFactory;
import com.rules.data.factory.DBFactory;
import com.rules.framework.BusinessRule;
import com.rules.framework.GateHandler;

/**
 * @author 595251
 *
 */
public class RuleInvoker {
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessRule.class);
	
	/**
	 * this is a wrapper method for rest method.
	 * @param @param request
	 * @param @param microserviceName
	 * @param @return 
	 * @return String
	 *
	 */
	public String executePrehookRule(String request, String microserviceName){
		return executeRule(request, microserviceName, RuleInvokerConstants.PREHOOK);
	}
	
	/**
	 * this is a wrapper method for rest method.
	 * @param @param request
	 * @param @param microserviceName
	 * @param @return 
	 * @return String
	 *
	 */
	public String executePosthookRule(String request, String microserviceName){
		return executeRule(request, microserviceName, RuleInvokerConstants.POSTHOOK);
	}
	
	/**
	 * 
	 * @param @param request
	 * @param @param ruleList
	 * @param @return
	 * @param @throws Exception 
	 * @return String
	 *
	 */
	private String executeRule(String request, String microserviceName, String hookInd) {
		
		List <Rule> ruleList  = 	null; 
		String response = null;
		try{
			if(RuleInvokerConstants.PREHOOK.equalsIgnoreCase(hookInd))
				ruleList  = 	getRules(microserviceName, RuleInvokerConstants.PREHOOK); 
			else if(RuleInvokerConstants.POSTHOOK.equalsIgnoreCase(hookInd))
				ruleList  = 	getRules(microserviceName, RuleInvokerConstants.POSTHOOK); 
			response = executeRule(request, ruleList);
		}catch (ParseException e){
			logger.info("parser exception ---- > "+ e.getMessage());
		}catch (Exception e){
			logger.info("exception ---- > "+ e.getMessage());
		}
		
		if (response == null)
			return "";
		
		return response.toString();
	}
	
	private String executeRule(String request, List <Rule>  ruleList) throws Exception{
		
		JSONParser parser = null;
		JSONObject json = null;
		JSONObject response = null;
		
		parser = new JSONParser();
		json = (JSONObject) parser.parse(request);			
		//execute the rules
		response = (JSONObject) new GateHandler().execute(json, ruleList);
				
		return response.toString();
	}
	
	
	/**
	 * Return the rules with respect to microservice and hook indicator (PRE/POST)
	 * 
	 * @param @param microserviceName
	 * @param @param hookInd
	 * @param @return
	 * @param @throws Exception 
	 * @return List<Rule>
	 *
	 */
	private List <Rule> getRules(String microserviceName, String hookInd) throws Exception{
		
		/*
		RuleMetadataPGDAO ruleMetadataDAO = (RuleMetadataPGDAO) new DAOFactory().getDAO(
				new DBFactory().getDBService(RuleConstants.POSTGRESQL)
				.getService(), RuleConstants.RULE_METADATA); 
		List <Rule> ruleList = null;
		
		if(hookInd == null || hookInd.equals(""))
			ruleList = ruleMetadataDAO.getRules(microserviceName); 
		else
			ruleList = ruleMetadataDAO.getRules(microserviceName, hookInd);
		
		*/
		
		//To mock the input
		List ruleList = new ArrayList <Rule> ();
		Rule rule = new Rule();
		
		
		rule.setRuleName("ForecastRule");
		rule.setRuleType("Static");
		ruleList.add(rule);
		
		rule = new Rule();
		rule.setRuleName("CountRule");
		rule.setRuleType("Static");
		ruleList.add(rule);
		
		
		return ruleList;
	}
}
