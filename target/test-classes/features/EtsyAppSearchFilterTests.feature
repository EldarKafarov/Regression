@regression @ui @MB2P-115
Feature: Validating Etsy search and filter functionalities

  Background: Repeated first steps in each scenario
    Given user navigates to Etsy application
    When user searches for "carpet"

  # @Before method will run
  Scenario: Validating price range filter functionality for searched item
    And user applies price filter over 1000
    Then user verifies that item prices are over 1000
    # @After method will run

    # @Before method will run
  @smoke
  Scenario: Validating search results
    Then user validates search result items contain keyword "carpet"
    # @After method will run

