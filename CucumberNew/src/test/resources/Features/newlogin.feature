@newlogin
Feature: Testing login feature

  Scenario Outline: testing login page
    Given Browser is open
    And user is on login page
    When user enters <username> and <password>
    And user click on login
    Then user is navigated to the home page

    Examples: 
      | username | password    |
      | student  | Password123 |
      | student  | Password123 |
