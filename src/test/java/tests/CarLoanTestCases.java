package tests;
import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.CarLoanPage;

import java.io.File;


public class CarLoanTestCases extends BaseTest {

    CarLoanPage page;

    @BeforeClass
    public void setUp() {

        page = new CarLoanPage(driver);
        System.out.println("Browser opened and site loaded...");
    }

    // =======================
    // Test Case 1: Verify page opens & enter loan amount
    // =======================
    @Test(priority = 1)
    public void tc01_openCarLoanPage() throws InterruptedException {
        page.clickCarLoan();
        System.out.println("TC01: Car Loan page opened.");
        Thread.sleep(2000);
        page.enterLoanAmount("1500000");
        System.out.println("TC01: Loan amount entered.");

        String emi = page.getEMI();
        Assert.assertFalse(emi.trim().isEmpty(), "EMI should not be empty after entering loan amount");


    }

    // =======================
    // Test Case 2: Move interest slider & Loan tenure
    // =======================
    @Test(priority = 2)
    public void tc02_moveInterestSlider() throws InterruptedException {

        // capture EMI before moving sliders
        String emiBefore = page.getEMI();

        page.moveInterestSlider(110);
        System.out.println("TC02: Interest slider moved.");
        page.moveTermSlider(200);
        System.out.println("TC02: Loan tenure slider moved.");
        Thread.sleep(2000);


        String emiAfter = page.getEMI();
        // Try to assert change; if environment-dependent, this assertion is soft:
        if (emiBefore != null && !emiBefore.trim().isEmpty()) {
            Assert.assertNotEquals(emiAfter, emiBefore,
                    "EMI should change after moving interest/tenure sliders (adjust offsets if this fails).");
        }

    }

    // =======================
    // Test Case 3: Verify Scroll, EMI & Total values print
    // =======================
    @Test(priority = 3)
    public void tc03_verifyLoanCalculations() throws InterruptedException {
        page.scrollDown();
        Thread.sleep(2000);
        System.out.println("TC03: EMI = " + page.getEMI());
        System.out.println("TC03: Total Interest = " + page.getTotalInterest());
        System.out.println("TC03: Total Amount = " + page.getTotalAmount());

        // 🔹 Assertions
        Assert.assertTrue(page.getEMI() != null && !page.getEMI().trim().isEmpty(), "EMI should not be empty");
        Assert.assertTrue(page.getTotalInterest() != null && !page.getTotalInterest().trim().isEmpty(), "Total Interest should not be empty");
        Assert.assertTrue(page.getTotalAmount() != null && !page.getTotalAmount().trim().isEmpty(), "Total Amount should not be empty");


    }


    // =======================
    // Test Case 4: Take Screenshot
    // =======================
    @Test(priority = 4)
    public void tc04_screenShotClick() throws InterruptedException {
        page.sreenshotClick();
        System.out.println("TC04: Screenshot click");

        // Simple assertion: Verify screenshot file was created
        File screenshot = new File(System.getProperty("user.dir") + "\\screenshots\\element.png");
        Assert.assertTrue(screenshot.exists(), "Screenshot file should be created.");
        Thread.sleep(2000);

    }

    @AfterClass
    public void tearDown() {
        driver.quit();
        System.out.println("Browser closed after all tests.");
    }
}

