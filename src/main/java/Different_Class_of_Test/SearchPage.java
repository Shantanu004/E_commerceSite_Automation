package Different_Class_of_Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

	WebDriver driver;
	
	@FindBy(xpath = "//a[normalize-space()='HP LP3065']")
	private WebElement ValidProductExistingValidity;
	
	@FindBy(xpath = "//p[contains(text(),'There is no product that matches the search criter')]")
	private WebElement InvalidProductValidity;
	
	
	
	
	public SearchPage(WebDriver driver){
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public boolean AfterSearchingConfirmingValidProductIsExist() {
		boolean ExistingproductDisplayStatus= ValidProductExistingValidity.isDisplayed();
		return ExistingproductDisplayStatus;
	}
	
	public String AfterSearchingConfirmingInValidProductValidity() {
		String InvalidproductDisplayStatus= InvalidProductValidity.getText();
		return InvalidproductDisplayStatus;
	}
	
	
	
	
}
