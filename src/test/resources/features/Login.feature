@Regression

Feature: Login into Rapidscoop Application

  @Login
  Scenario: Login to Web Application
    Given User login into Rapidscoop Web Application
    And I close my web browser

  @Login
  Scenario: Create instant post in twitter
    Given User login into Rapidscoop Web Application
    Then Create a instant post for twitter
    And I close my web browser

  @Login
  Scenario: Create instant post in linkedin
    Given User login into Rapidscoop Web Application
    Then Create a instant post for linkedin
    And I close my web browser

  @Login1
  Scenario: Create schedule post in twitter
    Given User login into Rapidscoop Web Application
    Then Create a schedule post for twitter
    And I close my web browser

  @Login
  Scenario: Create schedule post in linkedin
    Given User login into Rapidscoop Web Application
    Then Create a schedule post for linkedin
    And I close my web browser

  @Login
  Scenario: Create instant post in twitter and linkedin
    Given User login into Rapidscoop Web Application
    Then Create instant post in twitter and linkedin
    And I close my web browser

  @Login
  Scenario: Create schedule post in twitter and linkedin
    Given User login into Rapidscoop Web Application
    Then Create schedule post in twitter and linkedin
    And I close my web browser





