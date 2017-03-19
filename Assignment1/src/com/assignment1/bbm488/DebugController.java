package com.assignment1.bbm488;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class DebugController {
	
	private String log;
	
	public DebugController()
	{
		this.log = "";
	}
	
	public void addLog(String log)
	{
		this.log += log+"\n";
	}
	
	public String getLog()
	{
		return log;
	}
	
	public static String outAllParamaters(HttpServletRequest request)
	{
		String log = "";
		log +="---------- Request Parameters ----------\n";
		Enumeration<String> params = request.getParameterNames(); 
		while(params.hasMoreElements())
		{
			String paramName = params.nextElement();
			log+=paramName+":"+"(" +request.getParameter(paramName)+")" +"\n";
		}
		
		return log;
	}
	
}
