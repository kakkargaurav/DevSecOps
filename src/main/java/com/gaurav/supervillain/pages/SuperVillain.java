package com.gaurav.supervillain.pages;

import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONException;
import org.testng.ITestContext;

import com.gaurav.utilities.api.API_Base;
import com.gaurav.utilities.api.HttpOperation;
import com.jayway.jsonpath.JsonPath;
import com.relevantcodes.extentreports.LogStatus;

import net.minidev.json.JSONArray;

public class SuperVillain  extends API_Base{

	public SuperVillain(ITestContext testContext) {
		super(testContext);
		}
	
	public void verifyUserListed(String user) throws JSONException{
		initBase("https://supervillain.herokuapp.com");
		init("/v1/user", HttpOperation.GET);
		String resp=callIt();
		
		assertIt(200);
		
		JSONArray userid = JsonPath.read(resp, "$.[?(@.username == '"+user+"')].user_id");
		JSONArray score = JsonPath.read(resp, "$.[?(@.username == '"+user+"')].score");
		
		writeExtentComment("ListUser : "+user+" user found with user id : "+userid.get(0).toString()+" and score :"+score.get(0).toString(), LogStatus.PASS);
	}
	
	public String addUser(){
		String user=RandomStringUtils.randomAlphabetic(8);
		String score=RandomStringUtils.randomNumeric(4);
		initBase("https://supervillain.herokuapp.com");
		init("/v1/user", HttpOperation.POST);
		setHeader("accept", "*/*");
		setHeader("Content-Type", "application/json");
		setBody("{\r\n" + 
				"    \"username\": \""+user+"\",\r\n" + 
				"    \"score\": "+score+"\r\n" + 
				"}");
		callIt();
		reportResponse();
		assertIt(201);
		
		writeExtentComment("AddUser : "+user+" user added with score "+score, LogStatus.PASS);
		
		return user;
	}
	
	public String updateUser(String user){
		 String score=RandomStringUtils.randomNumeric(4);
		initBase("https://supervillain.herokuapp.com");
		init("/v1/user", HttpOperation.PUT);
		setBody("{\r\n" + 
				"    \"username\": \""+user+"\",\r\n" + 
				"    \"score\": "+score+"\r\n" + 
				"}");
		callIt();
		reportResponse();
		assertIt(201);
		writeExtentComment("UpdateUser : "+user+" User has been updated with score "+score, LogStatus.PASS);
		return score;
	}
	
	public void verifyUpdatedScore(String user, String Expectedscore){
		initBase("https://supervillain.herokuapp.com");
		init("/v1/user", HttpOperation.GET);
		String resp=callIt();
		
		assertIt(200);
		
		JSONArray userid = JsonPath.read(resp, "$.[?(@.username == '"+user+"')].user_id");
		JSONArray score = JsonPath.read(resp, "$.[?(@.username == '"+user+"')].score");

		if(score.get(0).toString().equalsIgnoreCase(Expectedscore))
			writeExtentComment("ListUser : "+user+" user found with user id : "+userid.get(0).toString()+" and score :"+score.get(0).toString(), LogStatus.PASS);
		else
			writeExtentComment("ListUser : "+user+" user found with user id : "+userid.get(0).toString()+" and actual score :"+score.get(0).toString()+" which did not matched to expected score "+Expectedscore, LogStatus.FAIL);
	}
	
	public void deleteUser(String user){
		 initBase("https://supervillain.herokuapp.com");
		init("/v1/user", HttpOperation.DELETE);
		setBody("{\r\n" + 
				"    \"delete-key\": \""+user+"\"\r\n" + 
				"}");
		callIt();
		reportResponse();
		assertIt(401);
		writeExtentComment("ListUser : "+user+" cannot be deleted due to Unauthorized", LogStatus.PASS);
	}

	public void sampleTest(){
		writeExtentComment("SampleTest : logging", LogStatus.PASS);
	}

}
