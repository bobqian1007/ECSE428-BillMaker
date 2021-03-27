@Sprint1
Feature:  Supervisor user sends supervise request to another user
  This feature verifies the functionality that individual user can request to become another individual user's supervisor.
  (As a individual user, I send the request to another individual user, so I can supervise that individual user. )

  Background:
    Given I logged in as a supervisor user

  Scenario: Supervisor user sends supervise request to an individual user (Normal Flow)
    And The other user is an individual user
    When I send the supervise request to the other user
    Then The other user receives the supervisor's request


  Scenario: Supervisor user sends supervise request to a supervisor user (Error Flow)
    And The other user is a supervisor user
    When I send the supervise request to the other user
    Then I shall be notified that the supervise request is not supported by the system