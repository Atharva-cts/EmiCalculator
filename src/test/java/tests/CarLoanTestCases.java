package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.CarLoanPage;

public class CarLoanTestCases {

    WebDriver driver;
    CarLoanPage page;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://emicalculator.net/"); // Change to your real URL

        page = new CarLoanPage(driver);
        System.out.println("Browser opened and site loaded...");
    }

    // =======================
    // Test Case 1: Verify page opens
    // =======================
    @Test(priority = 1)
    public void tc01_openCarLoanPage() {
        page.clickCarLoan();
        System.out.println("TC01: Car Loan page opened.");
    }

    // =======================
    // Test Case 2: Enter loan amount
    // =======================
    @Test(priority = 2)
    public void tc02_enterLoanAmount() {
        page.enterLoanAmount("1500000");
        System.out.println("TC02: Loan amount entered.");
    }

    // =======================
    // Test Case 3: Move interest slider
    // =======================
    @Test(priority = 3)
    public void tc03_moveInterestSlider() {
        page.moveInterestSlider(110);
        System.out.println("TC03: Interest slider moved.");
    }

    // =======================
    // Test Case 4: Move loan tenure slider
    // =======================
    @Test(priority = 4)
    public void tc04_moveLoanTenureSlider() {
        page.moveTermSlider(140);
        System.out.println("TC04: Loan tenure slider moved.");
    }

    // =======================
    // Test Case 5: Verify EMI & Total values
    // =======================
    @Test(priority = 5)
    public void tc05_verifyLoanCalculations() {
        page.scrollDown();

        System.out.println("TC05 EMI = " + page.getEMI());
        System.out.println("TC05 Total Interest = " + page.getTotalInterest());
        System.out.println("TC05 Total Amount = " + page.getTotalAmount());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("Browser closed after all tests.");
    }
}