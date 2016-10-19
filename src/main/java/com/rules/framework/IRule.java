package com.rules.framework;

/**
* File  : IRule.java
* Description          : This IRule interface is base interface for the rules in Rule Invoker  
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/

public interface IRule {
	
	public Object executePreprocess(Object request);
	public Object execute(Object request);
	public Object executePostprocess(Object request);

}
