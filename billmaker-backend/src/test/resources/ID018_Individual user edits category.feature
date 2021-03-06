@Sprint3
Feature: Edit category's name

    As an individual user
    I would like to edit the name of the category
    So that I can adjust ambiguous category names

    Background:
        Given I am logged in as an individual user

    Scenario: Edit the name of an existing category to a valid name (Normal Flow)
        When I choose an existing category
        And edit its name
        Then the category's name should be the new name

    Scenario: Edit the name of an existing category to the same name (Alternate Flow)
        When I choose a existing category 
        And I edit its name
        Then the category's name should be unchanged

    Scenario: Edit the name of an non-existing category (Error Flow)
        When I choose an non-existing category
        And I try to edit its name
        Then the "Category does not exist!" error message appears

    Scenario: Edit the name of an existing category to a name that already exists (Error Flow)
        When I choose an existing category
        And I edit its name to an existing category's name
        Then the "Category name already exists!" error message will appear

    Scenario: Edit the name with an empty name
        When I choose a category
        And I edit its name with an empty string
        Then the "Name cannot be empty!" error message will be shown

