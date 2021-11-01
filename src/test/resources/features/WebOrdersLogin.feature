@regression @smoke @ui @MB2P-116
Feature: Validating WebOrders application login functionality

  # Test case description
  Scenario: Validating login functionality with valid credentials
    Given user navigates to weborders application
    When user provides username "Tester" and password "test" and clicks on login
    Then user validates application is logged in

  Scenario: Validating login functionality with invalid credentials
    Given user navigates to weborders application
    When user provides username "invalid" and password "invalid" and clicks on login
    Then user validates error message "Invalid Login or Password."


