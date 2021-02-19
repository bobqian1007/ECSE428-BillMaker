Feature: Individual User changes email address

  As a individual user
  I would like to changes my old email address to the new address I'm using.

  Scenario Outline: Individual User changes email address (Normal Flow)

    Given I have a user "<UserID>" with password "<Password>" and email "<PreviousEmail>"
    When I change the email "<PreviousEmail>" to the new email address "<NewEmail>"
    Then the user email address is now "<NewEmail>"
    Examples:
      | UserID   | Password   | PreviousEmail           | NewEmail |
      | UserAcct | MyPassword | previousEmail@demo.com  | newEmail@demo.com |


  Scenario Outline: Individual User changes email address twice (Alternate Flow)

    Given I have a user "<UserID>" with password "<Password>" and email "<PreviousEmail>"
    When I change the email "<PreviousEmail>" to the new email address "<FirstEmail>"
    Then the user email address is now "<FirstEmail>"
    When I change the email "<FirstEmail>" to the new email address "<SecondEmail>"
    Then the user email address is now "<SecondEmail>"
    Examples:
      | UserID   | Password   | PreviousEmail          | FirstEmail          | SecondEmail          |
      | UserAcct | MyPassword | previousEmail@demo.com | firstEmail@demo.com | secondEmail@demo.com |

  Scenario Outline: Individual User changes to a email address that has already been used (Error Flow)

    Given I have a user "<UserID>" with password "<Password>" and email "<Email>"
    When I change the email "<Email>" to the new email address "<NewEmail>"
    Then the error message "Email already exists" is returned for change individual user email address
    Examples:
      | UserID   | Password   | Email          | NewEmail       |
      | UserAcct | MyPassword | Email@demo.com | Email@demo.com |

