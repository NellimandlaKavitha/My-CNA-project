@signup
Feature: User Signup Flow on MeConnect

  Background: 
    # This runs before every scenario in this feature file
    Given User is on the Landing Page

  @smoke
  Scenario: Successful New Account Creation and Profile Setup
    When User clicks on "Sign In" button
    And User navigates to the MeConnect Login Page
    And User clicks on "Create New Account" link
    Then User is on the "Create your MeConnect account" page
    # Account Creation Steps
    When User enters email "testuserr4@example.com"
    And User enters new password "Password123!" and confirms it
    And User agrees to the terms and conditions
    And User clicks on the "Next" button
    # Profile Setup Steps
    Then User is navigated to the "Setup your profile to continue" page
    When User enters personal details:
      | Field         | Value      |
      | First Name    | John       |
      | Last Name     | Doe        |
      | Date of Birth | 01-01-2003 |
      #| Gender        | Male       |
    And User clicks on the "Continue" button on the profile setup page
    # Updates and Final Sign-in Steps
    Then User is navigated to the "Select the updates you want to receive" page
    And User clicks on the final "Continue" button
    Then User is signed in and landed on the main site
