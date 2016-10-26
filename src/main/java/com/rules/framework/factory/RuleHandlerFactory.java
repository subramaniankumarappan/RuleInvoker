package com.rules.framework.factory;

/**
* File  : RuleHandlerFactory.java
* Description          : This RuleHandlerFactory class is a factory handler which creates 
* 						 handlers on request.
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/

import java.util.Map;

import com.rules.common.RuleInvokerConstants;
import com.rules.exception.RuleInvokerBaseException;
import com.rules.framework.BusinessRule;
import com.rules.framework.IRule;
import com.rules.staticrules.CountRule;
import com.rules.staticrules.CustomerSearchRule;
import com.rules.staticrules.SearchRule;
import com.rules.staticrules.SelectFieldsRule;
import com.rules.staticrules.SortRule;

public class RuleHandlerFactory {
	
	public IRule createHandler(String handlerName, String handlerType, Map optionalfields) throws Exception{
		
		if (RuleInvokerConstants.STATIC_HANDLER.equalsIgnoreCase(handlerType))
			return createStaticHandler(handlerName, optionalfields);
		else
			return createDynamicHandler(handlerName, optionalfields);
		
		
	}

	private IRule createDynamicHandler(String handlerName, Map optionalFields) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	private IRule createStaticHandler(String handlerName, Map optionalFields) throws Exception{
		BusinessRule handler =null;
		
		if (RuleInvokerConstants.CUSTOMER_SEARCH_HANDLER.equalsIgnoreCase(handlerName))
			handler = new CustomerSearchRule();
		else if (RuleInvokerConstants.SEARCH_RULE.equalsIgnoreCase(handlerName))
			handler = new SearchRule();
		else if (RuleInvokerConstants.COUNT_RULE.equalsIgnoreCase(handlerName))
			handler = new CountRule();
		else if (RuleInvokerConstants.SELECT_FIELDS_RULE.equalsIgnoreCase(handlerName))
			handler =  new SelectFieldsRule();
		else if (RuleInvokerConstants.SORT_RULE.equalsIgnoreCase(handlerName))
			handler =  new SortRule();
		
		if (handler == null)
			throw new RuleInvokerBaseException("No class available for the Rule - "+handlerName);
		
		handler.setOptionalFields(optionalFields);
		return handler;
	}

}
