@Sprint3
Feature: Individual User deletes account

  As a individual user
  I would like to delete my account
  So that I am not in the system anymore.

  Scenario Outline: Individual User deletes account (Normal Flow)

    Given I have a user "<UserID>" with password "<Password>" and email "<Email>"
    When I delete the user "<UserID>" with password "<Password>" and email "<Email>"
    Then the user "<UserID>" does not exist in the system
    Examples:
      | UserID   | Password  | Email           |
      | UserAcct | mypswd    | email@demo.com  |


  Scenario Outline: Non-existing User deletes account (Error Flow)

    Given I don't have a user "<UserID>" with password "<PreviousPassword>" and email "<Email>"
    When I delete the user "<UserID>" with password "<Password>" and email "<Email>"
    Then the user "<UserID>" does not exist in the system
    Examples:
      | UserID   | Password  | Email           |
      | UserAcct | mypswd    | email@demo.com  |



