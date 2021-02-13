Feature: Create Individual User

  As an individual user
  I would like to create a new individual account
  So that I can login as an individual user

  Scenario Outline: Create A Valid Individual User Account (Normal Flow)
    When the individual username "<user_name>" and password "<password>" and email "<email>" are entered
    Then an individual account is created with username "<user_name>" and password "<password>" and email "<email>"
    Examples:
      | user_name  | password  | email         |
      | Abby1      | 3001old1  | 1231@mail.com |
      | Bob1       | 645aer1   | 4561@mail.com |
      | Cindy1     | bb19AS21  | 7891@mail.com |

  Scenario Outline: Create Several Individual User Accounts (Alternate Flow)
    When the individual username "<user_name>" and password "<password>" and email "<email>" are entered
    And the individual username "<user_name1>" and password "<password1>" and email "<email1>" are entered
    Then an individual account is created with username "<user_name>" and password "<password>" and email "<email>"
    Then an individual account is created with username "<user_name1>" and password "<password1>" and email "<email1>"
    Examples:
      | user_name  | password  | email         | user_name1  | password1  | email1        |
      | Abby2      | 3001old2  | 1232@mail.com | Dickinson2  | sfvn12242  | 4562@mail.com |
      | Bob2       | 645aer2   | 7892@mail.com | Ella2       | 6DAD3342   | 9872@mail.com |
      | Cindy2     | bb19AS22  | 6542@mail.com | Ferry2      | FDEAce122  | 3212@mail.com |

  Scenario Outline: Create An Invalid Individual User Account with Duplicate Usernames (Error Flow)
    Given an individual user has been created with username "<user_name>" and password "<password>" and email "<email>"
    When the individual username "<user_name1>" and password "<password1>" and email "<email1>" are entered
    Then the error message "Username already exists" is returned for individual user creation
    Examples:
      | user_name  | password  |  email        | user_name1  | password1  | email1        |
      | Abby3      | 3001old3  | 1233@mail.com | Abby3       | sfvn12243  | 3213@mail.com |
      | Bob3       | 645aer3   | 4563@mail.com | Bob3        | 6DAD3343   | 6543@mail.com |
      | Cindy3     | bb19AS23  | 7893@mail.com | Cindy3      | FDEAce123  | 9873@mail.com |

