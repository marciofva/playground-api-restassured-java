package com.api.playground.test;

import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import com.api.playground.enums.URL;
import static io.restassured.RestAssured.given;

public class HealthCheckTest extends BaseTest {
	
	@Test
	public void healthCheckAuth() {
				
		given()
	    	.accept(ContentType.JSON)
	    .when()
			.get(URL.HEALTH.getValue())
		.then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK);
	}
}
