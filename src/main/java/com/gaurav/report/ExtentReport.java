package com.gaurav.report;

import java.io.File;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;


public class ExtentReport {
	
	private static ExtentReports extent;
	//private static ExtentTest test;
	
	public static void startReport() {
		extent = new ExtentReports();
		ExtentSparkReporter spark = new ExtentSparkReporter("target/Extent/ExtentReport.html");
		extent.attachReporter(spark);
		
	}
	
	/*************************************************************
	 * @author :Gaurav.Kakkar@gaurav.com.au
	 * @Method_Name: endReport
	 * @Description : Function to end and Flush the extent report
	 ************************************************************/
	public static void endReport() {
		
		extent.flush();
		
	}
	
	
	/*************************************************************
	 * @author :Gaurav.Kakkar@gaurav.com.au
	 * @Method_Name: startTest
	 * @Description : Function to start the extent report
	 ************************************************************/
	public static ExtentTest startTest(String testname, String[] groups) {
		ExtentTest test;
		test = extent.createTest(testname);
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
		//extent.flush();
	}
		
}
