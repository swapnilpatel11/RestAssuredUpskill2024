package com.fdmgroup.stepdefinitions;

import java.util.HashMap;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PUTStepDef {
	@Given("I ensure to perform POST operation for {string} with body as")
	public void i_ensure_to_perform_post_operation_for_with_body_as(String url, DataTable table) {
		// Getting Data from the table
		var data = table.asMaps();

		// Setting up the Path Params and body
		HashMap<String, String> body = new HashMap<>();
		HashMap<String, String> pathParams = new HashMap<>();

		for (Map<String, String> items : data) {
			body.put("id", items.get("id"));
			body.put("title", items.get("title"));
			body.put("author", items.get("author"));
		}

		// Arrange
		Hooks.utilities.setMethod("POST");
		Hooks.utilities.setUrl(url);
		Hooks.utilities.addBody(body);
		Hooks.utilities.addPathParams(pathParams);

		// Act
		Hooks.response = Hooks.utilities.executeAPI();
	}

	@Given("I perform PUT operation for {string}")
	public void i_perform_put_operation_for(String url, DataTable table) {
		// Getting Data from the table
		var data = table.asMaps();

		// Setting up the Path Params & Body
		HashMap<String, String> pathParams = new HashMap<>();
		HashMap<String, String> body = new HashMap<>();
		for (Map<String, String> items : data) {
			pathParams.put("postid", items.get("id"));

			body.put("id", items.get("id"));
			body.put("title", items.get("title"));
			body.put("author", items.get("author"));
		}

		// Arrange
		Hooks.utilities.setMethod("PUT");
		Hooks.utilities.setUrl(url);
		Hooks.utilities.addBody(body);
		Hooks.utilities.addPathParams(pathParams);

		// Act
		Hooks.response = Hooks.utilities.executeAPI();
	}

	@Then("I should see the body with title as {string}")
	public void i_should_see_the_body_with_title_as(String title) {
		// Assert
		assertThat(Hooks.response.getBody().jsonPath().get("title"), equalTo(title));
	}

}
