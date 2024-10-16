package tests.common;

//Standard Java Libraries
import java.net.MalformedURLException;
import java.net.UnknownHostException;

// TestNG Libraries
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.gaurav.report.ExtentReport;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public abstract class API_TestBase {

	
	protected API_TestBase() {
	
	}
	
	
	@BeforeSuite
	protected void BeforeSuite(ITestContext testContext) throws UnknownHostException {
		
		ExtentReport.startReport();
	}

	
	@BeforeMethod
	protected void BeforeMethod(ITestContext testContext, Object[] ob) throws MalformedURLException {
		String[] categories= {};
		String author= "No Author";
		if(!(testContext.getCurrentXmlTest().getParameter("Groups")==null))
			categories=testContext.getCurrentXmlTest().getParameter("Groups").split(",");
		
		ExtentTest extentTest = ExtentReport.startTest(testContext.getName(),categories );
		if(!(testContext.getCurrentXmlTest().getParameter("Author")==null))
			author=testContext.getCurrentXmlTest().getParameter("Author");
		extentTest.assignAuthor(author);
		testContext.setAttribute("ExtentTest", extentTest);
		testContext.setAttribute("TestTech", "API");
	}
	

		
	@AfterMethod
	protected void AfterMethod(ITestContext testContext) {
		ExtentTest extentTest = (ExtentTest) testContext.getAttribute("ExtentTest");
		if (extentTest.getRunStatus().equals(LogStatus.UNKNOWN))
		{
			extentTest.log(LogStatus.SKIP, "This test method is skipped");
		}
		ExtentReport.endTest(extentTest);
	}

	@AfterSuite
	protected void AfterSuite(ITestContext ctx) {
		ExtentReport.endReport();
	}
	
}
