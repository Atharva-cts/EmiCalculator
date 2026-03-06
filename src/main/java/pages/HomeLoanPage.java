package pages;

import base.BasePage;
import org.openqa.selenium.*;

public class HomeLoanPage extends BasePage {

    private final By calculatorsDropdown = By.id("menu-item-dropdown-2696");
    private final By homeLoanLink        = By.xpath("//a[@title='Home Loan EMI Calculator']");
    private final By homeValueField      = By.id("homeprice");
    private final By marginField         = By.id("downpayment");
    private final By loanInsurance       = By.id("homeloaninsuranceamount");
    private final By loanAmount          = By.id("homeloanamount");
    private final By interestRate        = By.id("homeloaninterest");
    private final By loanTenure          = By.id("homeloanterm");
    private final By loanFees            = By.id("loanfees");
    private final By startMonth          = By.id("startmonthyear");
    private final By monthSelector       = By.xpath("//span[normalize-space()='Jun']");

    // Homeowner Expenses
    private final By oneTimeExpenses     = By.id("onetimeexpenses");
    private final By propertyTaxes       = By.id("propertytaxes");
    private final By home_Insurance      = By.id("homeinsurance");
    private final By maintenance_Expenses= By.id("maintenanceexpenses");
    private final By Excel_file          = By.xpath("//a[@title='Download Excel Spreadsheet']");

    public HomeLoanPage(WebDriver driver) {
        super(driver);
    }

    public void openHomeLoanCalculator() {
        click(calculatorsDropdown);
        click(homeLoanLink);
        waitVisible(homeValueField);
    }
    public void Excel_Sheet_click() { click(Excel_file); }

    public void enterHomeValue(String v)         { type(homeValueField, v); }
    public void enterMargin(String v)            { type(marginField, v); }
    public void enterLoanInsurance(String v)     { type(loanInsurance, v); }
    public void enterLoanAmount(String v)        { type(loanAmount, v); }
    public void enterInterestRate(String v)      { type(interestRate, v); }
    public void enterLoanTenure(String v)        { type(loanTenure, v); }
    public void enterLoanFees(String v)          { type(loanFees, v); }

    public void enterMonth() throws InterruptedException {
        click(startMonth);
        Thread.sleep(1000);       // keeping your timing; could be replaced by an explicit wait on monthSelector
        click(monthSelector);
    }

    public void enterOneTimeExpenses(String v)   { type(oneTimeExpenses, v); }
    public void enterPropertTaxes(String v)      { type(propertyTaxes, v); }
    public void enterHomeInsurance(String v)     { type(home_Insurance, v); }
    public void enterMaintenanceExpenses(String v){ type(maintenance_Expenses, v); }

    public void scrollDown() { scrollBy(2000); }
}
