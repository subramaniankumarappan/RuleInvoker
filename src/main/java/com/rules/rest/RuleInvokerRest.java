package com.rules.rest;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import com.rules.common.constants.RuleConstants;
import com.rules.common.pojo.RuleParams;
import com.rules.controller.RuleInvoker;


/**
* File  : RuleInvokerRest.java
* Description          : This RuleInvokerRest class is a rest class which accepts 
* 						 request for Rule Invoker.
* Revision History :
* Version      Date            		Author       Reason
* 0.1          12-October-2016      595251  	Initial version
*/

@Named
@Path("/RuleInvoker")
public class RuleInvokerRest {
	
	
	@POST
	@Path("/execute")
	@Produces(MediaType.APPLICATION_JSON)
	public String executeRule(@QueryParam("input") String request, 
			@QueryParam("msName") String microserviceName,
			@QueryParam("hookInd") String hookInd,
			@QueryParam("sortFields") String sortFields,
			@QueryParam("sortOrder") String sortOrder,
			@QueryParam("selectFields") String selectFields,
			@QueryParam("size") String size,
			@QueryParam("searchField") String searchField,		
			@QueryParam("expression") String expression ) throws Exception  {
		
		System.out.println("executeRule");
		
		
		RuleParams ruleParams = new RuleParams();
		ruleParams.setMsName(microserviceName);
		ruleParams.setSortFields(sortFields);
		ruleParams.setSortOrder(sortOrder);
		ruleParams.setSize(size);
		ruleParams.setSelectFields(selectFields);
		ruleParams.setSearchField(searchField);
		ruleParams.setExpression(expression);
		
		if (RuleConstants.PREHOOK_REST.equalsIgnoreCase(hookInd))
			ruleParams.setHookInd(RuleConstants.PREHOOK);
		else
			ruleParams.setHookInd(RuleConstants.POSTHOOK);
		
		System.out.println("INPUT ---- > "+ request);
		System.out.println("ruleParams ---- > "+ ruleParams);

		if (microserviceName == null || microserviceName.equals(""))
			throw new IllegalArgumentException("The 'Micro Service Name' parameter must not be null or empty");
		
		
		
		return new RuleInvoker().executeRule(request, ruleParams);
	}
	
	
	@GET
	@Path("prehook")
	@Produces(MediaType.APPLICATION_JSON)
	public String executePrehookRule(@QueryParam("input") String request, 
			@QueryParam("msName") String microserviceName,
			@QueryParam("sortFields") String sortFields,
			@QueryParam("sortOrder") String sortOrder,
			@QueryParam("selectFields") String selectFields,
			@QueryParam("size") String size,
			@QueryParam("searchField") String searchField,		
			@QueryParam("expression") String expression ) throws Exception  {
		
		System.out.println("PreHook");
		
		RuleParams ruleParams = new RuleParams();
		ruleParams.setMsName(microserviceName);
		ruleParams.setSortFields(sortFields);
		ruleParams.setSortOrder(sortOrder);
		ruleParams.setSize(size);
		ruleParams.setSelectFields(selectFields);
		ruleParams.setSearchField(searchField);
		ruleParams.setExpression(expression);
		ruleParams.setHookInd(RuleConstants.PREHOOK);

		System.out.println("INPUT ---- > "+ request);
		System.out.println("ruleParams ---- > "+ ruleParams);
		
		if (microserviceName == null || microserviceName.equals(""))
			throw new IllegalArgumentException("The 'Micro Service Name' parameter must not be null or empty");
		
		return new RuleInvoker().executeRule(request, ruleParams);
	}
	
	@POST
	@Path("posthook")
	@Produces(MediaType.APPLICATION_JSON)
	public String executePosthookRule(@QueryParam("input") String request, 
			@QueryParam("msName") String microserviceName,
			@QueryParam("sortFields") String sortFields,
			@QueryParam("sortOrder") String sortOrder,
			@QueryParam("selectFields") String selectFields,
			@QueryParam("size") String size,
			@QueryParam("searchField") String searchField,		
			@QueryParam("expression") String expression ) throws Exception  {
		
		System.out.println("PostHook");
		
		RuleParams ruleParams = new RuleParams();
		ruleParams.setMsName(microserviceName);
		ruleParams.setSortFields(sortFields);
		ruleParams.setSortOrder(sortOrder);
		ruleParams.setSize(size);
		ruleParams.setSelectFields(selectFields);
		ruleParams.setSearchField(searchField);
		ruleParams.setExpression(expression);
		ruleParams.setHookInd(RuleConstants.POSTHOOK);

		System.out.println("INPUT ---- > "+ request);
		System.out.println("ruleParams ---- > "+ ruleParams);
		

		if (microserviceName == null || microserviceName.equals(""))
			throw new IllegalArgumentException("The 'Micro Service Name' parameter must not be null or empty");
		
		return new RuleInvoker().executeRule(request, ruleParams);
	}
	
}
