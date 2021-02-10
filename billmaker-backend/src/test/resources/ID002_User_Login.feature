Feature: User Login

  As a individual user
  I would like to login to the household accountbook system with username and password

  Scenario Outline: Individual User Login (Normal Flow)
    Given the system is running
    When submitting the username <UserID> and password <Password> to login
    Then the returned message is <State>

    Examples:
      | UserID | Password | State |
      | UserAcct | MyPassword | Success |

  Scenario Outline: Individual User Login when UserID does not exist (Error Flow)
    Given the system is running
    When submitting the username <UserID> and password <Password> to login
    Then the returned message is <State>
    And my account type is individual user
    Examples:
      | UserID  | Password | State |
      | UserAcct | MyPassword | Success |


  Scenario Outline: Individual User Login when UserID and Password does not match (Error Flow)
    Given the system is running
    When submitting the username <UserID> and password <Password> to login
    Then the returned message is <State>
    And my account type is not individual user
    Examples:
      | UserID   | Password   | State |
      | UserAcct | MyPassword | Error |


  Scenario Outline: Individual User Login when UserID and Password both does not exist (Error Flow)
    Given the system is running
    When submitting the username <UserID> and password <Password> to login
    Then the returned message is <State>
    And my account type is not individual user
    Examples:

      | UserID   | Password   | State |
      | UserAcct | MyPassword | Error |