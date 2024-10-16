package com.gaurav.responsivefight.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestContext;

import com.gaurav.utilities.web.Web_PageBase;
import com.relevantcodes.extentreports.LogStatus;

public class Bus extends Web_PageBase {
	WebDriver driver;

    @FindBy(id="bus_timer_start")
    WebElement busTimerBtn;

    @FindBy(id="bus_question_1")
    WebElement busQuestion1;
    
    @FindBy(id="bus_answer_1")
    WebElement busAnswer1;
    
    @FindBy(xpath="//button[.='Try the next battle']")
    WebElement nextBattleBtn;
    
    @FindBy(xpath="//button[.='Check your final score']")
    WebElement checkYourFinalScoreBtn;
    

   	public Bus(ITestContext testContext) {
		super(testContext);
		this.driver = (WebDriver) testContext.getAttribute("driver");

        //This initElements method will create all WebElements
        PageFactory.initElements(driver, this);
	}
	
	public void startBusChallenge(){
		//wait for element
    	waitForElementVisible(busTimerBtn);
		
    	busTimerBtn.click();
    	
    	 writeExtentCommentWithScreenshot("Bus : Challenge Started", LogStatus.PASS);
    }
	
	public void busQuestion1(){
		//wait for element
    	waitForElementVisible(busQuestion1);
		
    	busAnswer1.click();
    	
    	 writeExtentCommentWithScreenshot("Bus : Challenge Started", LogStatus.PASS);
    }
	
	public void tryNextBattle(){
		//wait for element
    	waitForElementVisible(nextBattleBtn);
		
    	nextBattleBtn.click();
    	
    	 writeExtentCommentWithScreenshot("Bus : Next Battle Started", LogStatus.PASS);
    }
	
	public void goToLeaderBoard(){
		//wait for element
    	waitForElementVisible(checkYourFinalScoreBtn);
		
    	checkYourFinalScoreBtn.click();
    	
    	 writeExtentCommentWithScreenshot("Bus : Navigate to leaderboard", LogStatus.PASS);
    }
	
	
   
}
