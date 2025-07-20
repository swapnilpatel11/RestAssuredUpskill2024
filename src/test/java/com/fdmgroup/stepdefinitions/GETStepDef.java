package com.fdmgroup.stepdefinitions;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GETStepDef {
	@Given("I perform GET operation for {string}")
	public void i_perform_get_operation_for(String url) {
		
		// Arrange
	    Hooks.utilities.setUrl(url);
	    Hooks.utilities.setMethod("GET");
	    
	    // Act
	    
	    Hooks.response = Hooks.utilities.executeAPI();
	}

	@Then("I should see the author names")
	public void i_should_see_the_author_names(DataTable table) {
	    
		// Getting Data Table
		var data = table.asList(String.class);
		
		//Assert
		List<String> authors = Hooks.response.getBody().jsonPath().get("author");
		System.out.println(authors);
		
		assertThat(authors, equalTo(data));
		assertThat(Hooks.response.getStatusCode(), equalTo(200));
		
	}

	@Given("I perform GET with path parameter for {string}")
	public void i_perform_get_with_path_parameter_for(String url, DataTable table) {
	    // Getting Data from the table
		var data = table.asMaps();
		
		// Setting up the Path Params
		HashMap<String,String> pathParams = new HashMap<>();
		for (Map<String,String> items: data) {
			pathParams.put("postid", items.get("postid"));
		}
		
		// Arrange
		Hooks.utilities.setMethod("GET");
		Hooks.utilities.setUrl(url);
		Hooks.utilities.addPathParams(pathParams);
		
		// Act
		Hooks.response = Hooks.utilities.executeAPI();
				
	}

	@Then("I should see the author name as {string}")
	public void i_should_see_the_author_name_as(String authorName) {
	    // Assert
		assertThat(Hooks.response.getBody().jsonPath().get("author"),equalTo(authorName));
	}

	@Given("I perfrom GET with query parameter for {string}")
	public void i_perfrom_get_with_query_parameter_for(String url, DataTable table) {
	    // Getting data from the table
		var data = table.asMaps();
		
		// Setting up the Query Params
		HashMap<String, String> queryParams = new HashMap<>();
		for (Map<String,String> items:data) {
			queryParams.put("id", items.get("id"));
			queryParams.put("title", items.get("title"));
		}
		
		// Arrange
		Hooks.utilities.setMethod("GET");
		Hooks.utilities.setUrl(url);
		Hooks.utilities.addQueryParams(queryParams);
		
		// Act
		Hooks.response = Hooks.utilities.executeAPI();
	}

}
