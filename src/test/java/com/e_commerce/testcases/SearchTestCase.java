package com.e_commerce.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import BasePackage.BaseClass;
import Different_Class_of_Test.HomePage;
import Different_Class_of_Test.SearchPage;


public class SearchTestCase extends BaseClass {

	SearchPage searchpage;
	HomePage homepage ;
	public WebDriver driver;
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		loadPropertiesFile();
		driver =intilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();
		homepage = new HomePage(driver);
		
	}

	@AfterMethod	
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
	
	@Test(priority = 1)
	public void verifySearchWithValidProduct() {

		searchpage=homepage.enterProductForSearch(DataProp.getProperty("ValidProduct"));
		
//		homepage.enterProductInSearchField(DataProp.getProperty("ValidProduct"));
//		searchpage = homepage.ClickOnSearchButton();	
		//SearchPage searchpage = new SearchPage(driver);
		Assert.assertTrue(searchpage.AfterSearchingConfirmingValidProductIsExist(),
				"valid product is not shown after searching");
	}

	@Test(priority = 2)
	public void verifySearchWithInValidProduct() {
		
		searchpage = homepage.enterProductForSearch(DataProp.getProperty("invalidProduct"));

		//String actualSearchMessag=searchpage.AfterSearchingConfirmingInValidProductValidity();
		Assert.assertEquals(searchpage.AfterSearchingConfirmingInValidProductValidity(),
				DataProp.getProperty("InvalidProductWarningMessage"),
				"Test should be failed for searching");
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifySearchWithValidProduct","verifySearchWithInValidProduct"})
	public void VerifySearchFieldWithoutInputtingvalue() {
		
		searchpage = homepage.ClickOnSearchButton();
		Assert.assertEquals(searchpage.AfterSearchingConfirmingInValidProductValidity(),
				DataProp.getProperty("InvalidProductWarningMessage"),
				"Test should be failed for searching");
		
	}
}
