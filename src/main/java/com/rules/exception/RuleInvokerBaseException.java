package com.rules.exception;

/**
* File  : RuleInvokerBaseException.java
* Description          : This RuleInvokerBaseException class is base exception class for Rule Invoker  
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/
public class RuleInvokerBaseException extends Exception{

	
	public RuleInvokerBaseException (){
		super("Some Exception in RuleInvoker");
	}
	public RuleInvokerBaseException (String message){
		super(message);
	}
}
