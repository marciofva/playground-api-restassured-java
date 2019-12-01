package com.api.playground.test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import com.api.playground.dataprovider.DataProviderService;
import com.api.playground.enums.URL;
import io.restassured.http.ContentType;

public class ServiceTest extends BaseTest{
	
	private static final String SCHEMA_ALL_SERVICE = "service-schema/allServices.json";
	private static final String SCHEMA_DETAILED_SERVICE = "service-schema/serviceById.json";
	

	@Test
	public void returnAllServices() {
				
	  	given()
		    .accept(ContentType.JSON)
		.when()
			.get(URL.SERVICE.getValue())
		.then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_ALL_SERVICE));
	}

	
	@Test(dataProviderClass = DataProviderService.class, dataProvider = "findById")
	public void findServices(String id) {
				
	  	given()
		    .accept(ContentType.JSON)
		    .pathParam("id", id)
		.when()
			.get(URL.SERVICE.getValue().concat("/{id}"))
		.then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_DETAILED_SERVICE))
			.body("id", equalTo(Integer.parseInt(id)));
	}
}