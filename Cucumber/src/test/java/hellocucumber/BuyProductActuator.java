package hellocucumber;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyProductActuator {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public void initSessionAsUser(String webDriver, String path) {
        // Set up WebDriver for Chrome
        System.setProperty(webDriver, path);

        // Initialize Chrome driver
        this.driver = new ChromeDriver();

        // WebDriver wait to ensure elements are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Launch PrestaShop website
        driver.get("http://localhost/my_shop/");

        // Maximize window to ensure proper display
        driver.manage().window().setPosition(new Point(700, 5));

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }

    public void goToLogin() {
        // Navigate to the login page by clicking the login link
        driver.findElement(By.xpath("//body/main[1]/header[1]/nav[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[1]/a[1]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/section/div/div/div/section/div/section/form/div/div[1]/label")));
    }

    public void enterLoginInfo(String username, String password) {
        // locate the username input box and enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"field-email\"]"))).sendKeys(username);
        // locate the password input box and enter password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"field-password\"]"))).sendKeys(password);
        // locate sign in button and press
        driver.findElement(By.xpath("//*[@id=\"submit-login\"]")).click();
        // Wait for the dashboard to load
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Check if login was successful
        try {
            // Wait for a dashboard-specific element (e.g., user name)
            driver.findElement((By.xpath("//*[@id='_desktop_user_info']/div[1]/a[2]/span[1]")));
            System.out.println("Login successful.");
        } catch (Exception e) {
            System.out.println("Login failed. Redirecting to registration.");
            registerUser(username, password);
        }

    }

    // Function to register the user
    public void registerUser(String username, String password) {
        String firstName = "John";
        String lastName = "Levi";
        String birthday = "1990-01-01";
        // Navigate to the registration page
        driver.findElement(By.xpath("//*[@id='content']/div[1]/a[1]")).click();

        // Enter personal information
        driver.findElement(By.xpath("//*[@id='field-id_gender-1']")).click();
        driver.findElement(By.xpath("//*[@id='field-firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//*[@id='field-lastname']\n")).sendKeys(lastName);
        driver.findElement(By.xpath("//*[@id='customer-form']/div[1]/div[4]/div[1]/input[1]")).sendKeys(username);
        driver.findElement(By.xpath("//*[@id='customer-form']/div[1]/div[5]/div[1]/div[1]/div[1]/input[1]")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name=\"birthday\"]")).sendKeys(birthday);
        driver.findElement(By.xpath("//input[@name=\"psgdpr\"]")).click();
        driver.findElement(By.xpath("//input[@name=\"customer_privacy\"]")).click();
        // Click on the continue button
        driver.findElement(By.xpath("//*[@id='customer-form']/footer[1]/button[1]")).click();
    }

    public void addProductToCart() {
        /**
         * Adds a product to the cart on PrestaShop.
         *
         * This method performs the following actions:
         * 1. Locates the search bar and enters the product name.
         * 2. Clicks on the first product from the search results.
         * 3. Clicks the 'Add to Cart' button.
         *
         * @throws InterruptedException If the Thread.sleep is interrupted.
         */
        // Search for a product (e.g., "T-Shirt")
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/header/div[2]/div/div[1]/div[2]/div[2]/form/input[2]"))).sendKeys("T-Shirt" + Keys.RETURN);

        // Click on the first product in the search results
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/section/div/div/div/section/section/div[3]/div[1]/div"))).click();

        // Add the product to the cart
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/main/section/div/div/div/section/div[1]/div[2]/div[2]/div[2]/form/div[2]/div/div[2]/button"))).click();
    }

    public void proceedToCheckout() {
        // Wait for the checkout button to load and click on it
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='blockcart-modal']/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/a[1]"))).click();

        // Wait for the checkout page to load and click on checkout button
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/main[1]/section[1]/div[1]/div[1]/div[1]/section[1]/div[1]/div[2]/div[1]/div[2]/div[1]/a[1]"))).click();
    }


    public void enterPersonalInformationAndContinue(String firstName, String lastName, String email, String password, String birthday){
        // Wait for the personal information section to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkout-personal-information-step']/h1[1]")));

        // Enter personal information
        driver.findElement(By.xpath("//*[@id='field-id_gender-1']")).click();
        driver.findElement(By.xpath("//*[@id='field-firstname']")).sendKeys(firstName);
        driver.findElement(By.xpath("//*[@id='field-lastname']\n")).sendKeys(lastName);
        driver.findElement(By.xpath("//*[@id='customer-form']/div[1]/div[4]/div[1]/input[1]")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id='customer-form']/div[1]/div[5]/div[1]/div[1]/div[1]/input[1]")).sendKeys(password);
        driver.findElement(By.xpath("//input[@name=\"birthday\"]")).sendKeys(birthday);
        driver.findElement(By.xpath("//input[@name=\"psgdpr\"]")).click();
        driver.findElement(By.xpath("//input[@name=\"customer_privacy\"]")).click();
        // Click on the continue button
        driver.findElement(By.xpath("//*[@id='checkout-personal-information-step']/div[1]/div[1]/form[1]/button[1]")).click();
    }

    public void enterAddressDetailsAndContinue() {
        String firstName = "John";
        String lastName = "Levi";
        String address = "3171 David Mission";
        String city = "West Emilytown";
        String state = "Michigan";
        String zipCode = "13289";
        String country = "United States";

        // Wait for the personal details section to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkout-addresses-step']/h1[1]")));

        //check if the address details are already filled in the past, if not fill them
        try {
            driver.findElement(By.xpath("//*[@id='delivery-addresses']/article[1]"));
        } catch (NoSuchElementException e) {

            // Enter personal details
            //driver.findElement(By.xpath("//input[@name=\"firstname\"]")).sendKeys(firstName);
            //driver.findElement(By.xpath("//input[@name=\"lastname\"]\n")).sendKeys(lastName);
            driver.findElement(By.xpath("//input[@name=\"address1\"]")).sendKeys(address);
            driver.findElement(By.xpath("//input[@name=\"city\"]")).sendKeys(city);

            //select state from dropdown
            WebElement dropdown_state = driver.findElement(By.xpath("//select[@name='id_state']"));
            Select select_state = new Select(dropdown_state);
            select_state.selectByVisibleText(state);

            driver.findElement(By.xpath("//input[@name=\"postcode\"]")).sendKeys(zipCode);

            //select country from dropdown
            WebElement dropdown_country = driver.findElement(By.xpath("//select[@name=\"id_country\"]"));
            Select select_country = new Select(dropdown_country);
            select_country.selectByVisibleText(country);
        }
        finally {
            // Click on the continue button
            driver.findElement(By.xpath("//button[@name=\"confirm-addresses\"]")).click();
        }
    }

    public void confirmShippingMethodAndContinue() {
        // Wait for the shipping method section to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkout-delivery-step']/h1[1]")));

        // Click on the continue button
        driver.findElement(By.xpath("//button[@name=\"confirmDeliveryOption\"]")).click();
    }

    public void enterPaymentDetailsAndConfirm() {
        // Wait for payment section to load
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='checkout-payment-step']/h1[1]")));

        // Select a payment method (e.g., "Pay by Cash on Delivery")
        driver.findElement(By.xpath("//*[@id=\"payment-option-1\"]")).click();

        // agree to terms and conditions
        driver.findElement(By.xpath("//*[@id='conditions-to-approve']/ul[1]/li[1]/div[1]/span[1]/input[1]")).click();

        // Click on the order confirmation button
        driver.findElement(By.xpath("//*[@id='payment-confirmation']/div[1]/button[1]")).click();
    }


    @Test
    public void verifySuccessfulProductBuy() {
        /**
         * Verifies that the product is successfully bought.
         * Check that the order confirmation page is displayed.
         */

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content-hook_order_confirmation']/div[1]/div[1]/div[1]/h3[1]")));
        String messageText = successMessage.getText();
        //extract only the letters in the message using regex
        Pattern pattern = Pattern.compile("[a-zA-Z ]+");
        Matcher matcher = pattern.matcher(messageText);
        if (matcher.find()) {
            messageText = matcher.group();
        }
        Assert.assertEquals("YOUR ORDER IS CONFIRMED", messageText);
    }

    public void verifyUnsuccessfulProductBuy() {
        // User is proceeding to checkout
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='blockcart-modal']/div[1]/div[1]/div[2]/div[1]/div[2]/div[1]/div[1]/a[1]"))).click();

        // Wait for the element to be visible
        WebElement subtotalLabel = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class, 'label js-subtotal')]")));

        // Get the text of the element
        String actualText = subtotalLabel.getText();

        // Define the expected text
        String expectedText = "0 items";

        Assert.assertEquals(expectedText, actualText);
    }
}