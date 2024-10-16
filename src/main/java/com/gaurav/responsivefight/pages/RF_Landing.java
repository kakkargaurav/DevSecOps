package com.gaurav.responsivefight.pages;

import com.aventstack.extentreports.Status;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

import com.gaurav.utilities.web.Web_PageBase;


import static org.junit.Assert.assertEquals;

public class RF_Landing extends Web_PageBase {
	WebDriver driver;

    @FindBy(id="worrior_username")
    WebElement username;

    @FindBy(id="warrior")
    WebElement warriorBtn;
    
    @FindBy(id="start")
    WebElement startBtn;

    @FindBy(linkText="Build something amazing")
    WebElement removedTitle;

    public RF_Landing(ITestContext testContext) {
		super(testContext);
		this.driver = (WebDriver) testContext.getAttribute("driver");

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}
	
	public void launchApp(){
    	driver.get("http://responsivefight.herokuapp.com/");
    }
	
	
	//Set user name in textbox
	protected String setUserName(){
    	//wait for element
    	waitForElementVisible(username);
    	
    	
    	String user=RandomStringUtils.randomAlphanumeric(10);
    	username.sendKeys(user);   
    	return user;
    }

    //Click Button
	protected void clickWarriorBtn(){
    	//wait for element
    	waitForElementVisible(warriorBtn);
    	
    	warriorBtn.click();
    }
    
  //Click Button
	protected void clickStartJourneyBtn(){
    	//wait for element
    	waitForElementVisible(startBtn);
    	
    	startBtn.click();
    }

    
    public String setWarriorUsername(){
    	//launch App
    	this.launchApp();
    	
    	//set username
        String user=this.setUserName();

        //click warrior button
        this.clickWarriorBtn();
        
        writeExtentCommentWithScreenshot("Landing : Warrior username eneterd", Status.PASS);
        return user;
    }
    
    public void verifyStartJourneyBtn(String user){
    	//wait for element
    	waitForElementVisible(startBtn);
    	
    	Assert.assertTrue(startBtn.getText().equals("Start your journey "+user));
        

        writeExtentCommentWithScreenshot("Landing : Btn verified with username", Status.PASS);
    }
    
    public void startJourney(){
    	//click Start Journey button
        this.clickStartJourneyBtn();

        writeExtentCommentWithScreenshot("Landing : Start Journey", Status.PASS);
    }

    public void verifyAppRemoved(){
        assertEquals("Heroku | Application Error",driver.getTitle());
        writeExtentCommentWithScreenshot("Website Removed", Status.PASS);
    }
    
   
}
