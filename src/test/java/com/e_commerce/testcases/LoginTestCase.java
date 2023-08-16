package com.e_commerce.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import BasePackage.BaseClass;
import Different_Class_of_Test.AccountPage;
import Different_Class_of_Test.HomePage;
import Different_Class_of_Test.LoginPage;


public class LoginTestCase extends BaseClass {
	LoginPage loginpage;
	AccountPage accountpage;
	public WebDriver driver;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.http.factory", "jdk-http-client");
		loadPropertiesFile();
		driver =intilizeBrowserAndOpenApplicationURL(prop.getProperty("browserName"));
		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

		HomePage homepage = new HomePage(driver);
//		homepage.clickOnMyAccount();
//		loginpage=homepage.clickOnLoginOption()
//		
		loginpage=homepage.navigateToLoginPage();
		
	}

	@AfterMethod	
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
	
	@Test(priority = 1,dataProvider = "DataSupplyingFromExcel")
	public void LoginWithValidCredentialsUsingDataProviderFromExcelFile(String email, String password) {
//		loginpage.enterEmailAddress(email);
//		loginpage.enterPassword(password);
//		accountpage = loginpage.clickOnLoginButton();
		accountpage =loginpage.login(email, password);
		
		Assert.assertTrue(accountpage.VerifyingDisplayedOfAccountInformation(), DataProp.getProperty("TestCaseFail"));
		
	}
	
	@DataProvider(name = "DataSupplyingFromExcel")
	public Object[][] SupplyEmailPasswordDataFromExcelFile() {
		
		//In Excel file if sheet-name is changed that file have to copy and paste again the project
		Object [] [] data = BaseClass.getTestDataFromExcel("Login");
		return data;	
	}
	
	@Test(priority = 0)
	public void LoginWithValidCredentials() {
		
		//LoginPage loginpage = new LoginPage(driver);
		
//		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
//		loginpage.enterPassword(prop.getProperty("validPassword"));
//		accountpage= loginpage.clickOnLoginButton();
//		
		accountpage =loginpage.login(prop.getProperty("validEmail"), prop.getProperty("validPassword"));
			
		Assert.assertTrue(accountpage.VerifyingDisplayedOfAccountInformation(), DataProp.getProperty("TestCaseFail"));
	}
		
	@Test(priority = 2)
	public void LoginWithInValidCredentials() {
		
//		loginpage.enterEmailAddress(generateWithEmailTimeStamp());
//		loginpage.enterPassword(DataProp.getProperty("Invalid_password"));
//		loginpage.clickOnLoginButton();
		
		
		loginpage.login(generateWithEmailTimeStamp(),DataProp.getProperty("Invalid_password"));
		
		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Wrong Page");
		
		//String actualWarningMessage = loginpage.displayedEmailPasswordNotMatchingWarningMessageText();
		//String expectedWarningMessage = DataProp.getProperty("WarningMessageValidity");
		Assert.assertTrue(loginpage.displayedEmailPasswordNotMatchingWarningMessageText().contains(DataProp.getProperty("WarningMessageValidity")),DataProp.getProperty("TestCaseFail"));
		
	}

	@Test(priority = 3)
	public void LoginWithValidUsernameInvalidpassword() {

//		loginpage.enterEmailAddress(prop.getProperty("validEmail"));
//		loginpage.enterPassword(DataProp.getProperty("Invalid_password"));
//		loginpage.clickOnLoginButton();
		
		loginpage.login(prop.getProperty("validEmail"), DataProp.getProperty("Invalid_password"));
		

		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Wrong Page");
		//String actualWarningMessage = loginpage.displayedEmailPasswordNotMatchingWarningMessageText();
		//String expectedWarningMessage = DataProp.getProperty("WarningMessageValidity");
		Assert.assertTrue(loginpage.displayedEmailPasswordNotMatchingWarningMessageText().contains(DataProp.getProperty("WarningMessageValidity")),DataProp.getProperty("TestCaseFail"));
	}
	
	@Test(priority = 4)
	public void LoginWithInValidUsernameValidpassword() {

//		loginpage.enterEmailAddress(generateWithEmailTimeStamp());
//		loginpage.enterPassword(prop.getProperty("validPassword"));
//		loginpage.clickOnLoginButton();
//		
		loginpage.login(prop.getProperty("validEmail"), DataProp.getProperty("Invalid_password"));


		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Wrong Page");
		//String actualWarningMessage = loginpage.displayedEmailPasswordNotMatchingWarningMessageText();
		//String expectedWarningMessage = DataProp.getProperty("WarningMessageValidity");
		Assert.assertTrue(loginpage.displayedEmailPasswordNotMatchingWarningMessageText().contains(DataProp.getProperty("WarningMessageValidity")),DataProp.getProperty("TestCaseFail"));
		
	}
	
	@Test(priority = 5)
	public void LoginWithoutProvidingCredentials() {

		
		loginpage.clickOnLoginButton();
		

		//Assert.assertTrue(driver.findElement(By.linkText("Edit your account information")).isDisplayed(), "Wrong Page");
		//String actualWarningMessage = loginpage.displayedEmailPasswordNotMatchingWarningMessageText();
	    //String expectedWarningMessage = DataProp.getProperty("WarningMessageValidity");
		Assert.assertTrue(loginpage.displayedEmailPasswordNotMatchingWarningMessageText().contains(DataProp.getProperty("WarningMessageValidity")),DataProp.getProperty("TestCaseFail"));
	}	
	

}
