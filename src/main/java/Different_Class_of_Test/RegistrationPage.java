package Different_Class_of_Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

	WebDriver driver;
	
	@FindBy(id = "input-firstname")
	private WebElement firstName;
	
	@FindBy(id = "input-lastname")
	private WebElement lastName;
	
	@FindBy(id = "input-email")
	private WebElement inputemail;
	
	@FindBy(id = "input-telephone")
	private WebElement telephone;
	
	@FindBy(id = "input-password")
	private WebElement passwordText;
	
	@FindBy(id = "input-confirm")
	private WebElement confirmPassword;
	
	@FindBy(xpath ="//label[normalize-space()='Yes']")
	private WebElement NewletterSubscibe;
	
	@FindBy(name = "agree")
	private WebElement infoSubmitagree;
	
	@FindBy(xpath = "//input[@value='Continue']")
	private WebElement submit;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement existingEmailWarning;
	
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	private WebElement PrivacyPolicyWarning;
	
	@FindBy(xpath = "//div[contains(text(),'First Name must be between 1 and 32 characters!')]")
	private WebElement warningForFirstName;
	
	@FindBy(xpath = "//div[contains(text(),'Last Name must be between 1 and 32 characters!')]")
	private WebElement warningForLastName;
	
	@FindBy(xpath = "//div[contains(text(),'E-Mail Address does not appear to be valid!')]")
	private WebElement warningForEmailAddress;
	
	@FindBy(xpath = "//div[contains(text(),'Telephone must be between 3 and 32 characters!')]")
	private WebElement warningForTelephoneNo;
	
	@FindBy(xpath = "//div[contains(text(),'Password must be between 4 and 20 characters!')]")
	private WebElement warningForPassword;
	
	@FindBy(xpath = "//div[contains(text(),'Password confirmation does not match password!')]")
	private WebElement warningForConfirmPassword;
	
	public RegistrationPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void enterFirstName(String firstname) {

		firstName.sendKeys(firstname);
	} 

	public void enterLastName(String lastname) {

		lastName.sendKeys(lastname);
	} 

	public void enterEmail(String email) {

		inputemail.sendKeys(email);
	} 
	public void enterTelephone(String telephoneNo) {

		telephone.sendKeys(telephoneNo);
	} 
	public void enterPassword(String password) {

		passwordText.sendKeys(password);
	} 
	
	public void enterConfirmPassword(String confirmpassword) {

		confirmPassword.sendKeys(confirmpassword);
	} 
	
	public void NewsLetterSubscribeConfirm() {
		NewletterSubscibe.click();
	}
	
	public void InfoAgree() {
		
		infoSubmitagree.click();	
	}

	public SuccessPageAfterAccountCreate RegistrationFormSubmit() {
		submit.click();
		return new SuccessPageAfterAccountCreate(driver);
	}
	
	public SuccessPageAfterAccountCreate registerWithRequiredField(String firstname, String lastname, String email,String telephoneNo,String password, String confirmpassword ) 
	{
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		inputemail.sendKeys(email);
		telephone.sendKeys(telephoneNo);
		passwordText.sendKeys(password);
		confirmPassword.sendKeys(confirmpassword);
		infoSubmitagree.click();
		submit.click();
		return new SuccessPageAfterAccountCreate(driver);		
	}
	
	public SuccessPageAfterAccountCreate registerWithAllField(String firstname, String lastname, String email,String telephoneNo,String password, String confirmpassword ) 
	{
		
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		inputemail.sendKeys(email);
		telephone.sendKeys(telephoneNo);
		passwordText.sendKeys(password);
		confirmPassword.sendKeys(confirmpassword);
		NewletterSubscibe.click();
		infoSubmitagree.click();
		submit.click();
		return new SuccessPageAfterAccountCreate(driver);		
	}
	
	public SuccessPageAfterAccountCreate registerWithoutConfirmPasswordField(String firstname, String lastname, String email,String telephoneNo,String password) 
	{
		
		firstName.sendKeys(firstname);
		lastName.sendKeys(lastname);
		inputemail.sendKeys(email);
		telephone.sendKeys(telephoneNo);
		passwordText.sendKeys(password);
		NewletterSubscibe.click();
		infoSubmitagree.click();
		submit.click();
		return new SuccessPageAfterAccountCreate(driver);		
	}
	
	public String WarningForExistingEmail() {
	 String emailWaring=existingEmailWarning.getText();
	 return emailWaring; 
	}
	
	public String WarningForPrivacyPolicy() {
		String privacypolicy = PrivacyPolicyWarning.getText();
		return privacypolicy;
	}
	
	public String WarningForFirstName() {
		String firstname = warningForFirstName.getText();
		return firstname;
	}
	
	public String WarningForLastName() {
		String lastname = warningForLastName.getText();
		return lastname;
	}
	
	public String WarningForEmail() {
		String email = warningForEmailAddress.getText();
		return email;
	}
	
	public String WarningForTelephone() {
		String telephone = warningForTelephoneNo.getText();
		return telephone;
	} 
	
	public String WarningForPassword() {
		String password = warningForPassword.getText();
		return password;
	}
	
	public String WarningForConfirmPassword() {
		String confirmpassword = warningForConfirmPassword.getText();
		return confirmpassword;
	}
	
	public boolean displayStatusOfWarningMessages(String expectedPrivacyPolicyWarning, String expectedFirstNameWarning,
		String expectedLastNameWarning, String expectedEmailWarning, String expectedTelephoneWarning, String expectedPasswordWarning) {
		
		boolean privacyPolicyWarningStatus = PrivacyPolicyWarning.getText().equals(expectedPrivacyPolicyWarning);
		boolean firstNameWarningStatus = warningForFirstName.getText().equals(expectedFirstNameWarning);
		boolean lastNameWaringStatus = warningForLastName.getText().equals(expectedLastNameWarning);
		boolean emailWarningStatus = warningForEmailAddress.getText().equals(expectedEmailWarning);
		boolean telephoneWarningStatus = warningForTelephoneNo.getText().equals(expectedTelephoneWarning);
		boolean passwordWarningStatus = warningForPassword.getText().equals(expectedPasswordWarning);
		
		return privacyPolicyWarningStatus && firstNameWarningStatus && lastNameWaringStatus && 
				emailWarningStatus && telephoneWarningStatus && passwordWarningStatus;
			   	
	}
	
	
	
	
	
}
