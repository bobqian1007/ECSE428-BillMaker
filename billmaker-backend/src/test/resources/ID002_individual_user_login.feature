@Sprint1
Feature: Individual User Login

  As a individual user
  I would like to login to the household accountbook system with username and password

  Scenario Outline: Individual User Login (Normal Flow)

    Given I have a user "<UserID>" with password "<Password>"
    When I use the username "<UserID>" and password "<Password>" to login
    Then the returned state is "<State>"
    Examples:
      | UserID   | Password   | State |
      | UserAcct | MyPassword | 200   |

  Scenario Outline: Individual User Login when UserID does not exist (Error Flow)
    Given I do not have a user "<UserID>" with password "<Password>"
    When I use the username "<UserID>" and password "<Password>" to login
    Then the returned state is "<State>"
    Examples:
      | UserID   | Password   | State |
      | UserAcct | MyPassword | 401   |

  Scenario Outline: Individual User Login when UserID and Password does not match (Error Flow)
    Given I have a user "<UserID>" with password "<Password>"
    When I use the username "<UserID>" and password "<Password_wrong>" to login
    Then the returned state is "<State>"
    Examples:
      | UserID   | Password   | Password_wrong | State |
      | UserAcct | MyPassword | MyWrongPw      | 401   |

