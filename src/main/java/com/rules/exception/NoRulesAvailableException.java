package com.rules.exception;

/**
* File  : NoRulesAvailableException.java
* Description          : This NoRulesAvailableException class is an exception class for No rules available  
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/
public class NoRulesAvailableException extends RuleInvokerBaseException {

	public NoRulesAvailableException(String message){
		super(message);
		
	}
	
	public NoRulesAvailableException(){
		super("No rules in input");
		
	}
}
