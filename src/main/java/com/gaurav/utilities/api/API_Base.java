package com.gaurav.utilities.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyArray;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasKey;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.ITestContext;
import org.testng.Reporter;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public abstract class API_Base  {

	protected RequestSpecification reqSpec;
	protected HttpOperation method;
	protected String url;
	protected Response resp;
	protected ITestContext testContext;
	protected ExtentTest extentTest = null;
	protected String testName = null;

	
	protected API_Base(ITestContext testContext) {
		this.testContext = testContext;
		this.extentTest = (ExtentTest)testContext.getAttribute("ExtentTest");	
		this.testName = testContext.getName();
	}
	

	public void writeExtentComment(String comment, LogStatus Extentlogstatus) {
		extentTest.log(Extentlogstatus, comment);				
	}
	



	public void reportResponse() {
		
			String stepname=Thread.currentThread().getStackTrace()[2].getMethodName();
			extentTest.log(LogStatus.INFO, resp.asString());	
			Reporter.log("<br><br>"+stepname+" : "+ resp.asString());
			
	}
	
    public void init(String url, HttpOperation method) {
		this.url = url;
		this.method = method;
		reqSpec = given();
	}
	
	public void initBase(String APIURL) {
			try {
			RestAssured.baseURI = APIURL;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		reqSpec = RestAssured.given();
	}

	public void setHeader(String[][] head) {
		for (int row = 0; row < head.length; row++)
			for (int col = 0; col < head[row].length; col++)
				reqSpec.header(head[row][col], head[row][col + 1]);
	}

	public void setHeader(String head, String val) { reqSpec.header(head, val);}

	public void setBody(String body) { reqSpec.body(body); }

	public void setFormParam(String key, String val) { reqSpec.formParam(key, val);}

	public void setQueryParam(String key, String val) { reqSpec.queryParam(key, val);}


	public String callIt() {
		if (method.toString().equalsIgnoreCase("get")) {
			resp = reqSpec.get(url);
			testContext.setAttribute("Response", resp.asString());
			return resp.asString();
		} else if (method.toString().equalsIgnoreCase("post")) {
			resp = reqSpec.post(url);
			testContext.setAttribute("Response", resp.asString());;
			return resp.asString();
		} else if (method.toString().equalsIgnoreCase("patch")) {
			resp = reqSpec.patch(url);
			testContext.setAttribute("Response", resp.asString());
			return resp.asString();
		} else if (method.toString().equalsIgnoreCase("put")) {
			resp = reqSpec.put(url);
			testContext.setAttribute("Response", resp.asString());
			return resp.asString();
		} else if (method.toString().equalsIgnoreCase("delete")) {
			resp = reqSpec.delete(url);
			testContext.setAttribute("Response", resp.asString());
			return resp.asString();
		}
		testContext.setAttribute("Response", "");
		return "invalid method set for API";
	}

	public API_Base assertIt(String key, Object val, ValidatorOperation operation) {

		switch (operation.toString()) {
		case "EQUALS":
			resp.then().body(key, equalTo(val));
			break;

		case "KEY_PRESENTS":
			resp.then().body(key, hasKey(key));
			break;

		case "HAS_ALL":
			break;
			
		case "NOT_EQUALS":
			resp.then().body(key, not(equalTo(val)));
			break;
			
		case "EMPTY":
			resp.then().body(key, empty());
			break;
			
		case "NOT_EMPTY":
			resp.then().body(key, not(emptyArray()));
			break;
		
		case "NOT_NULL":
			resp.then().body(key, notNullValue());
			break;
			
		case "HAS_STRING":
	        resp.then().body(key, containsString((String)val));
	        break;
	        
		case "SIZE":
	        resp.then().body(key, hasSize((int)val));
	        break;
		}
		
		return this;
	}

	public void assertIt(List<List<Object>> data) {
		for (List<Object> li : data) {
			switch (((ValidatorOperation) li.get(2)).toString()) {
			case "EQUALS":
				resp.then().body(((String) li.get(0)), equalTo((String) li.get(1)));
				break;

			case "KEY_PRESENTS":
				resp.then().body(((String) li.get(0)), hasKey((String) li.get(1)));
				break;
				
			case "HAS_ALL":
				break;
			}
		}
	}
	
	public API_Base assertIt(int code) {

		resp.then().statusCode(code);

		return this;
	}

	public String extractString(String path) { return resp.jsonPath().getString(path);}
	
	public int extractInt(String path) { return resp.jsonPath().getInt(path);}

	public List<?> extractList(String path) { return resp.jsonPath().getList(path);}

	public Object extractIt(String path) { return resp.jsonPath().get(path); }

	public String extractHeader(String header_name) { return resp.header(header_name);}

	public String getResponseString() { return resp.asString();}

	public void fileUpload(String key, String path, String mime) {
		reqSpec.multiPart(key, new File(path), mime);
	}
	
	public void multiPartString(String key, String input) { reqSpec.multiPart(key,input);}
	
	public void printResp() { resp.print();}
	
	public String getCookieValue(String cookieName) { return resp.getCookie(cookieName); }
	
	public API_Base assertIt(int code, int optionalCode) {
		resp.then().statusCode(anyOf(equalTo(code),equalTo(optionalCode)));
		return this;
	}
	
	public void setRedirection(boolean followRedirects) { 
		reqSpec.when().redirects().follow(followRedirects);
		}
	
	public void ListResponseHeaders()
	{ 
	 // Get all the headers. Return value is of type Headers.
	 Headers allHeaders = resp.headers();
	 // Iterate over all the Headers
	 for(Header header : allHeaders)
	 {
	 System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
	 }
	}
	
	public int getStatusCode() { return resp.getStatusCode(); }
	
	
	public Headers getAllHeaders() {return resp.getHeaders();}
	
	
	public void assertAll(String input, String output) {
		try {
			JSONAssert.assertEquals(input, output, false);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	
	public void assertAll(String input, String output, String strict) {
		try {
			JSONAssert.assertEquals(input, output, true);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
	
	public void assertValue(String input, String output) {
		assertEquals(input,output);
	}
	
	
	public void assertString(String actual, String expected, String error_message)
	{
		assertEquals(actual,expected,error_message);
	}
	
	
	public void assertInt(int actual, int expected, String error_message)
	{
		assertEquals(actual,expected,error_message);
	}

	
	public void assertValue(String input, String key, String value) {	
		try {
			JSONObject inputJson = new JSONObject(input);
			String Key = inputJson.get(key).toString();
			assertEquals(Key,value);
		} catch (JSONException e) {
			
		}
	}
	
	public String getValue(String input, String array, int pos, String key) throws JSONException {
			JSONObject inputJson = new JSONObject(input);
			JSONArray jsonArray = inputJson.getJSONArray(array);
			JSONObject job = jsonArray.getJSONObject(pos);
			String Key = job.get(key).toString();
			return Key;
		
	}
	
	public String getValue(String input, String key) throws JSONException {
		JSONObject inputJson = new JSONObject(input);
		String value = inputJson.get(key).toString();
		return value;		
	}
}
