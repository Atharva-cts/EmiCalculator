package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import pages.EmiCalculatorPage;
import pages.LoanAmountPage;

public class LoanAmountTest {

    private WebDriver driver;

    @Test
    public void openLoanCalculator_withWaits() throws InterruptedException {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://emicalculator.net/");

        LoanAmountPage page = new LoanAmountPage(driver);
        page.selectLoanCalculator();
        page.setEmiAmount();
        page.setLoanIntrest();
        page.setLoantermByyear();

        page.setLoanfees();


        // (Optional) Add a basic check, e.g., URL contains keywords
        // assert driver.getCurrentUrl().toLowerCase().contains("loan");

        //Slider


        page.scroll();
        page.display();
        Thread.sleep(2000);




    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}