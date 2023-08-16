package Different_Class_of_Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SuccessPageAfterAccountCreate {

	WebDriver driver;
	
	@FindBy(xpath  = "//h1[normalize-space()='Your Account Has Been Created!']")
	private WebElement accountCreationConfirm;
	
	public SuccessPageAfterAccountCreate(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String AccountCreationConfirmText() {
		 String confirmText  = accountCreationConfirm.getText();
		 return confirmText;
	}
}
