package stepDefinitions;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class commonSteps {
	
	
	public static Response getResponse() {
		
		//getting the response by hitting the URL below
		Response responseValue=null;
		
		try {
		RestAssured.baseURI = "https://www.woolworths.com.au";

		responseValue = given().queryParam("searchTerm", "deli").log().all().when()

				.get("apis/ui/v2/Search/count");
		
		}
		catch(Exception e){
			
		}
		
		return responseValue;
	}
	
	public static JsonPath rawToJson(String response) {
		
		//converting String to JSON format
		JsonPath js = new JsonPath(response);
		return js;
	}
	
}
