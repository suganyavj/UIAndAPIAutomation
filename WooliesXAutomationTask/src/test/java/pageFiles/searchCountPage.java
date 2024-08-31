package pageFiles;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import stepDefinitions.commonSteps;
import stepDefinitions.searchCountSteps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class searchCountPage {
	
	//defined the logger here
	private static final Logger logger = LoggerFactory.getLogger(searchCountSteps.class);
	
	Response responseValue;
	ResponseBody body;
	String bodyObject;
	JsonPath js;
	
	int search;
	int totalSearch=0;
	
	public void hit_endpoint() {
		
		//passing this from commonSteps file
		responseValue = commonSteps.getResponse();
		
		// Get all the body from json response
		body = responseValue.body();
		bodyObject = body.asString();
		
		
    }
	public void receive_response() {
		
		logger.info("<-------------------Response-------------->");
		System.out.println("Response Value---->:"+responseValue.asPrettyString());
		
	}
	
	public int validate_status_code() {
		
		int statusCode = responseValue.getStatusCode();
		logger.info(",-----Status code:------>"+statusCode);
		return statusCode;
		
	}
	
	public String validate_status_line() {
		
		String statusLine = responseValue.getStatusLine();
		logger.info(",-----Status Line:------>"+statusLine);
		return statusLine;
	}
	
	public void print_the_response_headers() {

		try {
		// Get all the headers from the response body
		logger.info("<----------------Header values------------------>");
		Headers headerValue = responseValue.getHeaders();
		
			for (Header data : headerValue) {
				
				System.out.println(data.getName() + " = " + data.getValue());
			}
		System.out.println("<----------------Header values------------------>");
		}
		catch(Exception e) {
			fail("Failed in fetching headers:"+e);
		}
		
	}
	
	public String getActualContentType() {
		
		String actualContentType = responseValue.getHeader("Content-Type");
		logger.info(",-----Content Type:------>"+actualContentType);
		
		return actualContentType;
		
	}
	
	public boolean validate_the_fields(String value) {
		
		logger.info("----validating the fields-----");
		
		if(bodyObject.contains(value)) {
			System.out.println("The "+value+" field is present");
			return true;
		}
		else { return false; }
		
	}
	
	public Object validate_the_search_count_as_object(String val) {

		logger.info("-----validating search count----");
		JsonPath path = body.jsonPath();
		Object articleCount = path.get(val);
		System.out.print("<---------Article Count--------->:"+articleCount);
		
		return articleCount;

	}

	
	public Object get_the_sum_of_search(){
		
		logger.info("------getting the sum of search-----");
		js = commonSteps.rawToJson(bodyObject);
		
		try {
			
		int count = js.getInt("_meta.size()");
		
		System.out.println("------------------------------------");
		System.out.println("Printing the SearchPath and Label");
		System.out.println("------------------------------------");
		
		for(int i=0;i<count;i++) {
			
			String names = js.getString("_meta["+i+"].SearchPath");
			String label = js.getString("_meta["+i+"].Label");
			label = label.replaceAll("\\s.*", "");
			search = Integer.parseInt(label);
			System.out.println(names+":"+search);
			
			totalSearch += search; 
		}
		System.out.println("------------------------------------");
		System.out.println("Total:"+totalSearch);
		System.out.println("------------------------------------");
		}
		catch(Exception e) {
			fail("Failed in getting the sum of search:"+e);
		}
		
		
		return totalSearch;
		
	}
	
	public Object validate_total() {
		
		logger.info("----getting the total count------");
		JsonPath path = body.jsonPath();
		Object totalCount = path.get("SearchCount.Total");
		System.out.print("<---------Total Count--------->:"+totalCount);
	
		return totalCount;
	}
	
	public void validate_count() {
		
		logger.info("----validating the artcle count not less than 10---");
		
		JsonPath path = body.jsonPath();
		int count = path.get("SearchCount.ArticleCount");
		System.out.print("<---------ArticleCount--------->:"+count);
	
		if(count<10) {
			fail("Alert: The ArticleCount is less than 10");
		}
	}
	
	public void UntraceableProductCount_is_zero() {
		
		logger.info("----validating the UntraceableProductCount should not be greater than 0---");
		
		JsonPath path = body.jsonPath();
		int count = path.get("SearchCount.UntraceableProductCount");
		System.out.print("<---------UntraceableProductCount--------->:"+count);
		
		if(count>0) {
			fail("Alert: The UntraceableProductCount is greater than 0");
		}
		
	}
	
	public void get_size_of_search() {
		
		logger.info("---Finding the number of searches in deli---");
		
		js = commonSteps.rawToJson(bodyObject);
		int count = js.getInt("_meta.size()");
		
		System.out.println("<----Number of searches in Deli----->:"+count);
		
	}
}
