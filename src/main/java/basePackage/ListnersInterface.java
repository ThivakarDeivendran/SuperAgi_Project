package basePackage;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListnersInterface   implements ITestListener{
	public ExtentReports extent;
	public ExtentTest extentTest;
	public ExtentSparkReporter sparkReport;
	
	public void onStart(ITestContext context) {
		sparkReport = new ExtentSparkReporter(System.getProperty("user.dir") + "/MyReport/report.html");
		sparkReport.config().setDocumentTitle("Test Suite Report");
		sparkReport.config().setReportName("Functional Test case");
		sparkReport.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReport);		
		extent.setSystemInfo("Environment", "Production");
		extent.setSystemInfo("Application", "Amazon");
	}
	public void onTestSuccess(ITestResult Result)  {
		extentTest = extent.createTest(Result.getName());
		extentTest.log(Status.PASS, "Test Case Pass  :"+ Result.getName());
	}
	public void onTestFailure(ITestResult Result) {
		extentTest = extent.createTest(Result.getName());
		extentTest.log(Status.FAIL, "Test Case Fail  :"+ Result.getName());
	}
	public void onTestSkipped(ITestResult Result) {
		extentTest = extent.createTest(Result.getName());
		extentTest.log(Status.SKIP, "Test Case Skip  :" + Result.getName());
	}
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}
