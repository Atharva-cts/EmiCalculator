package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.EmiCalculatorPage;

public class EmiCalculatorTest {

    private WebDriver driver;

    @Test
    public void openLoanCalculator_withWaits() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://emicalculator.net/");

        EmiCalculatorPage page = new EmiCalculatorPage(driver);
        page.selectLoanCalculator();
        page.setLoanAmount();
        page.setLoanIntrest();
        page.setLoantermByyear();
        page.setLoanTermByMonth();
        page.setLoanfees();


        // (Optional) Add a basic check, e.g., URL contains keywords
        // assert driver.getCurrentUrl().toLowerCase().contains("loan");

        //Slider
        String ariaBefore = page.getAriaNow();
        Double leftBefore = page.getLeftPercent();

        // Try moving the slider (drag by 40px to the right)
        page.dragHandleBy(40);

        // Capture after values
        String ariaAfter = page.getAriaNow();
        Double leftAfter = page.getLeftPercent();

        // Assertion strategy:
        // 1) If ARIA present, assert ARIA changed
        // 2) Else, assert left% changed
        boolean changed;
        if (ariaBefore != null && ariaAfter != null) {
            changed = !ariaAfter.equals(ariaBefore);
        } else if (leftBefore != null && leftAfter != null) {
            changed = !leftAfter.equals(leftBefore);
        } else {
            changed = false; // nothing to compare—likely wrong locator or slider not initialized
        }

        Assert.assertTrue(changed, String.format(
                "Slider did not move. aria: %s -> %s, left%%: %s -> %s",
                ariaBefore, ariaAfter, leftBefore, leftAfter
        ));
        page.scroll();
        page.display();
        Thread.sleep(2000);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}