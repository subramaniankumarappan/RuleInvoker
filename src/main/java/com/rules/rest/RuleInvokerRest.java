package com.rules.rest;

import java.util.ArrayList;

import javax.inject.Named;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.rules.controller.RuleInvoker;
import com.rules.framework.GateHandler;

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
	
	
	@GET
	@Path("execute")
	@Produces(MediaType.APPLICATION_JSON)
	public String executeRule(@QueryParam("input") String request, 
			@QueryParam("msName") String microserviceName,
			@QueryParam("hookInd") String hookInd)  {
		
		System.out.println("executeRule");
		System.out.println("INPUT ---- > "+ request);
		System.out.println("MicroService Name ---- > "+ microserviceName);
		

		if (microserviceName == null || microserviceName.equals(""))
			throw new IllegalArgumentException("The 'Micro Service Name' parameter must not be null or empty");
		
		
		
		return new RuleInvoker().executePrehookRule(request, microserviceName);
	}
	
	
	@GET
	@Path("prehook")
	@Produces(MediaType.APPLICATION_JSON)
	public String executePrehookRule(@QueryParam("input") String request, 
			@QueryParam("msName") String microserviceName)  {
		
		System.out.println("PreHook");
		System.out.println("INPUT ---- > "+ request);
		System.out.println("MicroService Name ---- > "+ microserviceName);
		

		if (microserviceName == null || microserviceName.equals(""))
			throw new IllegalArgumentException("The 'Micro Service Name' parameter must not be null or empty");
		
		return new RuleInvoker().executePrehookRule(request, microserviceName);
	}
	
	@GET
	@Path("posthook")
	@Produces(MediaType.APPLICATION_JSON)
	public String executePosthookRule(@QueryParam("input") String request, 
			@QueryParam("msName") String microserviceName)  {
		
		System.out.println("PostHook");
		
		System.out.println("INPUT ---- > "+ request);
		System.out.println("MicroService Name ---- > "+ microserviceName);
		

		if (microserviceName == null || microserviceName.equals(""))
			throw new IllegalArgumentException("The 'Micro Service Name' parameter must not be null or empty");
		
		return new RuleInvoker().executePosthookRule(request, microserviceName);
	}
	
}
