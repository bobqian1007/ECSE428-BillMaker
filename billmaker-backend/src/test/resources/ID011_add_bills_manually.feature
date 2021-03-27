@Sprint1
Feature: add bills manually

  @normalAddBillsManually
  Scenario: Type in bill information in order to record the receipt
    When user add bill information into the system
    Then user could find this bill under bill section

  @alternativeAddBillsManually
  Scenario: add bill with no location
    When user add bill information into the system with no location information
    Then user could find this bill under bill section


  @errorAddBillsManually
  Scenario: add bill with negative amount, the bill cannot be created
    When user add bill information with negative amount
    Then the bill instance cannot be created
