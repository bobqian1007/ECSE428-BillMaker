Feature: Generate Monthly Report
  As an individual user,
  I want to generate my monthly expense report,
  so I am able to know what I spend on for the month

  Background:
    Given I logged in as an individual user

  Scenario: User has records for a month
    Given I have records for a month
    When I generate the report for the month
    Then I should be able to see a report with the list of records for the month

  Scenario: User has no records for a month
    Given I have no records for a month
    When I generate the report for the month
    Then I should see a report without record for the month