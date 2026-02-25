package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoanCalculatorPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators (adjust if your site differs)
    private final By dropdownToggle = By.id("menu-item-dropdown-2696");
    private final By loanCalculatorLink = By.linkText("Loan Calculator");
    private final By loanAmount=By.id("loanamount");
    private final By loanIntrest=By.id("loanintrest");
    private final By loanTerm=By.id("loanterm");
    private final By loanMonth=By.xpath("//label[normalize-space()='Mo']");
    private final By loanFees=By.id("loanfees");

    private final By emiAmountSpan     = By.cssSelector("div[id='loansummary-emi'] p span");
    private final By totalInterestSpan = By.cssSelector("div[id='loansummary-apr'] p span");
    private final By totalAmountSpan   = By.cssSelector("div[id='loansummary-totalamount'] p");

    public LoanCalculatorPage(WebDriver driver) {
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
    }
    public void setLoanAmount(){

        WebElement loanAmount = wait.until(ExpectedConditions.elementToBeClickable(By.id("loanamount")));

// Clear and type the value

        loanAmount.click();
        loanAmount.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        loanAmount.sendKeys("15000000");

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
    public void setLoanTermByMonth(){
        WebElement loanterm =wait.until(
                ExpectedConditions.elementToBeClickable(loanMonth));
        loanterm.click();


        loanterm.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        loanterm.sendKeys("12");
    }

    public void setLoanfees(){
        WebElement loanfees=wait.until(
                ExpectedConditions.elementToBeClickable(loanFees));
        loanfees.click();

        loanfees.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        loanfees.sendKeys("0");
    }

    //sliderTest

    private final By sliderHandle = By.cssSelector(".ui-slider-handle");



    /** Basic readiness: visible + enabled + can receive focus (tabindex or click). */
    public boolean isHandleReady() {
        WebElement handle = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderHandle));
        return handle.isDisplayed() && handle.isEnabled();
    }

    /** Read ARIA value if present; returns null if not present. */
    public String getAriaNow() {
        WebElement handle = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderHandle));
        return handle.getAttribute("aria-valuenow"); // may be null if not set
    }

    /** Read inline left% from style (e.g., "left: 26%;"). Returns numeric percent or null. */
    public Double getLeftPercent() {
        WebElement handle = wait.until(ExpectedConditions.visibilityOfElementLocated(sliderHandle));
        String style = handle.getAttribute("style"); // e.g., "left: 26%;"
        if (style == null) return null;
        // Extract the number
        java.util.regex.Matcher m = java.util.regex.Pattern.compile("left:\\s*([\\d.]+)%").matcher(style);
        if (m.find()) {
            return Double.parseDouble(m.group(1));
        }
        return null;
    }

    /** Move the handle using drag-and-drop by X offset (pixels). */
    public void dragHandleBy(int offsetXPx) {
        WebElement handle = wait.until(ExpectedConditions.elementToBeClickable(sliderHandle));
        new Actions(driver)
                .moveToElement(handle)
                .clickAndHold()
                .moveByOffset(offsetXPx, 0)
                .release()
                .perform();
    }

    /** Nudge with keyboard (if the slider supports it). */
    public boolean nudgeRight(int steps) {
        WebElement handle = wait.until(ExpectedConditions.elementToBeClickable(sliderHandle));
        handle.click();
        String before = handle.getAttribute("aria-valuenow");
        for (int i = 0; i < steps; i++) {
            handle.sendKeys(Keys.ARROW_RIGHT);
        }
        String after = handle.getAttribute("aria-valuenow");
        // If ARIA is missing, we can't compare here—call getLeftPercent() in the test as fallback.
        return before != null && after != null && !after.equals(before);
    }

    public void scroll1(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500);");   // scroll down 500px
    }

    public void display(){

        wait.until(ExpectedConditions.visibilityOfElementLocated(emiAmountSpan));
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalInterestSpan));
        wait.until(ExpectedConditions.visibilityOfElementLocated(totalAmountSpan));

// Read text
        String emiAmountText     = driver.findElement(emiAmountSpan).getText();        // e.g., "8,207"
        String totalInterestText = driver.findElement(totalInterestSpan).getText();    // e.g., "92,397"
        String totalAmountText   = driver.findElement(totalAmountSpan).getText();
        // e.g., "4,92,397"
        System.out.println("Loan EMI "+emiAmountText);
        System.out.println("Total Interest "+totalInterestText);
        System.out.println("TotalAmount "+totalAmountText);

    }
    public void scroll2(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 550);");   // scroll down 500px
    }
}