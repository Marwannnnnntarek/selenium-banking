package banking.tests;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver;

    @BeforeClass
    // Setup method to initialize WebDriver
    public void setup() {
        // Initialize FirefoxDriver
        System.setProperty("webdriver.gecko.driver", Utils.FIREFOX_PATH);
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void verifyLogin() {
        // Step 1: Go to URL
        driver.get(Utils.BASE_URL);

        // Step 2: Enter valid UserId
        driver.findElement(By.name("uid")).sendKeys(Utils.USER_NAME);

        // Step 3: Enter valid Password
        driver.findElement(By.name("password")).sendKeys(Utils.PASSWORD);

        // Step 4: Click Login
        driver.findElement(By.name("btnLogin")).click();

        // Verification: Check if we landed on Manager home page
        String expectedTitle = Utils.EXPECTED_TITLE;
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle, "❌ Login Failed!");
        System.out.println("✅ Login successful. Title matched: " + actualTitle);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
