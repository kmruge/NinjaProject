package TestCase;

import java.lang.reflect.Method;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.sun.jmx.mbeanserver.Util;

import Base.driverClass;
import POM.HomePage;
import Utility.Utilise1;
import locators.Login_Locators;
@Listeners(ListenerPack.MyListener.class)
public class Login_Test extends Utilise1 {
	public Login_Test()
	{
		//Calling(Loading) property file from utilise1
		super();
	}
	@BeforeMethod
	void setUp()
	{
		//Initialize the browser from the Utilize class
		driverInitialize(pro.getProperty("BrowserName"));
		HomePage hPage=new HomePage(driver);
		hPage.clickOnAccount();
		hPage.clickOnLogin();
	}
	@AfterMethod
	void tearDown()
	{
		closeDriver();
	}
	@Test(priority=1,dataProvider="dataExtraction")
	void verifyLoginWithValidCredential(String email,String password,Method name)
	{
		commonLoginPageAction(email,password);
		VerificationAssertion("My Orders",Login_Locators.ValidCredential,name);
	}
	@Test(priority=2)
	void verifyLoginWithInvalidCredential(Method name)
	{
		commonLoginPageAction(CredentialEmail(),Loginpro.getProperty("PassWord"));
		VerificationAssertion(Loginpro.getProperty("invalidCredentialAlert"),Login_Locators.InvalidCredential,name);
	}
	@Test(priority=3)
	void verifyLoginWithoutCredential(Method name)
	{
		
		findingWebElement(Login_Locators.Submit).click();
		VerificationAssertion(Loginpro.getProperty("invalidCredentialAlert"),Login_Locators.WithoutCredential,name);
	}
	void commonLoginPageAction(String email,String password)
	{
		findingWebElement(Login_Locators.Email).sendKeys(email);
		findingWebElement(Login_Locators.Password).sendKeys(password);
		findingWebElement(Login_Locators.Submit).click();
	}
	
	@DataProvider(name="dataExtraction")
	String[][] InputCredentialFromExcel()
	{
		return ReadingDataFromExcel();
	}
}