package locators;

public interface Register_Locators {
	String FirstName="//input[@name='firstname']";
	String LastName="//input[@name='lastname']";
	String Email="//input[@name='email']";
	String TelePhone="//input[@name='telephone']";
	String PassWord="//input[@name='password']";
	String ConfirmPassWord="//input[@name='confirm']";
	String Agree="//input[@name='agree']";
	String  Continue="//input[@value='Continue']";
	String validAssertWarning="//div[@id='content']/h1";
	String ExistEmailWarning="//div[@class='alert alert-danger alert-dismissible']";
	String WithoutPrivacyWarning="//div[@class='alert alert-danger alert-dismissible']";
}
