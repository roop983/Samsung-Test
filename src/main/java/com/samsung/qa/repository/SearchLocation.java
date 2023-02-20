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
	
	
	@FindBy(xpath="//button[@data-value='Directions']")
	WebElement directions;
	
	
	
	public SearchLocation() {
		this.driver = driver;
		//this.wait = wait;
		PageFactory.initElements(driver, this);
		wait = new FluentWait<WebDriver>(driver)
				// Check for condition in every 2 seconds
				.pollingEvery(Duration.ofSeconds(2))
				// Till time out i.e. 30 seconds
				.withTimeout(Duration.ofSeconds(30))
				.ignoring(NoSuchElementException.class);
		
	}	
	
	
	public void searchPlace(String searchValue) {
		driver.get(prop.getProperty("url"));
		wait.until(ExpectedConditions.elementToBeClickable(toSearchBoxInput));
		toSearchBoxInput.sendKeys(searchValue);
		searchButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(directions));
	}
	
	
	public Map<String, Double> extractCoordinates(String keyname){
		Map<String, Double> map = new HashMap<String, Double>();
		String getCoordinatesURL = driver.getCurrentUrl();
		double latValue = Double.parseDouble(getCoordinatesURL.substring(getCoordinatesURL.indexOf('@')+1, getCoordinatesURL.lastIndexOf('/')).split(",")[0]);
		double longValue = Double.parseDouble(getCoordinatesURL.substring(getCoordinatesURL.indexOf('@')+1, getCoordinatesURL.lastIndexOf('/')).split(",")[1]);
		map.put("latitude", latValue);
		map.put("longitude", longValue);
		return map;
	}
	


	
}
