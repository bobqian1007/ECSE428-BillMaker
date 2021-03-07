@Sprint1
Feature: Create Supervisor User

  As an supervisor user
  I would like to create a new supervisor account
  So that I can login as a supervisor user

  Scenario Outline: Create A Valid Supervisor User Account (Normal Flow)
    When the username "<user_name>" and password "<password>" and email "<email>" are entered
    Then a supervisor account is created with username "<user_name>" and password "<password>" and email "<email>"
    Examples:
      | user_name | password | email        |
      | Abby      | 3001old  | 123@mail.com |
      | Bob       | 645aer   | 456@mail.com |
      | Cindy     | bb19AS2  | 789@mail.com |


  Scenario Outline: Create Several Supervisor User Accounts (Alternate Flow)
    When the username "<user_name>" and password "<password>" and email "<email>" are entered
    When the username "<user_name1>" and password "<password1>" and email "<email1>" are entered
    Then a supervisor account is created with username "<user_name>" and password "<password>" and email "<email>"
    Then a supervisor account is created with username "<user_name1>" and password "<password1>" and email "<email1>"
    Examples:
      | user_name | password | email        | user_name1 | password1 | email1       |
      | Abby      | 3001old  | 123@mail.com | Dickinson  | sfvn1224  | 456@mail.com |
      | Bob       | 645aer   | 789@mail.com | Ella       | 6DAD334   | 987@mail.com |
      | Cindy     | bb19AS2  | 654@mail.com | Ferry      | FDEAce12  | 321@mail.com |


  Scenario Outline: Create A Supervisor Individual User Account with Duplicate Usernames (Error Flow)
    Given a supervisor user has been created with username "<user_name>" and password "<password>" and email "<email>"
    When the username "<user_name1>" and password "<password1>" and email "<email1>" are entered
    Then the error message "Username already exists" is returned
    Examples:
      | user_name | password | email        | user_name1 | password1 | email1       |
      | Abby      | 3001old  | 123@mail.com | Abby       | sfvn1224  | 321@mail.com |
      | Bob       | 645aer   | 456@mail.com | Bob        | 6DAD334   | 654@mail.com |
      | Cindy     | bb19AS2  | 789@mail.com | Cindy      | FDEAce12  | 987@mail.com |



