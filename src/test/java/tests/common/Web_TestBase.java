package tests.common;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
// Standard Java Libraries
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.gaurav.report.ExtentReport;

public abstract class Web_TestBase {
	protected WebDriver driver;
	protected Web_TestBase() {
	
	}
	
	
	@BeforeSuite
	protected void BeforeSuite(ITestContext testContext) {
		ExtentReport.startReport();
	}

	
	@BeforeTest
	protected void BeforeTest(ITestContext testContext) throws MalformedURLException {
		String[] categories= {};
		String author= "No Author";
		if(!(testContext.getCurrentXmlTest().getParameter("Groups")==null))
			categories=testContext.getCurrentXmlTest().getParameter("Groups").split(",");
		
		ExtentTest extentTest = ExtentReport.startTest(testContext.getName(),categories );
		if(!(testContext.getCurrentXmlTest().getParameter("Author")==null))
			author=testContext.getCurrentXmlTest().getParameter("Author");
		extentTest.assignAuthor(author);
		testContext.setAttribute("ExtentTest", extentTest);
		testContext.setAttribute("TestTech", "Web");
		this.startBrowser(testContext);
	}
	

		
	@AfterTest
	protected void AfterTest(ITestContext testContext) {
		ExtentTest extentTest = (ExtentTest) testContext.getAttribute("ExtentTest");
		if (extentTest.getStatus().equals(Status.SKIP))
		{
			extentTest.log(Status.SKIP, "This test method is skipped");
		}
		ExtentReport.endTest(extentTest);
		this.stopBrowser(testContext);
	}

	@AfterSuite
	protected void AfterSuite(ITestContext ctx) {
		ExtentReport.endReport();
	}
	
	
	protected void startBrowser(ITestContext ctx) throws MalformedURLException {
		String browser = "chrome";
		if(!(ctx.getCurrentXmlTest().getParameter("Browser")==null))
			browser=(String) ctx.getCurrentXmlTest().getParameter("Browser");
		
		if(browser.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			options.addArguments("start-maximized"); // open Browser in maximized mode
			options.addArguments("disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			options.addArguments("--disable-gpu"); // applicable to windows os only
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			options.addArguments("--no-sandbox");
			options.addArguments("--headless");
			driver = new org.openqa.selenium.edge.EdgeDriver(options);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} else if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equalsIgnoreCase("grid-mac-firefox")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.MAC);
	       	driver = new RemoteWebDriver(new URL("http://192.168.18.101:4444/wd/hub"),cap);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equalsIgnoreCase("grid-win-firefox")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("firefox");
			cap.setPlatform(Platform.WIN10);
	       	driver = new RemoteWebDriver(new URL("http://192.168.18.101:4444/wd/hub"),cap);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equalsIgnoreCase("grid-mac-chrome")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.MAC);
	       	driver = new RemoteWebDriver(new URL("http://192.168.18.101:4444/wd/hub"),cap);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equalsIgnoreCase("grid-win-chrome")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("chrome");
			cap.setPlatform(Platform.WIN10);
	       	driver = new RemoteWebDriver(new URL("http://192.168.18.101:4444/wd/hub"),cap);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equalsIgnoreCase("grid-win-edge")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setBrowserName("MicrosoftEdge");
			cap.setPlatform(Platform.WIN10);
			driver = new RemoteWebDriver(new URL("http://192.168.18.101:4444/wd/hub"),cap);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equalsIgnoreCase("grid-mac-edge")) {
			DesiredCapabilities cap=new DesiredCapabilities();
			cap.setBrowserName("MicrosoftEdge");
			cap.setPlatform(Platform.MAC);
			driver = new RemoteWebDriver(new URL("http://192.168.18.101:4444/wd/hub"),cap);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equalsIgnoreCase("grid-win-opera")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("opera");
			cap.setPlatform(Platform.WIN10);
			driver = new RemoteWebDriver(new URL("http://192.168.18.101:4444/wd/hub"),cap);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else if(browser.equalsIgnoreCase("grid-mac-safari")) {
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setBrowserName("safari");
			cap.setPlatform(Platform.MAC);
	       	driver = new RemoteWebDriver(new URL("http://192.168.18.101:4444/wd/hub"),cap);
			driver.manage().window().maximize();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}else{
			EdgeOptions options = new EdgeOptions();
			options.addArguments("start-maximized"); // open Browser in maximized mode
			options.addArguments("disable-infobars"); // disabling infobars
			options.addArguments("--disable-extensions"); // disabling extensions
			options.addArguments("--disable-gpu"); // applicable to windows os only
			options.addArguments("--disable-dev-shm-usage"); // overcome limited resource problems
			options.addArguments("--no-sandbox");
			options.addArguments("--headless");
			driver = new org.openqa.selenium.edge.EdgeDriver(options);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		}
		ctx.setAttribute("driver", driver);
	}
	
	protected void stopBrowser(ITestContext ctx) {
		driver.close();
		driver.quit();
		try {
			Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f");
			Runtime.getRuntime().exec("taskkill /im geckodriver.exe /f");
			Runtime.getRuntime().exec("taskkill /im IEDriverServer.exe /f");
		} catch (IOException ignored) {
			
		}
	}

}
