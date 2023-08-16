package Different_Class_of_Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//i[@class='fa fa-user']")
	private WebElement myAccountDropMenu;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	private WebElement loginOption;
	
	@FindBy(xpath = "//a[contains(text(),'Register')]")
	private WebElement RegistrationOption;
	
	@FindBy(name = "search")
	private WebElement searchField;
	
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	private WebElement SearchButton;

	//Creating constructor 
	public HomePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Actions
	public void clickOnMyAccount(){
		myAccountDropMenu.click();
	}
	
	public LoginPage clickOnLoginOption() {
		loginOption.click();
	//New Code added for loginpage
		return new LoginPage(driver);
	}
	
//	Public clickOnMyAccount & Loginpage merged in navigatetologinpage for Login Page	
	public LoginPage navigateToLoginPage() {
		myAccountDropMenu.click();
		loginOption.click();
		//New Code added for loginpage
		return new LoginPage(driver);
	}
	
	public RegistrationPage clickOnRegistrationOption() {
		RegistrationOption.click();
		//New Code added for RegistrationPage
		return new RegistrationPage(driver);
	}
	
	
	public RegistrationPage navigateToRegisterPage() {
		myAccountDropMenu.click();
		RegistrationOption.click();
		//New Code added for RegistrationPage
		return new RegistrationPage(driver);
		
	}
	
	public void enterProductInSearchField(String searchfield) {
		searchField.sendKeys(searchfield);
	}
	
	public SearchPage ClickOnSearchButton() {
		SearchButton.click();
		//New Code added for searchpage
		return new SearchPage(driver);
	}
	
	public SearchPage enterProductForSearch(String searchfield) {
		searchField.sendKeys(searchfield);
		SearchButton.click();
		//New Code added for searchpage
		return new SearchPage(driver);
	}
	
	
	
}
