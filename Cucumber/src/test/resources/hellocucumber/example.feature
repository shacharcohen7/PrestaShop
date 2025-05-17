Feature: A set of scenarios for testing the "PrestaShop" application

  Scenario Outline: User buys an item from the store
    Given a user is logged into the PrestaShop store with "<Email>" and "<Password>"
    And the user adds an item to their shopping cart
    When the user buy the item
    Then the product purchase successfully confirmed

    Examples:
      | Email           | Password  |
      | john@gmail.com | JohnLevi456  |