@Sprint1
Feature: Individual user logs out

	As a individual user
	I would like to log out
	So that access to the system functions from my account are restricted until I log in

	Scenario: Individual user logs out successfully (Normal flow)

		Given I am logged into the Bill Management System as an individual user
		When I log out
		Then I can no longer access my account
		
	Scenario: Individual user logs out without logging in first (Error flow)

		Given I am not logged into the Bill Management System
		When I log out
		Then the system will display the "There is no one logged in!" error message