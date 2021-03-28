@Sprint4
Feature: An individual user edits previous bill's date

  As a individual user, I would like to edit previous bill's date, so that I can correct the error of the previous bill

  Background:
    Given the user is logged in as an individual user

  Scenario: Individual user change the date of the bill to a different date (Normal Flow)
    And there are bills recorded in the system
    When the user changes the date of a bill to a different date
    Then the user should see that the date is successfully changed

  Scenario: Individual user change the date of the bill to the same date (Alternate Flow)
    And there are bills recorded in the system
    When the user changes the date of a bill to the same date
    Then the user should see that the date remains the same


  Scenario: Individual user change the date of the bill to empty (Error Flow)
    And there are bills recorded in the system
    When the user changes the date of a bill to empty
    Then the user should not be able to perform this change