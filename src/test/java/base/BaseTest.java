package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import utils.ConfigurationUtil;
import utils.WebDriverUtil;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected String baseUrl = "https://emicalculator.net/";

    @BeforeClass(alwaysRun = true)
    @Parameters({"headless", "baseUrl"})
    public void setUp(
            @Optional("false") String headlessParam,
            @Optional("") String baseUrlParam
    ) {

        boolean runHeadless = (headlessParam != null && !headlessParam.isEmpty())
                ? Boolean.parseBoolean(headlessParam)
                : ConfigurationUtil.getBoolean("headless", false);

        driver = WebDriverUtil.createChromeDriver(runHeadless);
        wait   = new WebDriverWait(driver, Duration.ofSeconds(15));


        String urlFromConfig = ConfigurationUtil.get("baseUrl", this.baseUrl);
        String urlToOpen = (baseUrlParam != null && !baseUrlParam.isEmpty()) ? baseUrlParam : urlFromConfig;

        driver.get(urlToOpen);

        dismissCookieIfPresent();
    }

    protected void dismissCookieIfPresent() {
        WebDriverUtil.dismissCookieIfPresent(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }


}
