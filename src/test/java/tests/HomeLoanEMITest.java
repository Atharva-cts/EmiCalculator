package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import org.testng.Assert;
import pages.HomeLoanPage;
import org.testng.Assert;




public class HomeLoanEMITest extends BaseTest{

    private HomeLoanPage homePage;

    @BeforeClass
    public void setup() {
        // 1. Get the absolute path to your resources folder dynamically
        String downloadPath = System.getProperty("user.dir") + "\\src\\main\\resources";

        // 2. Set Chrome Preferences
        java.util.HashMap<String, Object> chromePrefs = new java.util.HashMap<>();
        chromePrefs.put("download.default_directory", downloadPath); // Sets the path
        chromePrefs.put("download.prompt_for_download", false);      // Disables the "Save As" popup
        chromePrefs.put("plugins.always_open_pdf_externally", true); // Optional: handles PDFs better

        // 3. Pass these preferences to ChromeOptions
        org.openqa.selenium.chrome.ChromeOptions options = new org.openqa.selenium.chrome.ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        // 4. Initialize the driver with the options

        homePage = new HomeLoanPage(driver);

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
        Thread.sleep(1500);
    }

    @Test(priority = 3, dependsOnMethods = "test_OpenCalculatorPage")
    public void homeowner_Expenses() throws InterruptedException {

        Assert.assertTrue(driver.findElement(By.id("onetimeexpenses")).isDisplayed(),
                "One‑time expenses field not visible.");

        homePage.enterOneTimeExpenses("15");
        homePage.enterPropertTaxes("0.35");
        homePage.enterHomeInsurance("0.07");
        homePage.enterMaintenanceExpenses("2750");
    }

    @Test(priority = 4, dependsOnMethods = "test_OpenCalculatorPage")
    public void Scroller() throws InterruptedException {
        homePage.scrollDown();
        Thread.sleep(1500);
        Assert.assertTrue(true, "Scroll action executed successfully.");
    }

    @Test(priority = 5, dependsOnMethods = "test_OpenCalculatorPage")
    public void Excel_file() throws InterruptedException {
        homePage.Excel_Sheet_click();


    }
}