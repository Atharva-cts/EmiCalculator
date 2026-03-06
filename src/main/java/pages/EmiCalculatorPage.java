package pages;

import base.BasePage;
import org.openqa.selenium.*;

public class EmiCalculatorPage extends BasePage {
    private final By dropdownToggle     = By.id("menu-item-dropdown-2696");
    private final By loanCalculatorLink = By.linkText("Loan Calculator");
    private final By loanAmount         = By.id("loanamount");
    private final By loanInterest       = By.id("loaninterest");
    private final By loanTermYears      = By.id("loanterm");
    private final By loanMonthTab       = By.xpath("//label[normalize-space()='Mo']");
    private final By loanFees           = By.id("loanfees");

    private final By emiAmountSpan      = By.cssSelector("div[id='loansummary-emi'] p span");
    private final By aprSpan            = By.cssSelector("div[id='loansummary-apr'] p span");
    private final By totalIntrestSpan   = By.cssSelector("div[id='loansummary-totalinterest'] p span");
    private final By totalAmountSpan    = By.cssSelector("div[id='loansummary-totalamount'] p");

    private final By sliderHandle       = By.cssSelector(".ui-slider-handle");

    public EmiCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public void openDropdown()        { click(dropdownToggle); }
    public void clickLoanCalculator() { click(loanCalculatorLink); }
    public void selectLoanCalculator(){ openDropdown(); clickLoanCalculator(); }

    public void setLoanAmount()   { type(loanAmount, "15000000"); }
    public void setLoanIntrest()  { type(loanInterest, "15.00"); }
    public void setLoantermByyear(){ type(loanTermYears, "1"); }

    public void setLoanTermByMonth() {
        click(loanMonthTab); // just to toggle focus into months; adjust if months input differs
        // If there is a month textbox, use type(<locator>, "12");
        // Keeping behavior equivalent to your original (focus + keys).
    }

    public void setLoanfees() { type(loanFees, "0"); }

    // Slider helpers via BasePage
    public boolean isHandleReady() {
        WebElement handle = waitVisible(sliderHandle);
        return handle.isDisplayed() && handle.isEnabled();
    }
    public String getAriaNow()   { return readAriaNow(sliderHandle); }
    public Double getLeftPercent(){ return readLeftPercent(sliderHandle); }
    public void dragHandleBy(int offsetXPx) { super.dragHandleBy(sliderHandle, offsetXPx); }

    public boolean nudgeRight(int steps) {
        // Use BasePage.nudge to press RIGHT keys
        String before = getAriaNow();
        nudge(sliderHandle, Keys.ARROW_RIGHT, steps);
        String after  = getAriaNow();
        return (before != null && after != null && !after.equals(before));
    }

    public void scroll()  { scrollBy(550); }
    public void display() {
        waitVisible(emiAmountSpan);
        waitVisible(aprSpan);
        waitVisible(totalIntrestSpan);
        waitVisible(totalAmountSpan);

        String emiText     = getText(emiAmountSpan);
        String aprText     = getText(aprSpan);
        String totIntText  = getText(totalIntrestSpan);
        String totAmtText  = getText(totalAmountSpan);

        System.out.println("Loan EMI " + emiText);
        System.out.println("Loan APR " + aprText);
        System.out.println("Total Interest " + totIntText);
        System.out.println("TotalAmount " + totAmtText);
    }
}
