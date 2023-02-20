package com.samsung.qa.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.samsung.qa.base.TestBase;
import com.samsung.qa.repository.SearchLocation;
import com.samsung.qa.util.TestUtil;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class MapSearchTest extends TestBase{
	
	SearchLocation searchLocation;
	TestUtil testUtil;
	String sheetName = "CoordinateDetails";
	SoftAssert softAssert= new SoftAssert();
	
	
	public MapSearchTest(){
		super();
	}
	
	@BeforeClass
	public void setUp() {
		initialization();
		searchLocation=new SearchLocation();
		testUtil = new TestUtil();
	}
	
	@DataProvider
	public Object[][] getTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	
	@Test(enabled =true,priority=1, dataProvider="getTestData")  // Add User Test
	public void coordinatesTest(String keyname, String latitude, String longitude) {
		try {
		searchLocation.searchPlace(keyname);
		Map<String, Double> map = searchLocation.extractCoordinates(keyname);
		double latitudeValue = map.get("latitude");
		double longitudeValue = map.get("longitude");
		Assert.assertEquals(latitudeValue, Double.parseDouble(latitude), "the latitude coordinates doesnt match");
		Assert.assertEquals(longitudeValue, Double.parseDouble(longitude), "the longitude coordinates doesnt match");
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
	}
	
	
	@Test(enabled =false,priority=1, dataProvider="getTestData")
	public void geolocationCoordinatesTest(String keyname, String latitude, String longitude) {
		RestAssured.baseURI="https://ipgeolocation.abstractapi.com/";
		RequestSpecification req= RestAssured.given();
		Response response = req.get("/v1/?api_key="+prop.getProperty("apikey"));
		String jsonString = response.getBody().asString();
		double latValue = response.jsonPath().getDouble("latitude");
		double longValue = response.jsonPath().getDouble("longitude");
		softAssert.assertEquals(latValue, Double.parseDouble(latitude), "the latitude coordinates doesnt match");
		softAssert.assertEquals(longValue, Double.parseDouble(longitude), "the longitude coordinates doesnt match");
		softAssert.assertAll();
		
	}
	
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
