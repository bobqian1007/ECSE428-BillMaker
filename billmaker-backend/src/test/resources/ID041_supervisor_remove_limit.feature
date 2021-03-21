@Sprint3
Feature: supervisor remove limit

  As an supervisor user,
  I would like to remove the limit of my account of my supervisee,
  So that he/she can buy goods as much as I want every month.

  Background:
    Given I have a supervisor account
	And I have a supervisee

  Scenario: Remove the account limit (Normal Flow)
    Given my supervisee have account limit
    When I remove his/her account limit
    And I request for his/her account limit
    Then The account limit should be infinity

  Scenario: Edit the account limit to zero (Alternate Flow)
    Given my supervisee do not have account limit
    When I remove his/her account limit
    And I request for his/her account limit
    Then The account limit should be infinity