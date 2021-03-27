@Sprint3
Feature: An individual user removes a category.

  As an individual user, I want to remove a category, so that I can delete all related things of this category.

  Background:
    Given I am an individual user

  Scenario: Remove a category with the category name in string (Normal Flow)
    When I remove a category by specifying a name in string
    Then I should see the specified category removed from the list of all categories

  Scenario: Remove a category with the category name in number (Alternate Flow)
    When I remove a category by specifying a name in number
    Then I should see the specified category removed from the list of all categories

  Scenario: Remove a category with an empty category name (Error Flow)
    When I remove a category without specifying a name
    Then I should not be able to remove this category