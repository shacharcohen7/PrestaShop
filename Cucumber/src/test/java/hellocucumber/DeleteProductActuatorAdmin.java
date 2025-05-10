package hellocucumber;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DeleteProductActuatorAdmin {
    private static WebDriver driver;
    private static WebDriverWait wait;

    public void initSessionAsAdmin(String webDriver, String path) {
        // Set up WebDriver for Chrome
        System.setProperty(webDriver, path);

        // Initialize Chrome driver
        this.driver = new ChromeDriver();

        // WebDriver wait to ensure elements are loaded (40 sec max)
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));

        // Launch PrestaShop website
        driver.get("http://localhost/my_shop/admin4150un9qxx4ji25cqkv/");

        // Maximize window to ensure proper display
        driver.manage().window().maximize();

        System.out.println("Driver setup finished for - " + driver.getTitle());
    }


    public void enterLoginInfoAdmin(String username, String password) {
        // locate the username input box and enter username
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"email\"]"))).sendKeys(username);
        // locate the password input box and enter password
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name=\"passwd\"]"))).sendKeys(password);
        // locate Log in button and press
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='submit_login']"))).click();
        wait.until(ExpectedConditions.titleContains("Home"));
        // Wait for the dashboard to load
    }

    public void deleteProduct() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//html/body/nav/div/ul/li[6]/a"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='subtab-AdminProducts']/a[1]"))).click();

        // Locate the input field and clear it before entering text
        WebElement productNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='product_name']")));
        productNameField.clear(); // Clear existing text
        productNameField.sendKeys("T-Shirt" + Keys.ENTER); // Enter new text and trigger search

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div[3]/div[2]/div/form[2]/table/tbody/tr/td[11]/div/div/a[2]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@data-title, \"Delete selection\")]"))).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(@class, 'btn-confirm-submit')]"))).click();
    }

}
