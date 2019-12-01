package com.api.playground.test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;
import com.api.playground.dataprovider.DataProviderProducts;
import com.api.playground.enums.URL;
import io.restassured.http.ContentType;

public class ProductTest extends BaseTest{
	
	private static final String SCHEMA_ALL_PRODUCTS = "product-schema/allProducts.json";
	private static final String SCHEMA_DETAILED_PRODUCT = "product-schema/productById.json";
	
	@Test
	public void returnAllProducts() {
				
	  	given()
		    .accept(ContentType.JSON)
		.when()
			.get(URL.PRODUCT.getValue())
		.then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_ALL_PRODUCTS));
	}
	
	
	@Test(dataProviderClass = DataProviderProducts.class, dataProvider = "findById")
	public void findProductById(String id) {
				
		given()
	    	.accept(ContentType.JSON)
	    	.pathParam("id", id)
	   .when()
	   		.get(URL.PRODUCT.getValue().concat("/{id}"))
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_DETAILED_PRODUCT))
			.body("id", equalTo(Integer.parseInt(id)));
	}
	
	
	@Test(dataProviderClass = DataProviderProducts.class, dataProvider = "limit")
	public void getAllProductsLimitTo1(String limit) {
				
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("$limit", limit)
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_ALL_PRODUCTS))
			.body("limit", equalTo(Integer.parseInt(limit)));
	}
	
	@Test
	public void sortByLowestPrice() {
		
		final float LOWEST_PRICE = 0.01f;
				
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("$sort[price]", 1)
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_ALL_PRODUCTS))
			.body("data.price[0]", equalTo(LOWEST_PRICE));
	}
	
	
	@Test(dataProviderClass = DataProviderProducts.class, dataProvider = "skip")
	public void getAllProductsWithSkip(String skip) {
				
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("$skip", skip)
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_ALL_PRODUCTS))
			.body("skip", equalTo(Integer.parseInt(skip)));
	}
	
	
	@Test
	public void sortByHighestPrice() {
				
		final float HIGHEST_PRICE = 27999.98f;
						
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("$sort[price]", -1)
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_ALL_PRODUCTS))
			.body("data.price[0]", equalTo(HIGHEST_PRICE));
	}


	@Test
	public void showOnlyNameAndPrice() {
				
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("$select[]", "name")
	    	.queryParam("$select[]", "price")
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body("data[0]", hasKey("name"))
			.body("data[0]", hasKey("price"))
			.body("data[0].size()", equalTo(2));
	}
	
	
	@Test(dataProviderClass = DataProviderProducts.class, dataProvider = "findByType")
	public void findByType(String type) {
				
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("type", type)
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_ALL_PRODUCTS))
			.body("data", hasItems(hasEntry("type", type)));
	}
	
	
	@Test(dataProviderClass = DataProviderProducts.class, dataProvider = "price")
	public void getProductWithPriceLessOrEqual(String price) {
						
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("price[$lte]", price)
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body("data.price", everyItem(lessThanOrEqualTo(Float.parseFloat(price))));
	}
	
	
	@Test(dataProviderClass = DataProviderProducts.class, dataProvider = "TwoPrices")
	public void getProductsPassingTwoPrices(String firstPrice, String secondPrice) {
						
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("price[$in]", firstPrice)
	    	.queryParam("price[$in]", secondPrice)
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body("data.price",hasItems(Float.parseFloat(firstPrice), Float.parseFloat(secondPrice)));
	}
	
	
	@Test(dataProviderClass = DataProviderProducts.class, dataProvider = "shipping")
	public void getProductWithShippingPriceGreaterThan(String shipping) {
						
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("shipping[$gt]", shipping)
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body("data.price", everyItem(greaterThan(Float.parseFloat(shipping))));
	}
	
	
	@Test(dataProviderClass = DataProviderProducts.class, dataProvider = "differentType")
	public void getProductWithDifferentType(String firstType, String secondType) {
				
		given()
	    	.accept(ContentType.JSON)
	    	.queryParam("type[$nin][]", firstType)
	    	.queryParam("type[$nin][]", secondType)
	   .when()
	   		.get(URL.PRODUCT.getValue())
	   .then()
			.log()
			.ifValidationFails()
			.statusCode(HttpStatus.SC_OK)
			.body(matchesJsonSchemaInClasspath(SCHEMA_ALL_PRODUCTS))
			.body("data", not(hasItems(firstType, secondType)));
	}
}
