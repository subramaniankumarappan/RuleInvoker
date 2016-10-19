package com.rules.framework;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rules.common.RuleInvokerConstants;
import com.rules.common.pojo.Rule;
import com.rules.data.service.PostgreSQLDBService;
import com.rules.exception.NoRulesAvailableException;
import com.rules.framework.factory.RuleHandlerFactory;

/**
* File  : GateHandler.java
* Description          : This GateHandler class is a gate handler which takes care of
*                        executing all the rules for the given request. Both request and 
*                        list of rules are provided as inputs to this class.
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/

public class GateHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(GateHandler.class);
	/**
	 * 
	 * @param @param request
	 * @param @param ruleList
	 * @param @return
	 * @param @throws Exception 
	 * @return Object
	 *
	 */
	 
	public Object execute(Object request, List <Rule> ruleList) throws Exception{
		
		if (ruleList == null || ruleList.size()==0)
			throw new NoRulesAvailableException();
		
		RuleHandlerFactory ruleHandlerFactory = new RuleHandlerFactory();
		IRule handler = null;
		Rule rule = null;
		//process the input for all the rules
		for (int i=0; i < ruleList.size(); i++){
			rule = ruleList.get(i);
			logger.info("Executing rule  ---- > "+ rule.getRuleName());
			handler = ruleHandlerFactory.createHandler(rule.getRuleName(), rule.getRuleType());
			//execute preprocess
			request = handler.executePreprocess(request);
			//execute actual rule
			request = handler.execute(request);
			//execute postprocess
			request = handler.executePostprocess(request);
			
		}
		return request;
	}

}
