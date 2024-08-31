Feature: Getting the count of search item in WooliesX Deli

Background: Hitting the endpoint and getting the response
  Given Send a GET request to the search endpoint
  Then I should receive a valid response

  @testData
  Scenario: Testing with testdata
    Given I have test data
    Then I log in with the test user

  @regex
  Scenario: Testing with regex
    Given passing regex value 100

  @API_tc_001
  Scenario: Verify the status code and status line in response
    Given The Response should contain the status code
    And Validate the status line
    
  @API_tc_002
  Scenario: Retrieving number of searches and fetching the headers
    When Get the search count from the response
    And Print the response headers 
    
  @API_tc_003
  Scenario: Validating the searchCount and ArticleCount
    Then Validate the fields in SearchCount node
    And Validate the ArticleCount matches the test data 
    
 @API_tc_004
  Scenario: Verifying the total search count
    When Get the sum of all searches and verify it matches with Total
    
  @API_tc_005
  Scenario: Verifying ArtcicleCount and UntraceableProductCount with Thershold
    And Get the article count and Verify count goes below the Threshold
    Then Verify the UntraceableProductCount should be 0 
    
    

 