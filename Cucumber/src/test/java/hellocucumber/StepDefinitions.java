package hellocucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class StepDefinitions {

    private static BuyProductActuator buyProductActuator;
    private static DeleteProductActuatorAdmin deleteProductActuatorAdmin;
    private String webDriver = "webdriver.chrome.driver";
    private String path = "..\\Selenium\\chromedriver.exe";

    // Initialize the user session
    @Before
    public void BuyProductInitUser() {
        System.out.println("--------------- INITIALIZING TEST - OPENING WEBPAGE ---------------");
        if (buyProductActuator == null) {
            buyProductActuator = new BuyProductActuator();
        }
        buyProductActuator.initSessionAsUser(webDriver, path);
    }

    // Initialize the admin session
    @Before
    public void DeleteProductInitAdmin() {
        System.out.println("--------------- INITIALIZING TEST - OPENING WEBPAGE ---------------");
        if (deleteProductActuatorAdmin == null) {
            deleteProductActuatorAdmin = new DeleteProductActuatorAdmin();
        }
        deleteProductActuatorAdmin.initSessionAsAdmin(webDriver, path);
    }

    // Scenario 1: User buys an item from the store

    // Simulate user login to the store
    @Given("a user is logged into the PrestaShop store with {string} and {string}")
    public void aUserIsLoggedIntoThePrestaShopStore(String email, String password) {
        buyProductActuator.goToLogin();
        buyProductActuator.enterLoginInfo(email, password);
    }

    // Simulate user adding an item to the shopping cart
    @Given("the user adds an item to their shopping cart")
    public void theUserAddsTheItemToTheirShoppingCart() {
        buyProductActuator.addProductToCart();
    }

    // Simulate user buy the item through the checkout and payment process
    @When("the user buy the item")
    public void theUserProceedsToCheckoutAndCompletesPayment() {
        buyProductActuator.proceedToCheckout();
        buyProductActuator.enterAddressDetailsAndContinue();
        buyProductActuator.confirmShippingMethodAndContinue();
        buyProductActuator.enterPaymentDetailsAndConfirm();
    }

    //verify that the product purchase successfully confirmed
    @Then("the product purchase successfully confirmed")
    public void theOrderConfirmationPageIsDisplayed() {
        buyProductActuator.verifySuccessfulProductBuy();
    }

    /*
    // $$*TODO* explain what this step does$$
    @Then("the item is marked as \"sold\" in the store's inventory")
    public void theItemIsMarkedAsSoldInTheStoresInventory() {
        // Verify that the item is marked as "sold" in the inventory
        System.out.println("Item is marked as \"sold\" in the store's inventory.");
        // Assert that the item is marked as sold (simulated)
        Assertions.assertTrue(true, "Item should be marked as sold.");
    }
    */

    // Scenario 2: Admin deletes an item from the store

    // Simulate admin login to the admin panel
    @Given("an admin is logged in with {string} and {string}")
    public void anAdminIsLoggedIntoThePrestaShopAdminPanel(String email, String password) {
        deleteProductActuatorAdmin.enterLoginInfoAdmin(email, password);
        System.out.println("Admin is logged into the PrestaShop admin panel.");
    }

    //simulate user login to the store
    @And("a user is logged in with {string} and {string}")
    public void aUserIsLoggedIntoTheStore(String email, String password) {
        buyProductActuator.goToLogin();
        buyProductActuator.enterLoginInfo(email, password);
    }

    // simulate user adding an item to the shopping cart
    @And("the user has added an item to their shopping cart")
    public void theUserAddedItemToCart() {
        buyProductActuator.addProductToCart();
    }

    // Simulate admin deleting the item from the store
    @When("the admin deletes the item from the store")
    public void theAdminDeletesTheItemFromTheStore() {
        deleteProductActuatorAdmin.deleteProduct();
    }

    // Verify that the user cannot purchase the deleted item
    @Then("the user should not be able to purchase the deleted item")
    public void theUserFailToBuyTheItem() {
        // Verify that the user cannot purchase the item
        buyProductActuator.verifyUnsuccessfulProductBuy();
    }
}