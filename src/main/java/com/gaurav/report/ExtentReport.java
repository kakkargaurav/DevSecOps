package com.gaurav.report;

import java.io.File;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

public class ExtentReport {
	
	private static ExtentReports extent;
	//private static ExtentTest test;
	
	public static void startReport() {
		extent = new ExtentReports(System.getProperty("user.dir") + "/target/Extent/ExtentReport.html", false);
		extent.loadConfig(new File("ReportConfiguration.xml"));
		
	}
	
	/*************************************************************
	 * @author :Gaurav.Kakkar@gaurav.com.au
	 * @Method_Name: endReport
	 * @Description : Function to end and Flush the extent report
	 ************************************************************/
	public static void endReport() {
		
		extent.flush();
		try {
			extent.close();
		}
		catch (Exception e){
			System.out.println("Error closing report");
		}
		
	}
	
	
	/*************************************************************
	 * @author :Gaurav.Kakkar@gaurav.com.au
	 * @Method_Name: startTest
	 * @Description : Function to start the extent report
	 ************************************************************/
	public static ExtentTest startTest(String testname, String[] groups) {
		ExtentTest test;
		test = extent.startTest(testname);
		for (String group : groups)
			test.assignCategory(group);
		
		return test;
	}
	
	
	/*************************************************************
	 * @author :Gaurav.Kakkar@gaurav.com.au
	 * @Method_Name: endTest
	 * @Description : Function to end the extent logger test.
	 ************************************************************/
	public static void endTest(ExtentTest test) {
		extent.endTest(test);
		extent.flush();
	}
		
}
