package ListenerPack;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.driverClass;
import ExtentsReportPack.CreationExtentsReport;

public class MyListener implements ITestListener {
	ExtentReports extensReport=null;
	ExtentTest test=null;
	@Override
	public void onStart(ITestContext context) {
		extensReport=CreationExtentsReport.generationReport();
	}

	@Override
	public void onTestStart(ITestResult result) {
		test=extensReport.createTest(result.getName());
		test.log(Status.INFO, result.getName()+" Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test=extensReport.createTest(result.getName());
		test.log(Status.PASS, result.getName()+" Test Successfully Executed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test=extensReport.createTest(result.getName());
		test.log(Status.FAIL, result.getName()+" Test Failed");
		//To take the screen shot we need driver instance to call screenShot class
		RemoteWebDriver driver=(RemoteWebDriver) driverClass.driver;
		File screenShot1=driver.getScreenshotAs(OutputType.FILE);
		String destinyPath="C:\\Users\\kmruge\\eclipse-workspace\\Ninja_Store_Project\\ScreenShots\\"+result.getName()+".png";
		File destine=new File(destinyPath);
		try {
			FileUtils.copyFile(screenShot1, destine);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.addScreenCaptureFromPath(destinyPath);
		test.log(Status.INFO, result.getThrowable());
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.log(Status.INFO, result.getThrowable());
		test.log(Status.SKIP, result.getName()+" Skipped");
	}

	@Override
	public void onFinish(ITestContext context) {
		extensReport.flush();
		//Auto opeing of Expents File Reporter
		File extentsFilePath=new File("C:\\Users\\VICKY\\git\\NinjaProject\\ExtentReportFile\\NinjaExtentReport.html");
		try {
			Desktop.getDesktop().browse(extentsFilePath.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
