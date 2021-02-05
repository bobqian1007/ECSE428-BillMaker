Feature: Edit the amount of the bill

As a individual user,
I would like to edit the amount of the bill
So that I can correct the errors of the previous bills

Scenario: Individual user change the amount of the bill to a different amount (Normal Flow)

  Given user is logged into the Bill management system as an indivudual user
  And there is a bill recorded in the system
  When user change the amount of the bill to a different amount
  And user query the amount of the bill
  Then the amount of the bill has changed to the expected amount



Scenario: Individual user change the amount of the bill to the same amount (Alternate Flow)

  Given user is logged into the Bill management system as an indivudual user
  And there is a bill recorded in the system
  When user change the amount of the bill to a same amount
  And user query the amount of the bill
  Then the amount of the bill has changed to the expected amount


Scenario: Individual user change the amount of the bill to a negative amount (Error Flow)

  Given user is logged into the Bill management system as an indivudual user
  And there is a bill recorded in the system
  When user change the amount of the bill to a same amount
  Then the system notifies the user the amount should not be positive
