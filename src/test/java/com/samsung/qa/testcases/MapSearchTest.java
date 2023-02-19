package com.samsung.qa.testcases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.samsung.qa.base.TestBase;
import com.samsung.qa.repository.SearchLocation;
import com.samsung.qa.util.TestUtil;


public class MapSearchTest extends TestBase{
	
	SearchLocation searchLocation;
	TestUtil testUtil;
	String sheetName = "CoordinateDetails";
	
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
	
	
//	@Test(priority=1, dataProvider="getTestData")  // Add User Test
//	public void coordinatesTest(String keyname, Double latitude, Double longitude) {
//		searchLocation.searchPlace(keyname);
//		Map<String, Double> map = searchLocation.extractCoordinates(keyname);
//		Double latitudeValue = searchLocation.getCoordinatesValue(map, keyname);
//		Assert.assertEquals(latitudeValue, latitude, "the latitude coordinates doesnt match");
//		Assert.assertEquals(latitudeValue, latitude, "the latitude coordinates doesnt match");
//	}
	
	
	@Test(priority=1, dataProvider="getTestData")  // Add User Test
	public void coordinatesTest(String keyname, String latitude, String longitude) throws InterruptedException {
		searchLocation.searchPlace(keyname);
		Thread.sleep(10);
		List<SearchLocation> list = searchLocation.extractCoordinatesList(keyname);
		System.out.println(keyname+" "+latitude+" "+longitude);
		try{
			Iterator<SearchLocation> it=list.iterator();
			while (it.hasNext()) {
				SearchLocation search=it.next();
				System.out.println(search.getLatitude()+" "+search.getLongitude());
				//Assert.assertEquals(search.getLatitude(), Double.parseDouble(longitude), "the latitude coordinates doesnt match"+" :keyname");
//				Assert.assertEquals(search.getLongitude(), Double.parseDouble(latitude), "the longitude coordinates doesnt match"+" :keyname");
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getStackTrace());
		}
		
		finally {
			System.out.println("Finally block executed");
		}
		
	}
	
//	@AfterClass
//	public void tearDown() {
//		driver.quit();
//	}

}
