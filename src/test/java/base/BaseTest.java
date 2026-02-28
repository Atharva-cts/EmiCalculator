package base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;
    protected String baseUrl = "https://emicalculator.net/";

    @BeforeClass(alwaysRun = true)
    @Parameters({"headless"})
    public void setUp(@Optional("false") String headless) {
        // If you want WebDriverManager, add it to pom and uncomment:
        // io.github.bonigarcia.wdm.WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(headless)) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        driver.get(baseUrl);
        dismissCookieIfPresent();
    }

    protected void dismissCookieIfPresent() {
        try {
            By cookieBtn = By.id("ez-accept-all");
            new WebDriverWait(driver, Duration.ofSeconds(5))
                    .until(ExpectedConditions.elementToBeClickable(cookieBtn))
                    .click();
        } catch (Exception ignored) {
            // No cookie banner or already dismissed
        }
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    /** Optional helper: create page objects with `page(MyPage.class)` */
    protected <T extends BasePage> T page(Class<T> clazz) {
        try {
            return clazz.getDeclaredConstructor(org.openqa.selenium.WebDriver.class).newInstance(driver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create page object: " + clazz.getSimpleName(), e);
        }
    }
}