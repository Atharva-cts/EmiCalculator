package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.HomeLoanPage;

public class HomeLoanEMITest {

    private WebDriver driver;
    private HomeLoanPage homePage;

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
        Thread.sleep(2000);

    }
    @Test(priority = 3, dependsOnMethods = "test_OpenCalculatorPage")
    public void homeowner_Expenses() throws InterruptedException {

        homePage.enterOneTimeExpenses("15");
        homePage.enterPropertTaxes("0.35");
        homePage.enterHomeInsurance("0.07");
        homePage.enterMaintenanceExpenses("2750");
        Thread.sleep(2000);
    }


}