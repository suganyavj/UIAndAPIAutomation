package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static io.restassured.RestAssured.given;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import TestData.testData;
import pageFiles.searchCountPage;
import testDataFile.TestDataLoader;
import testDataFile.TesData;
import java.io.IOException;

public class searchCountSteps {

	private static final Logger logger = LoggerFactory.getLogger(searchCountSteps.class);
	private TesData tData;
	searchCountPage page = new searchCountPage();

	@Given("I have test data")
	public void i_have_test_data() throws IOException {
		tData = TestDataLoader.loadTestData();
	}

	@Then("I log in with the test user")
	public void i_log_in_with_the_test_user() {
		String username = tData.username;
		String password = tData.password;
		System.out.println(username);
		System.out.println(password);
	}

	@Given("^passing regex value (.*)$")
	public void regex_value(int value){
		System.out.println(value);

	}

	@Given("Send a GET request to the search endpoint")
	public void send_request() {

		//sending the URL with details to hit the End-point
		page.hit_endpoint();
	}

	@Then("I should receive a valid response")
	public void receive_valid_response() {

		//Response is received
		page.receive_response();

	}

	@Given("The Response should contain the status code")
	public void validate_status_code() {

		//Verifying the status code is 200 - which is successful
		Assert.assertEquals(page.validate_status_code(), 200);

	}

	@And("Validate the status line")
	public void validate_status_line() {

		//Verifying the status line is "HTTP/1.1 200 OK
		Assert.assertEquals(page.validate_status_line(), "HTTP/1.1 200 OK");

	}

	@When("Get the search count from the response")
	public void get_size_of_search() {

		//Finding number of search items in deli
		page.get_size_of_search();

	}

	@And("Print the response headers")
	public void print_the_response_headers() {

		page.print_the_response_headers();

		//Validate a single header from the response - the expected data is passed through testData file
		Assert.assertEquals("The SpecialProductCount field is not present",testData.expectedContentType,page.getActualContentType());

	}

	@Then("Validate the fields in SearchCount node")
	public void validate_the_fields() {

		// Validation for a SearchCount where it has the required fields or not
		Assert.assertEquals("The ProductCount field is not present", page.validate_the_fields("ProductCount"), true);
		Assert.assertEquals("The SpecialProductCount field is not present",page.validate_the_fields("SpecialProductCount"), true);
		Assert.assertEquals("The RecipeCount field is not present",page.validate_the_fields("RecipeCount"), true);
		Assert.assertEquals("The ArticleCount field is not present",page.validate_the_fields("ArticleCount"), true);
		Assert.assertEquals("The UntraceableProductCount field is not present",page.validate_the_fields("UntraceableProductCount"), true);
		Assert.assertEquals("The Total field is not present",page.validate_the_fields("Total"), true);

	}

	@And("Validate the ArticleCount matches the test data")
	public void validate_the_search_count() {

		// Validation for a specific data value is present in the response or not
		Assert.assertEquals("ArticleCount Not Matched",page.validate_the_search_count_as_object("SearchCount.ArticleCount"), testData.ArticleCount);

	}

	@When("Get the sum of all searches and verify it matches with Total")
	public void matches_with_total() {

		//adding all the search counts and verifying it matches with the total searches
		Assert.assertEquals("The Total search and sum of search is not matching",page.get_the_sum_of_search(),page.validate_total());

	}

	@And("Get the article count and Verify count goes below the Threshold")
	public void verify_article_count() {

		//Verifying the article count should not go below 10
		page.validate_count();
	}

	@Then("Verify the UntraceableProductCount should be 0")
	public void UntraceableProductCount_is_zero() {

		//Verifying the Untraceable Product count should not be greater than 0
		page.UntraceableProductCount_is_zero();

	}




}
