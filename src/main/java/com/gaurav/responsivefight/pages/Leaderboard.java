package com.gaurav.responsivefight.pages;

import com.aventstack.extentreports.Status;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;

import com.gaurav.utilities.web.Web_PageBase;


public class Leaderboard extends Web_PageBase {
	WebDriver driver;

    @FindBy(id="leaderboard_link")
    WebElement leaderboardLinkBtn;

   
   	public Leaderboard(ITestContext testContext) {
		super(testContext);
		this.driver = (WebDriver) testContext.getAttribute("driver");

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}
	
	public void verifyScore(String user, int score){
		//wait for element
    	waitForElementVisible(leaderboardLinkBtn);
    	
		
    	WebElement we=driver.findElement(By.xpath("//td[.='"+user+"']/following-sibling::td"));
    	
    	//Verify Score
    	Assert.assertEquals(Integer.parseInt(we.getText()), score);

    	//Scroll element to Viewport
    	scrollElementToView(we);
    	
    	//report comment
    	writeExtentCommentWithScreenshot("Leaderboard : Score matched for user "+user+" as "+score, Status.PASS);
    }
	
	
	public void getUserId(String user){
		//wait for element
    	waitForElementVisible(leaderboardLinkBtn);
		
    	WebElement we=driver.findElement(By.xpath("//td[.='"+user+"']/preceding-sibling::td"));
    	
    	//get user row
    	WebElement we2=driver.findElement(By.xpath("//td[.='"+user+"']/parent::tr"));
    	
    	//highlight row with border
    	highLightElement(we2);;
    	
    	//report comment
    	 writeExtentCommentWithScreenshot("Leaderboard : User ID for  "+user+" is "+we.getText(), Status.PASS);
    }
	
   
}
