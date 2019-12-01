package com.api.playground.dataprovider;

import org.testng.annotations.DataProvider;

public class DataProviderProducts {
	
	@DataProvider(name = "findById")
	public static Object[][] findById() {
		return new Object[][] {{"43900"},
							   {"150115"}};
		}
	
	@DataProvider(name = "limit")
	public static Object[][] limit() {
		return new Object[][] {{"1"},
							   {"2"}};
		}
	
	@DataProvider(name = "skip")
	public static Object[][] skip() {
		return new Object[][] {{"200"},
							   {"25000"}};
		}
	
	@DataProvider(name = "findByType")
	public static Object[][] findByType() {
		return new Object[][] {{"HardGood"}};
		}
	
	@DataProvider(name = "price")
	public static Object[][] price() {
		return new Object[][] {{"1"}};
		}
	
	@DataProvider(name = "TwoPrices")
	public static Object[][] TwoPrices() {
		return new Object[][] {{"0.99", "1.99"}};
		}
	
	@DataProvider(name = "shipping")
	public static Object[][] shipping() {
		return new Object[][] {{"10"}};
		}
	
	@DataProvider(name = "differentType")
	public static Object[][] differentType() {
		return new Object[][] {{"HardGood", "Software"}};
		}
	
	@DataProvider(name = "categoryName")
	public static Object[][] categoryName() {
		return new Object[][] {{"Coffee Pods"}};
		}
	
	@DataProvider(name = "categoryId")
	public static Object[][] categoryId() {
		return new Object[][] {{"abcat0106004"}};
		}
}
