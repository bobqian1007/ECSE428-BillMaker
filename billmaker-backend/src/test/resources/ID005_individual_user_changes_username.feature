Feature: Individual User changes username

  As a individual user
  I would like to changes my old username to a new username.

  Scenario Outline: Individual User changes username (Normal Flow)

    Given an existing user "<PreviousUserID>" with password "<Password>" and email "<Email>"
    When the user wants to change the old username "<PreviousUserID>" to a new username "<NewUserID>"
    Then the user's username is now "<NewUserID>"
    Examples:
      | PreviousUserID   | Password          | Email                 | NewUserID   |
      | UserAcct         | mypassword        | email@demo.com        | newUsername |


  Scenario Outline: Individual User changes username twice (Alternative Flow)

    Given an existing individual user "<PreviousUserID>" with password "<Password>" and email "<Email>"
    When the user wants to change the old username "<PreviousUserID>" to a new username "<NewUserID1>"
    Then the user's username is now "<NewUserID1>"
    When the user wants to change the old username "<NewUserID1>" to a new username "<NewUserID2>"
    Then the user's username is now "<NewUserID2>"
    Examples:
      | PreviousUserID   | Password   | Email                  | NewUserID1          | NewUserID2          |
      | UserAcct         | MyPassword | email@demo.com         | newUsername1        | newUsername2        |


  Scenario Outline: Individual User changes to a username that has already been used (Error Flow)

    Given an existing user "<PreviousUserID>" with password "<Password>" and email "<Email>"
    And I have another existing user "<SameUserID>" with password "<DifferentPassword>" and email "<DifferentEmail>"
    When the user wants to change the old username "<PreviousUserID>" to a new username "<SameUserID>"
    Then the username is not changed
    And an error message "Username already exists" is returned for changing one's username
    Examples:
      | PreviousUserID   | Password   | Email          | SameUserID   | DifferentPassword   | DifferentPassword
      | UserAcct         | MyPassword | Email@demo.com | UserAcct1    | diffPassword        | diffEmail@demo.com
