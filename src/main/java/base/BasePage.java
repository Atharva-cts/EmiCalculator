package base;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait   = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
    }

    // -------- Waits --------
    protected WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    // Overloads with custom timeout (optional convenience)
    protected WebElement waitVisible(By locator, Duration timeout) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected WebElement waitClickable(By locator, Duration timeout) {
        return new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(locator));
    }

    // -------- Actions (optional to use) --------
    protected void click(By locator) {
        WebElement el = waitClickable(locator);
        scrollIntoView(el);
        el.click();
    }

    protected void jsClick(By locator) {
        WebElement el = waitClickable(locator);
        scrollIntoView(el);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    protected void type(By locator, String text) {
        WebElement el = waitVisible(locator);
        scrollIntoView(el);
        clearAndType(el, text);
        // Keep TAB only if your target page recalculates values on blur (safe on emicalculator)
        el.sendKeys(Keys.TAB);
    }

    protected void clearAndType(WebElement el, String text) {
        try { el.clear(); } catch (Exception ignored) { }
        // Windows/Linux
        el.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        el.sendKeys(Keys.DELETE);
        // macOS fallback (harmless elsewhere)
        if (!el.getAttribute("value").isEmpty()) {
            el.sendKeys(Keys.chord(Keys.COMMAND, "a"));
            el.sendKeys(Keys.DELETE);
        }
        el.sendKeys(text);
    }

    // -------- Scrolling --------
    protected void scrollIntoView(WebElement el) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
    }

    protected void scrollBy(int yPixels) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, arguments[0]);", yPixels);
    }

    // -------- Text helpers --------
    protected String getText(By locator) {
        return waitVisible(locator).getText().trim();
    }

    protected String waitForNonEmptyText(By locator, Duration timeout) {
        WebDriverWait w = new WebDriverWait(driver, timeout);
        return w.until(d -> {
            String t = d.findElement(locator).getText();
            return (t != null && !t.trim().isEmpty()) ? t.trim() : null;
        });
    }

    /** Wait until text under locator changes compared to previousText. */
    protected void waitForTextChange(By locator, String previousText, Duration timeout) {
        WebDriverWait w = new WebDriverWait(driver, timeout);
        w.until(d -> {
            String now = d.findElement(locator).getText();
            return now != null && !now.trim().equals(previousText);
        });
    }

    protected long parseMoney(String s) {
        String digits = s.replaceAll("[^0-9]", "");
        return digits.isEmpty() ? 0L : Long.parseLong(digits);
    }

    // -------- Slider helpers (optional) --------
    /** Drag a specific slider handle by X pixels. Pass the handle locator from your page. */
    protected void dragHandleBy(By handleLocator, int offsetXPx) {
        WebElement handle = waitClickable(handleLocator);
        actions.moveToElement(handle).clickAndHold().moveByOffset(offsetXPx, 0).release().perform();
    }

    /** Nudge with keyboard (if the slider supports it). */
    protected void nudge(By handleLocator, Keys key, int steps) {
        WebElement handle = waitClickable(handleLocator);
        handle.click();
        for (int i = 0; i < steps; i++) handle.sendKeys(key);
    }

    /** Read ARIA value (e.g., aria-valuenow) from a slider handle. */
    protected String readAriaNow(By handleLocator) {
        WebElement handle = waitVisible(handleLocator);
        return handle.getAttribute("aria-valuenow");
    }

    /** Read inline style "left: xx%;" from a slider handle. */
    protected Double readLeftPercent(By handleLocator) {
        WebElement handle = waitVisible(handleLocator);
        String style = handle.getAttribute("style");
        if (style == null) return null;
        Matcher m = Pattern.compile("left:\\s*([\\d.]+)%").matcher(style);
        return m.find() ? Double.parseDouble(m.group(1)) : null;
    }
}