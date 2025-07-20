package com.fdmgroup.stepdefinitions;



import com.fdmgroup.utilities.RestAssuredUtilities;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;

public class Hooks {
	protected static RestAssuredUtilities utilities;
	protected static ResponseOptions<Response> response;
	
	@Before 
	public void setup() {
		// Setting up Utilities
		utilities = RestAssuredUtilities.getInstance();
		
	}
	
	@After
	public void tearDown() {
		// Deleting the utilities object after each scenario
		utilities.resetUtilities();
	}
	
	
}
