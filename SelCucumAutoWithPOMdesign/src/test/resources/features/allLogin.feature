Feature: Login Functionality

  Background: 
    Given user opens salesforce application

  Scenario: Login error message validation
    When user is on "LoginPage"
    When I enter valid username only
    And click on Login button
    Then verify error message "Please enter your password."

  Scenario: Successful login with valid credentials
    When user is on "LoginPage"
    When I enter valid username and valid password
    And click on Login button
    When user is on "HomePage"
    Then verify Getting started heading "Getting Started"

  Scenario: Successful login with remember me checked
    When user is on "LoginPage"
    When I enter valid username and valid password
    When click remember me checkbox
    When click on Login button
    When user is on "HomePage"
    And click logout
    Then username is entered in the text field

  Scenario: Forgot Password without entering user credentials
    When user is on "LoginPage"
    When user click on forgotpassword link
    When user is on "ForgotPasswordPage"
    When user enter emailid and click continue
    When user is on "CheckYourEmailPage"
    Then user clicks returnToLogin

  Scenario: Forgot Password with invalid user credentials
    When user is on "LoginPage"
    When user enters invalid username and password
    And click on Login button
    Then verify error message "Please check your uesrname and password" 
