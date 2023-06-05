package ExtentsReportPack;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class CreationExtentsReport {

	public static ExtentReports generationReport()
	{
		ExtentReports extentReport=new ExtentReports();
		ExtentSparkReporter Reportspark = new ExtentSparkReporter("C:\\Users\\VICKY\\git\\NinjaProject\\ExtentReportFile\\NinjaExtentReport.html");
		Reportspark.config().setTheme(Theme.DARK);
		Reportspark.config().setReportName("NinjaStore");
		Reportspark.config().setDocumentTitle("Ninja Report Documents");
		Reportspark.config().setTimeStampFormat("dd/MM/yyyy hh:mm:ss");
		extentReport.attachReporter(Reportspark);
		Properties pro=new Properties();
		try {
			pro.load(new FileInputStream("C:\\Users\\VICKY\\git\\NinjaProject\\Files\\PropertiesFile.properties"));
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentReport.setSystemInfo("URL", pro.getProperty("URL"));
		extentReport.setSystemInfo("BrowserName", pro.getProperty("BrowserName"));
		extentReport.setSystemInfo("ValidCredential", pro.getProperty("ValidCredential"));
		extentReport.setSystemInfo("ValidPassWord", pro.getProperty("ValidPassWord"));
		return extentReport;
	}
}
