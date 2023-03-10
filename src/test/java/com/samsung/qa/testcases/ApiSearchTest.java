package com.samsung.qa.testcases;

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

public class ApiSearchTest extends TestBase{
	//Declare objects of repository and test util classes to be used later in the current class
	//Initialize Sheetname having test data
	TestUtil testUtil;
	String sheetName = "ApiCoordinateDetails";
	SoftAssert softAssert;
	
	//Initialize objects of the required classes to access their methods
	@BeforeClass
	public void setUp() {
		testUtil = new TestUtil();
		softAssert = new SoftAssert();
	}
	
	//Used for storing cell value from the test data sheet
	@DataProvider
	public Object[][] getTestData() throws InvalidFormatException{
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}
	
	// Coordinates with API Test
	@Test(priority=1, dataProvider="getTestData")
	public void geolocationCoordinatesTest(String keyname, String latitude, String longitude) {
		//API call to get coordinates of location
		RestAssured.baseURI="https://ipgeolocation.abstractapi.com/";
		RequestSpecification req= RestAssured.given();
		Response response = req.get("/v1/?api_key="+prop.getProperty("apikey"));
		
		//Storing API response as String
		String jsonString = response.getBody().asString();
		
		//Extract the coordinates and store it a double variable
		double latValue = response.jsonPath().getDouble("latitude");
		double longValue = response.jsonPath().getDouble("longitude");
		
		//Soft assert to validate against the expected values
		softAssert.assertEquals(latValue, Double.parseDouble(latitude), "the latitude coordinates doesnt match");
		softAssert.assertEquals(longValue, Double.parseDouble(longitude), "the longitude coordinates doesnt match");
		softAssert.assertAll();
		
	}


}
