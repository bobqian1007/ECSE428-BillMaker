
Feature: Edit the limit of the bill

  As a individual user,
  I would like to edit the limit of the bill
  So that I can change the limitation of the stuff I can buy

  Background:
    Given I logged in as an individual user

  Scenario: Edit the account limit to a positive number (Normal Flow)
    When I edit the account limit to a positive number
    And I request for the account limit
    Then The account limit should match with the value we set

  Scenario: Edit the account limit to zero (Alternate Flow)
    When I edit the account limit to zero
    And I request for the account limit
    Then The account limit should match with the value we set

  Scenario: Edit the account limit to a negative number(Error Flow)
    When I edit the account limit to zero
    Then The system should give me an error message of the limit should not be negative