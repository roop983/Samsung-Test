package com.samsung.qa.repository;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.samsung.qa.base.TestBase;


public class SearchLocation extends TestBase{
	
	@FindBy(xpath="//input[@id='searchboxinput']")
	WebElement toSearchBoxInput;
	
	@FindBy(xpath="//button[@id='searchbox-searchbutton']")
	WebElement searchButton;
	
	String keyname;
	Double latValue;
	Double longValue;
	
	
	public SearchLocation() {
		this.driver = driver;
		this.wait = wait;
		PageFactory.initElements(driver, this);
		
	}	
	
	public SearchLocation(String keyname, Double latValue, Double longValue) {
		this.keyname=keyname;
		this.latValue=latValue;
		this.longValue=longValue;
		
	}	
	
	public Double getLatitude() {
		return latValue;
	}
	
	public Double getLongitude() {
		return longValue;
	}
	
	
	public void searchPlace(String searchValue) {
		//Thread.sleep(5000);
		driver.get(prop.getProperty("url"));
		wait = new FluentWait<WebDriver>(driver)
				// Check for condition in every 2 seconds
				.pollingEvery(Duration.ofSeconds(2))
				// Till time out i.e. 30 seconds
				.withTimeout(Duration.ofSeconds(30))
				.ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.elementToBeClickable(toSearchBoxInput));
		toSearchBoxInput.sendKeys(searchValue);
		searchButton.click();
	}
	
	
	public List<SearchLocation> extractCoordinatesList(String keyname){
		List<SearchLocation> list =new ArrayList<SearchLocation> ();
		String getCoordinatesURL = driver.getCurrentUrl();
		
		double latValue = Double.parseDouble(getCoordinatesURL.substring(getCoordinatesURL.indexOf('@')+1, getCoordinatesURL.lastIndexOf('/')).split(",")[0]);
		double longValue = Double.parseDouble(getCoordinatesURL.substring(getCoordinatesURL.indexOf('@')+1, getCoordinatesURL.lastIndexOf('/')).split(",")[1]);
		System.out.println(latValue);
		System.out.println(longValue);
		SearchLocation l1=new SearchLocation(keyname,latValue,longValue);
		list.add(l1);
		return list;
	}
	
//	public Map<String, Double> extractCoordinates(String keyname){
//		Map<String, Double> map = new HashMap<String, Double>();
//		String getCoordinatesURL = driver.getCurrentUrl();
//		double latValue = Double.parseDouble(getCoordinatesURL.substring(getCoordinatesURL.indexOf('@')+1, getCoordinatesURL.lastIndexOf('/')).split(",")[0]);
//		double longValue = Double.parseDouble(getCoordinatesURL.substring(getCoordinatesURL.indexOf('@')+1, getCoordinatesURL.lastIndexOf('/')).split(",")[1]);
//		map.put("latitude for :"+keyname, latValue);
//		map.put("longitude for :"+keyname, longValue);
//		return map;
//	}
	
//	public Double getCoordinatesValue(Map<String, Double> m, String keyname){
//		Double coordinateValue = 0.0;
//		for (Map.Entry<String, Double> mEntry :  m.entrySet()) {
//			if (mEntry.getKey().contains(keyname)) {
//				coordinateValue = mEntry.getValue();
//			}
//		}
//		return coordinateValue;
//	}


	
}
