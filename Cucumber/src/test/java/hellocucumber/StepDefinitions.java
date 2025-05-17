package hellocucumber;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;

public class StepDefinitions {

    private static BuyProductActuator buyProductActuator;
//    private static DeleteProductActuatorAdmin deleteProductActuatorAdmin;
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
}