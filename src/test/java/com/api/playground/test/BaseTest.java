package com.api.playground.test;

import org.apache.http.HttpStatus;
import org.testng.annotations.BeforeClass;
import com.api.playground.configuration.Configuration;
import io.restassured.RestAssured;

public class BaseTest implements HttpStatus{
	
	private Configuration configuration;
	
	@BeforeClass
	public void setURI() {
		configuration = new Configuration();
		RestAssured.baseURI = configuration.getUri();
		RestAssured.port = configuration.getPort();
	}
}
