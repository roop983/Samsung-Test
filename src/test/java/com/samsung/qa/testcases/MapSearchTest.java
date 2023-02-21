package com.samsung.qa.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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
	///Declare objects of repository and test util classes to be used later in the current class
	//Initialize Sheetname having test data
	SearchLocation searchLocation;
	TestUtil testUtil;
	String sheetName = "MapsCoordinateDetails";
	SoftAssert softAssert;
	
	
	public MapSearchTest(){
		super();
	}
	
	//Invoke Parent class initialize method for driver setup and initialize objects of the required classes to access their methods
	@BeforeClass
	public void setUp() {
		initialization();
		searchLocation=new SearchLocation();
		testUtil = new TestUtil();
		softAssert = new SoftAssert();
	}
	//Used for storing cell value from the test data sheet
	@DataProvider
	public Object[][] getTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	//Google Maps Search Test
	@Test(priority=1, dataProvider="getTestData")  
	public void coordinatesTest(String keyname, String latitude, String longitude) {
		try {
			
		// Search for the place in google maps
		searchLocation.searchPlace(keyname);
		
		//Extract coordinates and store in a map
		Map<String, Double> map = searchLocation.extractCoordinates(keyname);
		double latitudeValue = map.get("latitude");
		double longitudeValue = map.get("longitude");
		
		//Assert to validate against the expected coordinates values
		Assert.assertEquals(latitudeValue, Double.parseDouble(latitude), "the latitude coordinates doesnt match");
		Assert.assertEquals(longitudeValue, Double.parseDouble(longitude), "the longitude coordinates doesnt match");
		}
		
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
	}
	
	//Close the driver window
	@AfterClass
	public void tearDown() {
		driver.quit();
	}

}
