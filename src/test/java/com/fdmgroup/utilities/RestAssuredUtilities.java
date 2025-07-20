package com.fdmgroup.utilities;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import io.restassured.specification.RequestSpecification;

public class RestAssuredUtilities {
	
	private static RestAssuredUtilities utilities;
	private RequestSpecBuilder builder = new RequestSpecBuilder();
	private String url;
	private String method;
	
	
	private RestAssuredUtilities() {
		super();
		this.url = "http://localhost:3000";
	}
	
	public static RestAssuredUtilities getInstance() {
		if(utilities == null) {
			utilities = new RestAssuredUtilities();
		}
		return utilities;
	}
	
	public void resetUtilities() {
		utilities = null;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String endPoint) {
		this.url = url + endPoint;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	// Building blocks
	public void addQueryParams(Map<String,String> params) {
		builder.addQueryParams(params);
	}
	
	public void addPathParams(Map<String,String> params) {
		builder.addPathParams(params);
	}
	
	public void addBody(Map<String,String> body) {
		builder.setBody(body);
	}
	
	public ResponseOptions<Response> executeAPI(){
		// Build the request specification from the builder object
	//	RequestSpecification requestSpec = builder.build();
		
		// Construct the request with the specification
		RequestSpecification request = RestAssured.given();
		request.contentType(ContentType.JSON);
		request.spec(builder.build());
		
		if(method.equals("POST")) {
			return request.post(this.url);
		} else if (method.equals("GET")) {
			return request.get(this.url);
		} else if (method.equals("DELETE")) {
			return request.delete(this.url);
		} else if (method.equals("PUT")) {
			return request.put(this.url);
		}
		return null;
	}
	
}
