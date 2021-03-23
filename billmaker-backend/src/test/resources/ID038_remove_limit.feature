@Sprint2
Feature: remove limit

  As an individual user,
  I would like to remove the limit of my account,
  So that I can buy goods as much as I want every month.

  Background:
    Given I have a account

  Scenario: Remove the account limit (Normal Flow)
    Given I have account limit
    When I remove the account limit
    And I request for the account limit
    Then The account limit should be infinity

  Scenario: Edit the account limit to zero (Alternate Flow)
    Given I do not have account limit
    When I remove the account limit
    And I request for the account limit
    Then The account limit should be infinity
