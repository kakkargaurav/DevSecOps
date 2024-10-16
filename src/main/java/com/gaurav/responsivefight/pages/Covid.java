package com.gaurav.responsivefight.pages;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

import com.gaurav.utilities.web.Web_PageBase;
import com.relevantcodes.extentreports.LogStatus;

public class Covid extends Web_PageBase {
	WebDriver driver;

    @FindBy(id="welcome_text")
    WebElement title;

    @FindBy(id="news")
    WebElement newsBtn;
    
    @FindBy(id="bus")
    WebElement busBtn;
    
    @FindBy(id="restaurant")
    WebElement restaurantBtn;
    
    @FindBy(id="office")
    WebElement officeBtn;  

   	public Covid(ITestContext testContext) {
		super(testContext);
		this.driver = (WebDriver) testContext.getAttribute("driver");

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}
	
	public void verifyTitle(String user){
		//wait for element
    	waitForElementVisible(title);
		
    	Assert.assertTrue(title.getText().equals("Choose your battle field "+user));
    	 writeExtentCommentWithScreenshot("Covid : Title Verified", LogStatus.PASS);
    }
	
	public void startAreYouGame(){
		//wait for element
    	waitForElementVisible(newsBtn);
		
    	//click Are You game btn
    	newsBtn.click();
    	
    	 writeExtentCommentWithScreenshot("Covid : Are you Game started", LogStatus.PASS);
    }
	
	public void takeTheBus(){
		//wait for element
    	waitForElementVisible(busBtn);
		
    	//click Are You game btn
    	busBtn.click();
    	
    	 writeExtentCommentWithScreenshot("Covid : Take the bus started", LogStatus.PASS);
    }
	
	public void goToPublicPlace(){
		//wait for element
    	waitForElementVisible(restaurantBtn);
		
    	//click Are You game btn
    	restaurantBtn.click();
    	
    	 writeExtentCommentWithScreenshot("Covid : Go to a public place started", LogStatus.PASS);
    }
	
	public void goToTheOffice(){
		//wait for element
    	waitForElementVisible(officeBtn);
		
    	//officeBtn Are You game btn
    	newsBtn.click();
    	
    	 writeExtentCommentWithScreenshot("Covid : Go to the office started", LogStatus.PASS);
    }
	
	
    
   
}
