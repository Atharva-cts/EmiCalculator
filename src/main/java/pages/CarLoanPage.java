package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

public class CarLoanPage {

    WebDriver driver;
    Actions actions;

    By carLoanLink = By.linkText("Car Loan");
    By loanAmount = By.id("loanamount");
    By loanSpinner = By.xpath("//input[@id='loanamount']/following-sibling::div/span");
    By interestSlider = By.id("loaninterestslider");
    By termSlider = By.id("loantermslider");

    By emi = By.xpath("//div[@id='emiamount']/p/span");
    By totalInterest = By.xpath("//div[@id='emitotalinterest']/p/span");
    By totalAmount = By.xpath("//div[@id='emitotalamount']/p/span");

    public CarLoanPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new Actions(driver);
    }

    public void clickCarLoan() {
        driver.findElement(carLoanLink).click();
    }

    public void enterLoanAmount(String amount) {
        WebElement amt = driver.findElement(loanAmount);
        amt.clear();
        amt.sendKeys(amount);
        driver.findElement(loanSpinner).click();
    }

    public void moveInterestSlider(int x) {
        WebElement slider = driver.findElement(interestSlider);
        actions.clickAndHold(slider).moveByOffset(x, 0).release().perform();
    }

    public void moveTermSlider(int x) {
        WebElement slider = driver.findElement(termSlider);
        actions.clickAndHold(slider).moveByOffset(x, 0).release().perform();
    }


    public void scrollDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 300);");
    }

    public String getEMI() {
        return driver.findElement(emi).getText();
    }

    public String getTotalInterest() {
        return driver.findElement(totalInterest).getText();
    }

    public String getTotalAmount() {
        return driver.findElement(totalAmount).getText();
    }
}