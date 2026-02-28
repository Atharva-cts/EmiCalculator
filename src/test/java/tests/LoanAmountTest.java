package tests;
import base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import pages.LoanAmountPage;

public class LoanAmountTest extends  BaseTest{

    private LoanAmountPage page;

    @BeforeClass(alwaysRun = true)
    public void setUpSuite() {

        page = new LoanAmountPage(driver);
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

        // Optional: verify correct tab active if available in Page Object
        // Assert.assertTrue(page.isLoanCalculatorSelected(), "Loan Calculator tab was not selected");
    }

    /**
     * 2) Set all loan input fields for Loan Amount calculation
     */
    @Test(
            description = "Set EMI amount, interest, loan term (years), and fees",
            dependsOnMethods = "testSelectLoanCalculator"
    )
    public void testSetLoanInputs() {
        page.setEmiAmount();
        page.setLoanIntrest();
        page.setLoantermByyear();
        page.setLoanfees();

        // Optional validations if getters exist
        // Assert.assertEquals(page.getEmiAmount(), "25,000");
        // Assert.assertEquals(page.getInterestRate(), "10");
        // Assert.assertEquals(page.getLoanTermYears(), "5");
        // Assert.assertEquals(page.getProcessingFee(), "2");
    }

    /**
     * 3) Scroll action (e.g., to results/graphs section)
     */
    @Test(
            description = "Scroll the page to the results/graphs section",
            dependsOnMethods = "testSetLoanInputs"
    )
    public void testScroll() {
        page.scroll();

        // Optional: assert an element in the scrolled-into-view area is visible
        // Assert.assertTrue(page.isResultsSectionInView(), "Results section is not in view after scroll");
    }

    /**
     * 4) Display/print details (e.g., computed loan amount, schedule)
     */
    @Test(
            description = "Display/print the computed values for the Loan Amount flow",
            dependsOnMethods = "testScroll"
    )
    public void testDisplay() throws InterruptedException {
        page.display();
        Thread.sleep(1000); // Consider replacing with explicit waits within page.display()

        // Optional: validate computed outputs if you have getters on the page
        // String loanAmount = page.getComputedLoanAmount();
        // Assert.assertNotNull(loanAmount, "Computed loan amount should not be null");
        // Assert.assertTrue(loanAmount.matches("\\d[\\d,]*"), "Unexpected loan amount format: " + loanAmount);
    }
}