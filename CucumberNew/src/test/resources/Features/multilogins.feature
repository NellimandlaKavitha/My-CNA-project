@Multilogin
Feature: Login with multiple users

  Scenario Outline: Login using different user credentials
    Given I open the application
    And User clicks login "Sign In" button
    When I login with "<usernameKey>" and "<passwordKey>"
    Then I should see the homepage

    Examples: 
      | usernameKey | passwordKey |
      | username1   | password1   |
      | username2   | password2   |
      | username3   | password3   |
      | username4   | password4   |
      | username5   | password5   |
