package Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Base.driverClass;

public class Utilise1 {
	protected WebDriver driver;
	public static Properties pro=null;
	public Properties Loginpro=null;
	public Properties Registerpro=null;
	public static final int Implicite_Wait=30;
	public static final int Page_Wait=10;
	public Utilise1()
	{
		pro=new Properties();
		Loginpro=new Properties();
		Registerpro=new Properties();
		try {
			pro.load(new FileInputStream("C:\\Users\\VICKY\\git\\NinjaProject\\Files\\PropertiesFile.properties"));
			Loginpro.load(new FileInputStream("C:\\Users\\VICKY\\git\\NinjaProject\\Files\\LoginProperties.properties"));
			Registerpro.load(new FileInputStream("C:\\Users\\VICKY\\git\\NinjaProject\\Files\\RigisterProperties.properties"));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void driverInitialize(String name)
	{
		driver=driverClass.driverInitialize(name);
	}
	public WebElement findingWebElement(String Path)
	{
		return driver.findElement(By.xpath(Path));
	}
	public void closeDriver()
	{
		driver.quit();
	}
	public String CredentialEmail()
	{
		Date date=new Date();
		String dateStr=date.toString().replace(" ", "").replace(":", "");
		return "muru"+dateStr+"@gmail.com";
	}
	public void VerificationAssertion(String expected,String path,Method methName)
	{
		boolean check=findingWebElement(path).getText().contains(expected);
		Assert.assertTrue(check,"Assertion Faile at "+methName);
	}
	//Writing data's in the excel after registration
	public void ReadingRegistrationDetailsInExcel(String Email, String password) throws IOException
	{
		FileInputStream InFile=new FileInputStream("C:\\Users\\VICKY\\git\\NinjaProject\\Files\\data.xlsx");
		XSSFWorkbook book=new XSSFWorkbook(InFile);
		XSSFSheet sheet=book.getSheet("RegisterCredential1");
		int lastRow=sheet.getLastRowNum();
		Row row=sheet.createRow(lastRow+1);
		row.createCell(0).setCellValue(Email);
		row.createCell(1).setCellValue(password);
		File file =new File("C:\\Users\\VICKY\\git\\NinjaProject\\Files\\data.xlsx");
		FileOutputStream outFile=new FileOutputStream(file);
		book.write(outFile);
		book.close();
	}
	//Reading data's from Excel
	public String[][] ReadingDataFromExcel()
	{
		String[][] date=null;
		try {
		FileInputStream InFile=new FileInputStream("C:\\Users\\VICKY\\git\\NinjaProject\\Files\\data.xlsx");
		XSSFWorkbook book=new XSSFWorkbook(InFile);
		XSSFSheet sheet=book.getSheet("RegisterCredential1");
		int lastRow=sheet.getLastRowNum();
		int lastCol=sheet.getRow(0).getLastCellNum();
		date=new String[lastRow-1][lastCol];
		for(int i=1;i<lastRow;i++)
		{
			XSSFRow row=sheet.getRow(i);
			for(int j=0;j<lastCol;j++)
			{
				XSSFCell cell=row.getCell(j);
				DataFormatter diff=new DataFormatter();
				String value=diff.formatCellValue(cell);
				date[i-1][j]=value;
			}
		}
		}catch(Exception e)
		{
			e.getStackTrace();
		}
		return date;
	}
}
