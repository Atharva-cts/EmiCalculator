package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BasePage;
import java.time.Duration;

public class LoanAmountPage extends BasePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators (adjust if your site differs)
    private final By dropdownToggle = By.id("menu-item-dropdown-2696");
    private final By loanCalculatorLink = By.linkText("Loan Calculator");
    private final By loanAmountLink=By.xpath("//a[normalize-space()='Loan Amount Calculator']");
    private final By emiAmount=By.xpath("//input[@id='loanemi']");
    private final By loanIntrest=By.xpath("//input[@id='loaninterest']");
    private final By loanTerm=By.id("loanterm");
    private final By loanFees=By.id("loanfees");

    private final By loanAmountSpan     = By.cssSelector("div[id='loansummary-loanamount'] p span");
    private final By  aprValueSpan = By.cssSelector("div[id='loansummary-apr'] p span");
    private final By  totalIntrestSpan = By.cssSelector("div[id='loansummary-totalinterest'] p span");

    private final By totalAmountSpan   = By.cssSelector("div[id='loansummary-totalamount'] p");


    public LoanAmountPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // simple explicit wait
    }

    public void openDropdown() {
        wait.until(ExpectedConditions.elementToBeClickable(dropdownToggle)).click();
    }

    public void clickLoanCalculator() {
        wait.until(ExpectedConditions.elementToBeClickable(loanCalculatorLink)).click();
    }
    public void selectLoanCalculator() {
        openDropdown();
        clickLoanCalculator();
        wait.until(ExpectedConditions.elementToBeClickable(loanAmountLink)).click();
    }
    public void setEmiAmount(){

        WebElement loanAmount = wait.until(ExpectedConditions.elementToBeClickable(emiAmount));

// Clear and type the value

        loanAmount.click();
        loanAmount.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        loanAmount.sendKeys("15000");

    }
    public void setLoanIntrest(){
        //driver.findElement(loanAmount).sendKeys("1500000");

        WebElement loanInterest = wait.until(
                ExpectedConditions.elementToBeClickable(By.id("loaninterest"))
        );

// Clear and type 10.75 (or any target like 15.50)
        loanInterest.click();
        loanInterest.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        loanInterest.sendKeys("15.00"); // example: set to 15.00

// If the page validates on blur/tab:
        loanInterest.sendKeys(Keys.TAB);


    }
    public void setLoantermByyear(){
        WebElement loanterm=wait.until(
                ExpectedConditions.elementToBeClickable(loanTerm));
        loanterm.click();

        loanterm.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        loanterm.sendKeys("1");

    }

    public void setLoanfees(){
        WebElement loanfees=wait.until(
                ExpectedConditions.elementToBeClickable(loanFees));
        loanfees.click();

        loanfees.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        loanfees.sendKeys("0");
    }



    public void display(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(loanAmountSpan));
        wait.until(ExpectedConditions.visibilityOfElementLocated(aprValueSpan));
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalIntrestSpan));
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmountSpan));

// Read text
        String emiAmountText     = driver.findElement(loanAmountSpan).getText();        // e.g., "8,207"
        String aprText = driver.findElement(aprValueSpan).getText();    // e.g., "92,397"
        String totalInterestText = driver.findElement(totalIntrestSpan).getText();    // e.g., "92,397"
        String totalAmountText   = driver.findElement(totalAmountSpan).getText();
        // e.g., "4,92,397"
        System.out.println("Principle Loan Amount "+emiAmountText);
        System.out.println("Loan APR "+ aprText);
        System.out.println("Total Interest "+totalInterestText);
        System.out.println("TotalAmount "+totalAmountText);

    }
    public void scroll(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 550);");   // scroll down 500px
    }


}
