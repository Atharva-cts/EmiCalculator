package pages;

import base.BasePage;
import org.openqa.selenium.*;
import java.io.File;

public class CarLoanPage extends BasePage {
    private final By carLoanLink   = By.linkText("Car Loan");
    private final By loanAmount    = By.id("loanamount");
    private final By loanSpinner   = By.xpath("//input[@id='loanamount']/following-sibling::div/span");
    private final By interestSlider= By.id("loaninterestslider");
    private final By termSlider    = By.id("loantermslider");
    private final By emi           = By.xpath("//div[@id='emiamount']/p/span");
    private final By totalInterest = By.xpath("//div[@id='emitotalinterest']/p/span");
    private final By totalAmount   = By.xpath("//div[@id='emitotalamount']/p/span");

    public CarLoanPage(WebDriver driver) {
        super(driver);
    }

    public void clickCarLoan() {
        click(carLoanLink); // BasePage.click
    }

    public void enterLoanAmount(String amount) {
        type(loanAmount, amount); // BasePage.type -> clears + types + TAB
        click(loanSpinner);       // ensure blur/update (unchanged intent)
    }

    public void moveInterestSlider(int x) {
        dragHandleBy(interestSlider, x); // BasePage.dragHandleBy
    }

    public void moveTermSlider(int x) {
        dragHandleBy(termSlider, x); // BasePage.dragHandleBy
    }

    public void scrollDown() {
        scrollBy(500); // BasePage.scrollBy
    }

    public String getEMI()           { return getText(emi); }
    public String getTotalInterest() { return getText(totalInterest); }
    public String getTotalAmount()   { return getText(totalAmount); }

    public void sreenshotClick(){
        WebElement ele = waitVisible(By.xpath("//div[@class='row gutter-left gutter-right']"));
        File sourceFile  = ele.getScreenshotAs(OutputType.FILE);
        File targetFile  = new File(System.getProperty("user.dir") + "\\\\screenshots\\\\element.png");
        sourceFile.renameTo(targetFile);
    }
}
