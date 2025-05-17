# Testing PrestaShop using Cucumber
This directory contains the cucumber files for testing the Admin Deleting Store Items and User Purchasing Workflow module of the PrestaShop application.

## Running the tests
Run ```mvn test``` to run all the tests.

## Feature files
The behaviors that we tested are in the feature files that inside the [resources/hellocucumber](resources/hellocucumber) directory. See the files for a detailed description of the tests.

### Tested Behaviors
The following scenarios are tested in this module:

1.  Scenario Outline: User buys an item from the store
   
    Given a user is logged into the PrestaShop store with "<Email_>" and "<Password_>"
    
    And the user adds an item to their shopping cart
   
    When the user buy the item
   
    Then the product purchase successfully confirmed
    

## Step files
The step files in the [src/test/java/hellocucumber](src/test/java/hellocucumber) directory contain the code that defines how each sentence in the feature files is translated to Selenium actions. See the files for a detailed description of the implementation.

## Notes:
We made the following assumptions about the system for the purpose of the tests:
  * If there is no registered user with the following details: john@gmail.com and password JohnLevi456, the test will automatically register the user.
  * A product named "T-Shirt" is available in the store.

