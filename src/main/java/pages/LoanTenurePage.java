package pages;

import base.BasePage;
import org.openqa.selenium.*;

public class LoanTenurePage extends BasePage {
    private final By dropdownToggle     = By.id("menu-item-dropdown-2696");
    private final By loanCalculatorLink = By.linkText("Loan Calculator");
    private final By loanTenureLink     = By.xpath("//a[normalize-space()='Loan Tenure Calculator']");

    private final By loanAmount         = By.id("loanamount");
    private final By emiAmount          = By.id("loanemi");
    private final By loanInterest       = By.id("loaninterest");
    private final By loanFees           = By.id("loanfees");

    private final By tenureSpan         = By.xpath("//div[@id='loansummary-tenure']//p[1]");
    private final By aprValueSpan       = By.cssSelector("div[id='loansummary-apr'] p span");
    private final By totalIntrestSpan   = By.cssSelector("div[id='loansummary-totalinterest'] p span");
    private final By totalAmountSpan    = By.cssSelector("div[id='loansummary-totalamount'] p");

    private final By sliderHandle       = By.cssSelector(".ui-slider-handle");

    public LoanTenurePage(WebDriver driver) { super(driver); }

    public void openDropdown()        { click(dropdownToggle); }
    public void clickLoanCalculator() { click(loanCalculatorLink); }
    public void selectLoanCalculator(){ openDropdown(); clickLoanCalculator(); click(loanTenureLink); }

    public void setLoanAmount()   { type(loanAmount, "15000000"); }
    public void setEmiAmount()    { type(emiAmount, "15000"); }
    public void setLoanIntrest()  { type(loanInterest, "15.00"); }
    public void setLoanfees()     { type(loanFees, "0"); }

    // Slider helpers
    public boolean isHandleReady()    { WebElement h = waitVisible(sliderHandle); return h.isDisplayed() && h.isEnabled(); }
    public String  getAriaNow()       { return readAriaNow(sliderHandle); }
    public Double  getLeftPercent()   { return readLeftPercent(sliderHandle); }
    public void    dragHandleBy(int x){ super.dragHandleBy(sliderHandle, x); }
    public boolean nudgeRight(int s)  { String b=getAriaNow(); nudge(sliderHandle, Keys.ARROW_RIGHT, s); String a=getAriaNow(); return b!=null && a!=null && !a.equals(b); }

    public void scroll()  { scrollBy(550); }
    public void display(){
        waitVisible(tenureSpan);
        waitVisible(aprValueSpan);
        waitVisible(totalIntrestSpan);
        waitVisible(totalAmountSpan);

        System.out.println("Loan Tenure "    + getText(tenureSpan));
        System.out.println("Loan APR "       + getText(aprValueSpan));
        System.out.println("Total Interest " + getText(totalIntrestSpan));
        System.out.println("TotalAmount "    + getText(totalAmountSpan));
    }
}
