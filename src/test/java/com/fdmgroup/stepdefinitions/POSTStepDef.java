package com.fdmgroup.stepdefinitions;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class POSTStepDef {
	@Given("I perform POST operation for {string}")
	public void i_perform_post_operation_for(String url) {
	    // Setting up the Body
		HashMap<String,String> body = new HashMap<>();
		body.put("author", "Ulala");
		body.put("title", "Title Ulala");
		
		// Arrange
		Hooks.utilities.setMethod("POST");
		Hooks.utilities.setUrl(url);
		Hooks.utilities.addBody(body);
		
		// Act
		Hooks.response = Hooks.utilities.executeAPI();
	}

	@Given("I perform POST operation for {string} with")
	public void i_perform_post_operation_for_with(String url,DataTable table) {
	   // Getting Data from the table
		var data = table.asMaps();
		
		// Setting up the Path Params and body
		HashMap<String, String> body = new HashMap<>();
		HashMap<String,String> pathParams = new HashMap<>();
		
		for(Map<String,String> items : data) {
			body.put("body", items.get("name"));
			pathParams.put("profileNo", items.get("profile"));
		}
		
		// Arrange
		Hooks.utilities.setMethod("POST");
		Hooks.utilities.setUrl(url);
		Hooks.utilities.addBody(body);
		Hooks.utilities.addPathParams(pathParams);
		
		// Act
		Hooks.response = Hooks.utilities.executeAPI();
	}

	@Then("I should see the body has name as {string}")
	public void i_should_see_the_body_has_name_as(String name) {
	    // Assert
		assertThat(Hooks.response.getBody().jsonPath().get("body"),equalTo(name));
	}

}
