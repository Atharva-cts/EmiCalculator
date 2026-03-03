package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.EmiCalculatorPage;

public class EmiCalculatorTest extends BaseTest {

    private EmiCalculatorPage page;

    @BeforeClass(alwaysRun = true)
    public void setUpSuite() {

        page = new EmiCalculatorPage(driver);
    }

    @AfterClass(alwaysRun = true)
    public void tearDownSuite() {
    }

    /**
     * 1) Select Loan Calculator tab/section
     */
    @Test(description = "Select the Loan Calculator section/tab")
    public void testSelectLoanCalculator() {
        page.selectLoanCalculator();

        // Optional quick sanity check (if your page exposes a state/locator)
        // Assert.assertTrue(page.isLoanCalculatorSelected(), "Loan Calculator tab was not selected");
    }

    /**
     * 2) Set all loan input fields
     */
    @Test(
            description = "Set loan amount, interest, term (years & months), and fees",
            dependsOnMethods = "testSelectLoanCalculator"
    )
    public void testSetLoanInputs() {
        page.setLoanAmount();
        page.setLoanIntrest();
        page.setLoantermByyear();
        page.setLoanTermByMonth();
        page.setLoanfees();

        // Optional validations (if getters or readable values exist)
        // Assert.assertEquals(page.getLoanAmount(), "1,000,000");
        // Assert.assertEquals(page.getInterestRate(), "10");
        // Assert.assertEquals(page.getLoanTermYears(), "5");
        // Assert.assertEquals(page.getLoanTermMonths(), "6");
        // Assert.assertEquals(page.getProcessingFee(), "2");
    }

    /**
     * 3) Slider movement verification
     */
    @Test(
            description = "Verify slider can be moved and state changes",
            dependsOnMethods = "testSetLoanInputs"
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
            changed = false; // nothing to compare
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
            description = "Scroll to results/graphs and display computed EMI details",
            dependsOnMethods = "testSliderMovement"
    )
    public void testScrollAndDisplay() throws InterruptedException {
        page.scroll();
        page.display();

        // Keep sleep minimal; ideally use explicit waits within page.display()
        Thread.sleep(1000);

        // Optional: verify key computed values are non-empty or match expected pattern
        // Assert.assertTrue(page.getEmiValue().matches("\\d[\\d,]*\\.\\d{2}"), "EMI value format unexpected");
    }
}