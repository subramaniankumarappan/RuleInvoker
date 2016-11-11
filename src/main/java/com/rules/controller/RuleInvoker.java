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
import com.rules.common.pojo.RuleParams;
import com.rules.common.util.RuleUtil;
import com.rules.data.dao.postgres.RuleMetadataPGDAO;
import com.rules.data.factory.DAOFactory;
import com.rules.data.factory.DBFactory;
import com.rules.framework.GateHandler;

/**
 * @author 595251
 *
 */
public class RuleInvoker {
	
	private static final Logger logger = LoggerFactory.getLogger(RuleInvoker.class);
	
	/**
	 * 
	 * @param @param request
	 * @param @param ruleParams
	 * @param @return
	 * @param @throws Exception 
	 * @return String
	 *
	 */
	public String executeRule(String request, RuleParams ruleParams) throws Exception{
		
		List <Rule> ruleList  = 	null; 
		String response = null;
		ruleList = getQueryStringRules(ruleParams);
		try{
			if(RuleConstants.PREHOOK.equalsIgnoreCase(ruleParams.getHookInd()))
				ruleList.addAll(getDBRules(ruleParams.getMsName(), RuleConstants.PREHOOK)); 
			else if(RuleConstants.POSTHOOK.equalsIgnoreCase(ruleParams.getHookInd()))
				ruleList.addAll(getDBRules(ruleParams.getMsName(), RuleConstants.POSTHOOK)); 
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

	
	
	/**
	 * 
	 * @param @param request
	 * @param @param ruleList
	 * @param @return
	 * @param @throws Exception 
	 * @return String
	 *
	 */
	private String executeRule(String request, List <Rule>  ruleList) throws Exception{
		
		logger.info("ruleList ---- > "+ ruleList);
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
	 * @param @param ruleParams
	 * @param @return 
	 * @return List<Rule>
	 *
	 */
	private List<Rule> getQueryStringRules(RuleParams ruleParams){
		//create all static rules here
		
		List<Rule> ruleList = new ArrayList ();
		Rule rule = null;
		
		//check and add count rule
		if (! RuleUtil.isEmpty(ruleParams.getSize())){
			rule = new Rule();
			rule.setEnabled(RuleConstants.ENABLED_PROCEED_Y);
			rule.setRuleName(RuleInvokerConstants.COUNT_RULE);
			rule.setProceedInd(RuleConstants.ENABLED_PROCEED_Y);
			rule.setRuleType(RuleInvokerConstants.STATIC_HANDLER);
			rule.setExecSeq("-1");
			rule.setOptionalfields(RuleConstants.SIZE + RuleConstants.EQUALS + ruleParams.getSize() );
			
			ruleList.add(rule);
		}
		//check and add search rule
		if (! RuleUtil.isEmpty(ruleParams.getSearchField()) && !RuleUtil.isEmpty(ruleParams.getExpression())){
			rule = new Rule();
			rule.setEnabled(RuleConstants.ENABLED_PROCEED_Y);
			rule.setRuleName(RuleInvokerConstants.SEARCH_RULE);
			rule.setProceedInd(RuleConstants.ENABLED_PROCEED_Y);
			rule.setRuleType(RuleInvokerConstants.STATIC_HANDLER);
			rule.setExecSeq("-1");
			StringBuilder optionalFields = new StringBuilder();
			optionalFields.append(RuleConstants.SEARCH_FIELD).append(RuleConstants.EQUALS)
						  .append(ruleParams.getSearchField()).append(RuleConstants.SEPARATOR)
						  .append(RuleConstants.EXPRESSION).append(RuleConstants.EQUALS)
				              .append(ruleParams.getExpression()).append(RuleConstants.SEPARATOR);
			rule.setOptionalfields(optionalFields.toString());
			
			ruleList.add(rule);
		}
		//check and add sort rule
		if (! RuleUtil.isEmpty(ruleParams.getSortFields())){
			rule = new Rule();
			rule.setEnabled(RuleConstants.ENABLED_PROCEED_Y);
			rule.setRuleName(RuleInvokerConstants.SORT_RULE);
			rule.setProceedInd(RuleConstants.ENABLED_PROCEED_Y);
			rule.setRuleType(RuleInvokerConstants.STATIC_HANDLER);
			rule.setExecSeq("-1");
			StringBuilder optionalFields = new StringBuilder();
			optionalFields.append(RuleConstants.SORT_FIELDS).append(RuleConstants.EQUALS)
						  .append(ruleParams.getSortFields()).append(RuleConstants.SEPARATOR);
						
			if (! RuleUtil.isEmpty(ruleParams.getSortOrder()))
				optionalFields.append(RuleConstants.SORT_ORDER).append(RuleConstants.EQUALS)
				              .append(ruleParams.getSortOrder()).append(RuleConstants.SEPARATOR);
			rule.setOptionalfields(optionalFields.toString());
			
			ruleList.add(rule);
		}
		//check and add select/filter fields rule
		if (! RuleUtil.isEmpty(ruleParams.getSelectFields())){
			rule = new Rule();
			rule.setEnabled(RuleConstants.ENABLED_PROCEED_Y);
			rule.setRuleName(RuleInvokerConstants.SELECT_FIELDS_RULE);
			rule.setProceedInd(RuleConstants.ENABLED_PROCEED_Y);
			rule.setRuleType(RuleInvokerConstants.STATIC_HANDLER);
			rule.setExecSeq("-1");
			StringBuilder optionalFields = new StringBuilder();
			optionalFields.append(RuleConstants.SELECT_FIELDS).append(RuleConstants.EQUALS)
						  .append(ruleParams.getSelectFields()).append(RuleConstants.SEPARATOR);
						  
			rule.setOptionalfields(optionalFields.toString());
			
			ruleList.add(rule);
		}
		System.out.println("Querystring Rule Size --->" +ruleList.size());	
		System.out.println("Querystring Rules --->" +ruleList);	
		return ruleList;
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
	private List <Rule> getDBRules(String microserviceName, String hookInd) throws Exception{
		
		
		RuleMetadataPGDAO ruleMetadataDAO = (RuleMetadataPGDAO) new DAOFactory().getDAO(
				new DBFactory().getDBService(RuleConstants.POSTGRESQL)
				.getService(), RuleConstants.RULE_METADATA); 
		List <Rule> ruleList = null;
		
		if(hookInd == null || hookInd.equals(""))
			ruleList = ruleMetadataDAO.getRules(microserviceName); 
		else
			ruleList = ruleMetadataDAO.getRules(microserviceName, hookInd);
		
		
		/*
		//To mock the input
		List ruleList = new ArrayList <Rule> ();
		Rule rule = new Rule();
		
		
		rule.setRuleName("SearchRule");
		rule.setRuleType("Static");
		rule.setOptionalfields("FIELD=cust_nbr;EXPRESSION=");
		ruleList.add(rule);
		/*
		rule.setRuleName("SortRule");
		rule.setRuleType("Static");
		rule.setOptionalfields("FIELDS=cust_nbr,row_inact_dt;");
		ruleList.add(rule);
		
		
		rule = new Rule();
		rule.setRuleName("CountRule");
		rule.setRuleType("Static");
		//rule.setOptionalfields("COUNT=2");;
		ruleList.add(rule);
		
		
		rule = new Rule();
		rule.setRuleName("SelectFieldsRule");
		rule.setRuleType("Static");
		rule.setOptionalfields("FIELDS=cust_nbr,B,row_inact_dt");
		ruleList.add(rule);
		*/
		
		return ruleList;
	}
}
