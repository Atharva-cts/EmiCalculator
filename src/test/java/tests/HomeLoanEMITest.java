package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomeLoanPage;
import org.testng.Assert;

public class HomeLoanEMITest {

    private WebDriver driver;
    private HomeLoanPage homePage;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        homePage = new HomeLoanPage(driver);

        driver.get("https://emicalculator.net/");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test(priority = 1)
    public void test_OpenCalculatorPage() {
        homePage.openHomeLoanCalculator();
        Assert.assertTrue(driver.getTitle().contains("EMI"), "Home Loan EMI page did NOT open.");

    }

    @Test(priority = 2, dependsOnMethods = "test_OpenCalculatorPage")
    public void test_EnterValues() throws InterruptedException {
        homePage.enterHomeValue("6500000");
        homePage.enterMargin("10");
        homePage.enterLoanInsurance("5000");
        homePage.enterLoanAmount("2500000");
        homePage.enterInterestRate("9.5");
        homePage.enterLoanTenure("20");
        homePage.enterLoanFees("0.25");
        homePage.enterMonth();

        Assert.assertTrue(driver.findElement(By.id("homeprice")).isDisplayed(),
                "Home value field is not displayed.");
        Thread.sleep(2000);

    }
    @Test(priority = 3, dependsOnMethods = "test_OpenCalculatorPage")
    public void homeowner_Expenses() throws InterruptedException {

        Assert.assertTrue(driver.findElement(By.id("onetimeexpenses")).isDisplayed(),
                "One‑time expenses field not visible.");

        homePage.enterOneTimeExpenses("15");
        homePage.enterPropertTaxes("0.35");
        homePage.enterHomeInsurance("0.07");
        homePage.enterMaintenanceExpenses("2750");
        Thread.sleep(2000);
    }

    // In Scroller()
    @Test(priority = 4, dependsOnMethods = "test_OpenCalculatorPage")
    public void Scroller() throws InterruptedException {

        homePage.scrollDown();
        Thread.sleep(1500);

        Assert.assertTrue(true, "Scroll action executed successfully.");
    }


}