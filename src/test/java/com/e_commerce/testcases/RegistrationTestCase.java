package com.e_commerce.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import BasePackage.BaseClass;
import Different_Class_of_Test.HomePage;
import Different_Class_of_Test.RegistrationPage;
import Different_Class_of_Test.SuccessPageAfterAccountCreate;


public class RegistrationTestCase extends BaseClass {
	RegistrationPage registration;
	SuccessPageAfterAccountCreate confirmingaccountcreatepage;
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
		registration= homepage.navigateToRegisterPage();

	}

	@AfterMethod	
	public void teardown() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
	
	@Test(priority = 1)
	public void FillingUpTheAllRequiredFieldsWithValidValue() throws InterruptedException {
		//DataProp value coming from TestData_Configuration
		//prop value coming from Base_Configuartion
		
		//RegistrationPage registration = new RegistrationPage(driver);
		
		confirmingaccountcreatepage = registration.registerWithRequiredField(DataProp.getProperty("FirstName"),
				DataProp.getProperty("LastName"), generateWithEmailTimeStamp(),
				DataProp.getProperty("Telephone"),prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		
//		registration.enterFirstName(DataProp.getProperty("FirstName"));
//		registration.enterLastName(DataProp.getProperty("LastName"));
//		registration.enterEmail(generateWithEmailTimeStamp());
//		registration.enterTelephone(DataProp.getProperty("Telephone"));
//		registration.enterPassword(prop.getProperty("validPassword"));
//		registration.enterConfirmPassword(prop.getProperty("validPassword"));
		
//		Thread.sleep(3000);
//		registration.InfoAgree();
//		Thread.sleep(3000);
//		confirmingaccountcreatepage=registration.RegistrationFormSubmit(); //When clicking on 
		//registration Submit button it goes to Account create success page. 
		
		
		//handlecheckBox
//		WebElement privacyPolicy= driver.findElement(By.name("agree"));
//		JavascriptExecutor js = (JavascriptExecutor)driver;
//		js.executeScript("arguments[0].click()", privacyPolicy);
//		
		//SuccessPageAfterAccountCreate confirmingaccountcreate = new SuccessPageAfterAccountCreate(driver);
		
//		String actualWarningMessage = confirmingaccountcreatepage.AccountCreationConfirmText();
//		String expectedWarningMessage = DataProp.getProperty("RegistrationValidityConfirm");
		Assert.assertEquals(confirmingaccountcreatepage.AccountCreationConfirmText(),DataProp.getProperty("RegistrationValidityConfirm"), DataProp.getProperty("TestCaseFailForRegistration"));
	}
	
	@Test(priority = 2)
	public void FillingUpTheAllFieldsWithValidValue() throws InterruptedException {

		confirmingaccountcreatepage= registration.registerWithAllField(DataProp.getProperty("FirstName"),
				DataProp.getProperty("LastName"), generateWithEmailTimeStamp(),
				DataProp.getProperty("Telephone"),prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		
		Assert.assertEquals(confirmingaccountcreatepage.AccountCreationConfirmText(),
				DataProp.getProperty("RegistrationValidityConfirm"),"Registration is not Success");
	}
	
	@Test(priority = 3)
	public void verifyTheRegistrarWithExistingEmail() throws InterruptedException {
		
	registration.registerWithAllField(DataProp.getProperty("FirstName"),
				DataProp.getProperty("LastName"), prop.getProperty("validEmail"),
				DataProp.getProperty("Telephone"),prop.getProperty("validPassword"),
				prop.getProperty("validPassword"));
		
		Assert.assertEquals(registration.WarningForExistingEmail(),DataProp.getProperty("ExistingEmailValidity"),"Registration is not Success");
		
	}
	
	@Test(priority = 4)
	public void VerifyallRequiredFieldsWithoutFillingUpTheFields() {
		
		registration.RegistrationFormSubmit();
		
		Assert.assertTrue(registration.displayStatusOfWarningMessages
				(DataProp.getProperty("warningMessageForPrivacyPolicy"),
				DataProp.getProperty("warningMessageForFirstName"),
				DataProp.getProperty("warningMessageForLastName"),
				DataProp.getProperty("warningMessageForEmail"),
				DataProp.getProperty("TelephoneWarning"),
				DataProp.getProperty("PasswordWarning")));
		
//		Assert.assertEquals(registration.WarningForPrivacyPolicy(),DataProp.getProperty("warningMessageForPrivacyPolicy"),"Test Case fail for InValid Privacy Policy");
//		Assert.assertEquals(registration.WarningForFirstName(),DataProp.getProperty("warningMessageForFirstName"),"Test Case Fail for invalid first name");
//		Assert.assertEquals(registration.WarningForLastName(),DataProp.getProperty("warningMessageForLastName"),"Test Case fail for invalid last name");
//		Assert.assertEquals(registration.WarningForEmail(),DataProp.getProperty("warningMessageForEmail"),"Test case fail for invalid email");
//		Assert.assertEquals(registration.WarningForTelephone(),DataProp.getProperty("TelephoneWarning"),"Test Case fail for invalid telephone");	
//		//String actualPasswordWarningMessage = registration.WarningForPassword();
//		Assert.assertEquals(registration.WarningForPassword(),DataProp.getProperty("PasswordWarning"),"Test Case fail for invalid password");
	    
		System.out.println("Code up to coming in password");
		
	}
	
	@Test(priority = 5)
	public void VerifyConfirmPasswordByFillingAllFieldsWithoutThisfield() throws InterruptedException {
		
		registration.registerWithoutConfirmPasswordField(DataProp.getProperty("FirstName"),
				DataProp.getProperty("LastName"), generateWithEmailTimeStamp(),
				DataProp.getProperty("Telephone"),prop.getProperty("validPassword"));
		
		
		Assert.assertEquals(registration.WarningForConfirmPassword(),
				DataProp.getProperty("confirmPasswordFieldValidity"),
				DataProp.getProperty("TestCaseFailForRegistration"));
		
	}
		
	
}
