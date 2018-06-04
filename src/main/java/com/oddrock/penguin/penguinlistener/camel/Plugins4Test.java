package com.oddrock.penguin.penguinlistener.camel;

import org.apache.camel.Exchange;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component("plugins4Test")
public class Plugins4Test {
	private static Logger logger = Logger.getLogger(Plugins4Test.class);
	
	public String test(Exchange exchange, String payload){
		logger.warn("has enter test method");
		return "Test success!";
	}
	
}
