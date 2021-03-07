Feature: Supervisor changes password

  As a supervisor
  I would like to changes my old password to a new password.

  Scenario Outline: Supervisor changes password (Normal Flow)

    Given I have a supervisor account "<UserID>" with password "<PreviousPassword>" and email "<Email>"
    When I change the old password "<PreviousPassword>" to a new password "<NewPassword>"
    Then the user password is now "<NewPassword>"
    Examples:
      | UserID   | PreviousPassword  | Email           | NewPassword |
      | UserAcct | previousPassword  | email@demo.com  | newPassword |

  Scenario Outline: supervisor changes to a password that has already been used (Alternative Flow)

    Given I have a user "<UserID>" with password "<Password>" and email "<Email>"
    And I have a supervisor account "<UserID1>" with password "<DifferentPassword>" and email "<Email1>"
    When I change the password "<DifferentPassword>" to a new password "<NewPassword>"
    Then the user password is now "<NewPassword>"
    Examples:
      | UserID   | Password   | Email          | UserID1   | DifferentPassword   | Email1          | NewPassword |
      | UserAcct | MyPassword | Email@demo.com | UserAcct1 | diffPassword        | Email1@demo.com | MyPassword  |

  Scenario Outline: Supervisor changes to new password with space (Error Flow)

    Given I have a supervisor account "<UserID>" with password "<PreviousPassword>" and email "<Email>"
    When I change the old password "<PreviousPassword>" to a new password "<NewPassword>"
    Then the error message "Password contains invalid character" is returned
    Examples:
      | UserID   | PreviousPassword  | Email           | NewPassword    |
      | UserAcct | previousPassword  | email@demo.com  | new pass word  |



