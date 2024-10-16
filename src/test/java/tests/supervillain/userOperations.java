package tests.supervillain;

import java.net.UnknownHostException;

import org.json.JSONException;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.gaurav.supervillain.pages.SuperVillain;
import tests.common.API_TestBase;

public class userOperations extends API_TestBase  {
	protected String user="";
	protected String score="";

	@Test
	public void testAPI(ITestContext testContext) throws UnknownHostException, InterruptedException, JSONException{
		// What pages are being used?
		SuperVillain api= new SuperVillain(testContext);
		api.sampleTest();
	}
}
