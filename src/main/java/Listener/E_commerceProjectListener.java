package Listener;

import java.io.File;
import java.awt.Desktop;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import UtilitiesPackage.GenerateExtentReport;
import UtilitiesPackage.utilities;

public class E_commerceProjectListener implements ITestListener {

	ExtentReports extentReport;
	ExtentTest extentTest;
	public void onStart(ITestContext context) {
		extentReport = GenerateExtentReport.generateExtentReportMethod();	
	}
	
	public void onTestStart(ITestResult result) {
		//testName = result.getName();
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.INFO,result.getName()+" TestCase Start Automated");
		
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.log(Status.PASS,result.getName()+" TestCase successfully Pass by Automation"); 	
	}

	public void onTestFailure(ITestResult result) {
		
		WebDriver driver = null;
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String ScreenshotFolder= utilities.captureScreenshotMethod(driver, result.getName());
		
		extentTest.addScreenCaptureFromPath(ScreenshotFolder);
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" TestCase found as a bug in Automation");
		
	}

	public void onTestSkipped(ITestResult result) {
		extentTest.log(Status.INFO,result.getThrowable());
		extentTest.log(Status.FAIL,result.getName()+" test case is skipped");
		
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
		
		
		String pathOfExtentReport =System.getProperty("user.dir")+"\\test-output\\ExtentReport\\extentReport_v1.html";
		File extentReport = new File (pathOfExtentReport);
		
		try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
}
