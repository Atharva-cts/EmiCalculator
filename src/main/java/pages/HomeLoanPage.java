package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomeLoanPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators of Home Loan Details
    private By calculatorsDropdown = By.id("menu-item-dropdown-2696");
    private By homeLoanLink        = By.xpath("//a[@title='Home Loan EMI Calculator']");
    private By homeValueField      = By.id("homeprice");
    private By marginField         =By.id("downpayment");
    private By loanInsurance       =By.xpath("//input[@id='homeloaninsuranceamount']");
    private By loanAmount         =By.xpath("//input[@id='homeloanamount']");
    private By interestRate       =By.xpath("//input[@id='homeloaninterest']");
    private By loanTenure       =By.xpath("//input[@id='homeloanterm']");
    private By loanFees      =By.xpath("//input[@id='loanfees']");
    private By startMonth     =By.xpath("//input[@id='startmonthyear']");
    private By monthSelector   =By.xpath("//span[normalize-space()='Jun']");

    //Locators of Homeowner Expenses
    private By oneTimeExpenses   =By.xpath("//input[@id='onetimeexpenses']");
    private By propertyTaxes    =By.xpath("//input[@id='propertytaxes']");
    private By home_Insurance     =By.xpath("//input[@id='homeinsurance']");
    private By maintenance_Expenses    =By.xpath("//input[@id='maintenanceexpenses']");



    public HomeLoanPage(WebDriver driver) {
        this.driver = driver;
        this.wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // --- Explicit Wait Click ---
    private void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    // --- Explicit Wait SendKeys ---
    private void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(Keys.chord(Keys.CONTROL, "a"));  // select all
        element.sendKeys(Keys.DELETE);                    // clear
        element.sendKeys(text);                           // type
    }

    // --- Page Actions ---
    public void openHomeLoanCalculator() {
        click(calculatorsDropdown);
        click(homeLoanLink);

        // Wait for page to load by waiting for home value field
        wait.until(ExpectedConditions.visibilityOfElementLocated(homeValueField));
    }

    public void enterHomeValue(String value) {
        type(homeValueField, value);
    }

    public void enterMargin(String value) {
        type(marginField, value);
    }

    public void enterLoanInsurance(String value) {
        type(loanInsurance, value);
    }

    public void enterLoanAmount(String value) {
        type(loanAmount, value);
    }
    public void enterInterestRate(String value) {
        type(interestRate, value);
    }
    public void enterLoanTenure(String value) {
        type(loanTenure, value);
    }

    public void enterLoanFees(String value) {
        type(loanFees, value);
    }

    public void enterMonth() throws InterruptedException {
        click(startMonth);
        Thread.sleep(1000);
        click(monthSelector);

    }

    public void enterOneTimeExpenses(String value) {
        type(oneTimeExpenses, value);
    }

    public void enterPropertTaxes(String value) {
        type(propertyTaxes, value);
    }

    public void enterHomeInsurance(String value) {
        type(home_Insurance, value);
    }

    public void enterMaintenanceExpenses(String value) {
        type(maintenance_Expenses, value);
    }
}