package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WebDriverUtil {


    public static WebDriver createChromeDriver(Boolean headlessNullable) {
        boolean headless = (headlessNullable != null)
                ? headlessNullable
                : ConfigurationUtil.getBoolean("headless", false);

        ChromeOptions options = new ChromeOptions();
        if (headless) {
            options.addArguments("--headless=new");
            options.addArguments("--window-size=1920,1080");
        } else {
            options.addArguments("--start-maximized");
        }
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-infobars");
        options.addArguments("--remote-allow-origins=*");

        return new ChromeDriver(options);
    }

    public static void openBaseUrl(WebDriver driver) {
        String url = ConfigurationUtil.get("baseUrl", "https://emicalculator.net/");
        driver.get(url);
    }

    /** Dismiss the cookie banner commonly shown on emicalculator.net. Safe to call always. */
    public static void dismissCookieIfPresent(WebDriver driver) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            By cookieBtn = By.id("ez-accept-all");
            wait.until(ExpectedConditions.elementToBeClickable(cookieBtn)).click();
        } catch (Exception ignored) {
        }
    }
}