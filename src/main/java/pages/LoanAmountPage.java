package pages;

import base.BasePage;
import org.openqa.selenium.*;

public class LoanAmountPage extends BasePage {
    private final By dropdownToggle     = By.id("menu-item-dropdown-2696");
    private final By loanCalculatorLink = By.linkText("Loan Calculator");
    private final By loanAmountLink     = By.xpath("//a[normalize-space()='Loan Amount Calculator']");
    private final By emiAmount          = By.id("loanemi");
    private final By loanInterest       = By.id("loaninterest");
    private final By loanTermYears      = By.id("loanterm");
    private final By loanFees           = By.id("loanfees");

    private final By loanAmountSpan     = By.cssSelector("div[id='loansummary-loanamount'] p span");
    private final By aprValueSpan       = By.cssSelector("div[id='loansummary-apr'] p span");
    private final By totalIntrestSpan   = By.cssSelector("div[id='loansummary-totalinterest'] p span");
    private final By totalAmountSpan    = By.cssSelector("div[id='loansummary-totalamount'] p");

    public LoanAmountPage(WebDriver driver) { super(driver); }

    public void openDropdown()        { click(dropdownToggle); }
    public void clickLoanCalculator() { click(loanCalculatorLink); }

    public void selectLoanCalculator() {
        openDropdown();
        clickLoanCalculator();
        click(loanAmountLink);
    }

    public void setEmiAmount()      { type(emiAmount, "15000"); }
    public void setLoanIntrest()    { type(loanInterest, "15.00"); }
    public void setLoantermByyear() { type(loanTermYears, "1"); }
    public void setLoanfees()       { type(loanFees, "0"); }

    public void display() {
        waitVisible(loanAmountSpan);
        waitVisible(aprValueSpan);
        waitVisible(totalIntrestSpan);
        waitVisible(totalAmountSpan);

        System.out.println("Principle Loan Amount " + getText(loanAmountSpan));
        System.out.println("Loan APR "              + getText(aprValueSpan));
        System.out.println("Total Interest "        + getText(totalIntrestSpan));
        System.out.println("TotalAmount "           + getText(totalAmountSpan));
    }

    public void scroll() { scrollBy(550); }
}
