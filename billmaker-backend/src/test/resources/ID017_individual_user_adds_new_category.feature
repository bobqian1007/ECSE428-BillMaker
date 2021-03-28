@Sprint3
Feature: An individual user adds a new category.

  As an individual user, I want to add a new category, so that I can put related things into this category.

  Background:
    Given I am sign in an individual user

  Scenario: Add a category with the category name in string (Normal Flow)
    When I add a new category with a name in string
    Then I should see the newly added category in the list of all categories

  Scenario: Add a category with the category name in number (Alternate Flow)
    When I add a new category with a name in number
    Then I should see the newly added category in the list of all categories

  Scenario: Add a category with an empty category name (Error Flow)
    When I add a category without a name
    Then I should not be able to create this category