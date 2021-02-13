Feature: Approve Supervisor Request

  As an individual user
  I would like to accept the request from supervisor
  So that I my bill account will be supervised

  Background:
    Given I have an account
    And I have no supervisor
    And The supervisor has an account
    And the supervisor has sent a supervise request to me


  Scenario: User in family account accept the supervisor request
    When I approve the supervise request
    Then I verify that my account has a supervisor


  Scenario: User in family account refuse the supervisor request
    When I refuse the supervise request
    Then I verify that my account has no supervisor


  Scenario: User in family account accept the second supervisor request
    When I approve the the supervise request
    Then I shall be notified that each user can not have two supervisor




