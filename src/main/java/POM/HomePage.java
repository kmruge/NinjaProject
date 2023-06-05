package POM;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver=null;
	public HomePage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//span[text()='My Account']")
	WebElement clickOnAccount;
	
	@FindBy(xpath="//a[text()='Login']")
	WebElement clickOnLogin;
	
	@FindBy(xpath="//a[text()='Register']")
	WebElement clickOnRegister;
	
	public void clickOnAccount()
	{
		clickOnAccount.click();
	}
	public void clickOnLogin()
	{
		clickOnLogin.click();
	}
	public void clickOnRegister()
	{
		clickOnRegister.click();
	}
}
