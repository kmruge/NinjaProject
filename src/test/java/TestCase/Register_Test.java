package TestCase;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import POM.HomePage;
import Utility.Utilise1;
import locators.Register_Locators;

public class Register_Test extends Utilise1{
	int rowValue=0;
	@BeforeMethod
	void setUp()
	{
		driverInitialize(pro.getProperty("BrowserName"));
		HomePage hpage=new HomePage(driver);
		hpage.clickOnAccount();
		hpage.clickOnRegister();
	}
	@AfterMethod
	void tearDown()
	{
		closeDriver();
	}
	@Test(invocationCount=5)
	void RegisterWithAllDetailsProperly(Method name)
	{
		//Creating the email with time stamp appended
		String Email=CredentialEmail();
		String password=Registerpro.getProperty("PassWord");
		//Method to perform common action on registration page
		commonFieldInRegisterPage(Registerpro.getProperty("FirstName"),Registerpro.getProperty("LastName"),Email,Registerpro.getProperty("TelePhone"),password,Registerpro.getProperty("PassWordConfirmation"));
		findingWebElement(Register_Locators.Agree).click();
		findingWebElement(Register_Locators.Continue).click();
		try {
			ReadingRegistrationDetailsInExcel(Email,password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	void RegisterWithExistEmail(Method name)
	{
		commonFieldInRegisterPage(Registerpro.getProperty("FirstName"),Registerpro.getProperty("LastName"),Registerpro.getProperty("ExitedEmail"),Registerpro.getProperty("TelePhone"),Registerpro.getProperty("PassWord"),Registerpro.getProperty("PassWordConfirmation"));
		findingWebElement(Register_Locators.Agree).click();
		findingWebElement(Register_Locators.Continue).click();
		VerificationAssertion(Registerpro.getProperty("EmailAlreadyRegisteredAlert"),Register_Locators.ExistEmailWarning,name);
	}
	@Test()
	void RegisterWithoutClickingPrivacy(Method name)
	{
		commonFieldInRegisterPage(Registerpro.getProperty("FirstName"),Registerpro.getProperty("LastName"),Registerpro.getProperty("ExitedEmail"),Registerpro.getProperty("TelePhone"),Registerpro.getProperty("PassWord"),Registerpro.getProperty("PassWordConfirmation"));
		findingWebElement(Register_Locators.Continue).click();
		VerificationAssertion(Registerpro.getProperty("WithoutClickingPrivacyAlert"),Register_Locators.WithoutPrivacyWarning,name);
		
	}
	//CommonMethod for all test on registration
	void commonFieldInRegisterPage(String FirstName, String LastName,String Email,String TelePhone,String passWord,String confirmPassWord)
	{
		findingWebElement(Register_Locators.FirstName).sendKeys(FirstName);
		findingWebElement(Register_Locators.LastName).sendKeys(LastName);
		findingWebElement(Register_Locators.Email).sendKeys(Email);
		findingWebElement(Register_Locators.TelePhone).sendKeys(TelePhone);
		findingWebElement(Register_Locators.PassWord).sendKeys(passWord);
		findingWebElement(Register_Locators.ConfirmPassWord).sendKeys(confirmPassWord);
	}
}
