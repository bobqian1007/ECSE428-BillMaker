@Sprint1
Feature: An individual user delete a previous bill record.

  As an individual user, I want to delete a record in the system, so the unwanted records are removed.

  Background:
    Given I logged in as an individual user

  Scenario: Delete a bill record by bill id (Normal Flow)
    When I delete a bill record with a bill id
    And I request for all the bill records
    Then I should not see the record in the total record list

  Scenario: Delete a bill record by shop name and time (Alternate Flow)
    When I delete a bill record at a time with a shop name
    And I request for all the bill records
    Then I should not see the record in the total record list

  Scenario: Delete a bill record with a wrong shop name at a time (Error Flow)
    When I delete a bill record at a time with a shop name that does not exist
    Then A message indicating "Record not exists" is generated