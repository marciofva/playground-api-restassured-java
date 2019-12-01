package com.api.playground.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderService {
	
	@DataProvider(name = "findById")
	public static Object[][] findById() {
		return new Object[][] {{"2"},
							   {"8"}};
		}
	
	
	@DataProvider(name = "createService")
	public static Object[][] createService() {
		return new Object[][] {{"2"},
							   {"8"}};
		}
}
