package locators;

public interface Login_Locators {
	String Email="//input[@name='email']";
	String Password="//input[@name='password']";
	String Submit="//input[@value='Login']";
	String ValidCredential="//div[@id='content']/h2[text()='My Orders']";
	String InvalidCredential="//div[@class='alert alert-danger alert-dismissible']";
	String WithoutCredential="//div[@class='alert alert-danger alert-dismissible']";
}
