package banking.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.gecko.driver", Utils.FIREFOX_PATH);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(Utils.BASE_URL);
    }

    @Test(dataProvider = "loginData", dataProviderClass = Utils.class)
    public void testLogin(String username, String password) {
        WebElement userField = driver.findElement(By.name("uid"));
        WebElement passField = driver.findElement(By.name("password"));
        WebElement loginBtn = driver.findElement(By.name("btnLogin"));

        // Clear previous input
        userField.clear();
        passField.clear();

        // Enter credentials
        userField.sendKeys(username);
        passField.sendKeys(password);
        loginBtn.click();

        // Debug info
        System.out.println("🔹 Attempting login with Username: " + username + ", Password: " + password);

        // Validate login by page title
        String expectedTitle = Utils.EXPECTED_TITLE;
        String actualTitle = driver.getTitle();

        try {
            Assert.assertEquals(actualTitle, expectedTitle,
                    "❌ Login failed for Username: " + username);
            System.out.println("✅ Login successful for Username: " + username);
        } catch (AssertionError e) {
            System.out.println("⚠️ Login test failed for Username: " + username);
            throw e; // rethrow so TestNG marks it as failed
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
