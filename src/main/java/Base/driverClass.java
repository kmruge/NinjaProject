package Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utility.Utilise1;

public class driverClass {
	public static WebDriver driver=null;
	public static WebDriver driverInitialize(String Name)
	{ 
		if(Name.equals("Chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(Name.equals("FireFox"))
		{
			driver=new FirefoxDriver();
		}
		else if(Name.equals("Edge"))
		{
			driver=new EdgeDriver();
		}
		else
		{
			System.out.print("Please Enter valid Browser Name");
			return null;
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilise1.Implicite_Wait));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilise1.Page_Wait));
		driver.get(Utilise1.pro.getProperty("URL"));
		
		return driver;
	}
	
}
