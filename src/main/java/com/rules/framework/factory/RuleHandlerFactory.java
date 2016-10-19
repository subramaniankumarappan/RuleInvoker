package com.rules.framework.factory;

/**
* File  : RuleHandlerFactory.java
* Description          : This RuleHandlerFactory class is a factory handler which creates 
* 						 handlers on request.
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/

import com.rules.common.RuleInvokerConstants;
import com.rules.exception.RuleInvokerBaseException;
import com.rules.framework.IRule;
import com.rules.staticrules.CountRule;
import com.rules.staticrules.CustomerSearchRule;
import com.rules.staticrules.ForecastRule;

public class RuleHandlerFactory {
	
	public IRule createHandler(String handlerName, String handlerType) throws Exception{
		
		if (RuleInvokerConstants.STATIC_HANDLER.equalsIgnoreCase(handlerType))
			return createStaticHandler(handlerName);
		else
			return createDynamicHandler(handlerName);
		
		
	}

	private IRule createDynamicHandler(String handlerName) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	private IRule createStaticHandler(String handlerName) throws Exception{
		
		if (RuleInvokerConstants.CUSTOMER_SEARCH_HANDLER.equalsIgnoreCase(handlerName))
			return new CustomerSearchRule();
		else if (RuleInvokerConstants.FORECAST_RULE.equalsIgnoreCase(handlerName))
			return new ForecastRule();
		else if (RuleInvokerConstants.COUNT_RULE.equalsIgnoreCase(handlerName))
			return new CountRule();
		throw new RuleInvokerBaseException("No class available for the Rule - "+handlerName);
	}

}
