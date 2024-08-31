Feature: Automating the website

  @webAuto
  Scenario: getting the cookies
    Given launch the browser
    When get the cookie data
    And login to the site

  @webAuto1
  Scenario: getting the cookies
    Given launch the browser
    And login to the site
    And get names in list

  @webAuto2
  Scenario: getting the cookies
    Given launch the browser
    When get salary in web table

  @webAuto3
  Scenario: getting the cookies
    Given launch the browser
    When get alert

  @webAuto4
  Scenario: Drag and drop
    Given launch the browser
    When drag and drop
