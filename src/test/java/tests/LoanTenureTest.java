package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoanTenurePage;

public class LoanTenureTest {

    private WebDriver driver;
    private LoanTenurePage page;

    @BeforeClass(alwaysRun = true)
    public void setUpSuite() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://emicalculator.net/");
        page = new LoanTenurePage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDownSuite() {
        if (driver != null) driver.quit();
    }

    /**
     * 1) Select Loan Calculator tab/section
     */
    @Test(description = "Select the Loan Calculator section/tab")
    public void testSelectLoanCalculator() {
        page.selectLoanCalculator();

        // Optional: verify correct tab/section is active
        // Assert.assertTrue(page.isLoanCalculatorSelected(), "Loan Calculator tab was not selected");
    }

    /**
     * 2) Set all required inputs for the Loan Tenure calculation
     *    Inputs: Loan Amount, Interest, EMI Amount, Fees
     */
    @Test(
            description = "Set loan amount, interest, EMI amount, and processing fees",
            dependsOnMethods = "testSelectLoanCalculator"
    )
    public void testSetInputs() {
        page.setLoanAmount();
        page.setLoanIntrest();
        page.setEmiAmount();
        page.setLoanfees();

        // Optional validations if getters exist:
        // Assert.assertEquals(page.getLoanAmount(), "1,000,000");
        // Assert.assertEquals(page.getInterestRate(), "10");
        // Assert.assertEquals(page.getEmiAmountValue(), "25,000");
        // Assert.assertEquals(page.getProcessingFee(), "2");
    }

    /**
     * 3) Slider movement verification
     */
    @Test(
            description = "Verify the slider moves and state changes (ARIA or left%)",
            dependsOnMethods = "testSetInputs"
    )
    public void testSliderMovement() {
        String ariaBefore = page.getAriaNow();
        Double leftBefore = page.getLeftPercent();

        // Move the slider handle (e.g., by 40px to the right)
        page.dragHandleBy(40);

        String ariaAfter = page.getAriaNow();
        Double leftAfter = page.getLeftPercent();

        boolean changed;
        if (ariaBefore != null && ariaAfter != null) {
            changed = !ariaAfter.equals(ariaBefore);
        } else if (leftBefore != null && leftAfter != null) {
            changed = !leftAfter.equals(leftBefore);
        } else {
            changed = false; // nothing to compare—likely wrong locator or slider not initialized
        }

        Assert.assertTrue(
                changed,
                String.format(
                        "Slider did not move. aria: %s -> %s, left%%: %s -> %s",
                        ariaBefore, ariaAfter, leftBefore, leftAfter
                )
        );
    }

    /**
     * 4) Scroll AND Display (combined)
     */
    @Test(
            description = "Scroll to results/graphs and display computed Loan Tenure details",
            dependsOnMethods = "testSliderMovement"
    )
    public void testScrollAndDisplay() throws InterruptedException {
        page.scroll();
        page.display();

        // Keep sleep minimal; ideally use explicit waits inside Page Object
        Thread.sleep(1000);

        // Optional: validate computed outputs if you have getters
        // String tenureValue = page.getComputedTenure();
        // Assert.assertNotNull(tenureValue, "Computed tenure should not be null");
        // Assert.assertTrue(tenureValue.matches("\\d+\\s*(months|years)"), "Unexpected tenure format: " + tenureValue);
    }
}