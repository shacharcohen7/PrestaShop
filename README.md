# Software Quality Engineering - System Testing
This is a repository for the system-testing assignment of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called [PrestaShop](https://prestashop.com/).

PrestaShop is a popular open-source platform that allows users to create and manage online stores. It provides interface for administrators to manage products, categories, orders, and customers. Additionally, it includes a customer-facing store where users can browse products, add them to the cart, and complete purchases.

## Installation
To install PrestaShop and set up the testing environment:

1. Clone the PrestaShop repository:
```
git clone https://github.com/PrestaShop/PrestaShop.git
cd PrestaShop
```
2. Set up a web server with PHP (e.g. using XAMPP).

3. Create a MySQL database and configure PrestaShop to use it.

4. Install PrestaShop by following the setup wizard.
   If you're having trouble with the installation, check out this [video](https://www.youtube.com/watch?v=PypN1d6xHq0&t=544s).

5. Install the desired ChromeDriver from this [link](https://developer.chrome.com/docs/chromedriver/downloads?hl=he).
   The ChromeDriver should be compatible to your current Chrome browser version.

6. Add to the pom.xml file dependencies for Cucumber and Selenium.

## What we tested
We tested the product management and purchase workflow module. We chose to test the following user stories: 

*User story:* User buys an item from the store

*Preconditions:* A user is logged into the PrestaShop store and adds an item to their shopping cart.

*Expected outcome:* The product purchase is successfully confirmed.

*User story:* Admin deletes an item from the store and user can't buy it.

*Preconditions:* An admin and a user are logged into the PrestaShop store and the user has added the item to their shopping cart.

*Expected outcome:* The user should not be able to purchase the deleted item.

## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a behavior-driven testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results
We conducted tests using both Cucumber and Provengo. The generated test suites covered the primary interactions of the selected user stories. The tests are documented in the corresponding directories.
