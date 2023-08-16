package Different_Class_of_Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

	WebDriver driver;
	
	@FindBy(id = "input-email")
	private  WebElement emailAddressField;
	
	@FindBy(id="input-password")
	private WebElement passwordfield;
	
	@FindBy(xpath="//input[@value='Login']")
	private WebElement loginButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement emailPasswordNotMatching;
	
	
	public LoginPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public void enterEmailAddress(String emailText) {
		emailAddressField.sendKeys(emailText);
	}
	
	public void enterPassword(String password) {
		passwordfield.sendKeys(password);
	}
	
	public AccountPage clickOnLoginButton() {
		loginButton.click();
		return new AccountPage(driver);
	}
	
	//Alternative code for enteremailaddress, enterpassword,accountpage method 
	public AccountPage login(String emailText, String password) {
		emailAddressField.sendKeys(emailText);
		passwordfield.sendKeys(password);
		loginButton.click();
		return new AccountPage(driver);
	}
	
	public String displayedEmailPasswordNotMatchingWarningMessageText() {
		
		String warningText = emailPasswordNotMatching.getText();
		return warningText;
	}
}