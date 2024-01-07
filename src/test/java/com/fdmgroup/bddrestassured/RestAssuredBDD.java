package com.fdmgroup.bddrestassured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.containsString;
//import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;

import io.restassured.http.ContentType;

public class RestAssuredBDD {

	public static void simpleGETPosts() {
		var response = given().contentType(ContentType.JSON).when().get("http://localhost:3000/posts").then();
		System.out.println(response.log().body());

		// Assertions we put in then method as expected result
		var responseAssertion = given().contentType(ContentType.JSON).when().get("http://localhost:3000/posts").then()
				.body("author",
						containsInAnyOrder("typicode", "java-author", "selenium-author", "sql-author", "myauthor"));
		System.out.println(responseAssertion.log().body());
	}

	public static void performPathParameter() {
		var response = given().contentType(ContentType.JSON).with().pathParam("post", 1).when()
				.get("http://localhost:3000/posts/{post}").then().body("author", containsString("typicode"));

		System.out.println(response.log().body());
	}

	public static void performQueryParameter() {
		var response = given().contentType(ContentType.JSON).with().queryParam("id", 1)
				.queryParam("title", "json-server").when().get("http://localhost:3000/posts").then()
				.body("author", hasItem("typicode")).statusCode(200);

		System.out.println(response.log().body());
	}
	
	// POST

	public static void performPostWithPathParameter() {
		String body = """
					{
				"body":"samama"
					}
					""";
		given().contentType(ContentType.JSON).pathParam("profileNo", 3).with().body(body).when()
				.post("http://localhost:3000/posts/{profileNo}/comments").then().body("body", is("samama"));

	}

	public static void performPutPost() {
		String body = """
				{

				"title":"your title",
				"author":"your author"
				}
				""";
		// update record with body
		given().contentType(ContentType.JSON).body(body).when().put("http://localhost:3000/posts/4").then()
				.body("author", is("your author"));

	}

	public static void performDeletePost() {
		given().contentType(ContentType.JSON).when().delete("http://localhost:3000/posts/5").then();

	}

}
