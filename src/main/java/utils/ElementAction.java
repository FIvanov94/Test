package utils;

import driver.DriverManager;
import exceptions.ApplicationException;
import exceptions.AutomationImplementationException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class ElementAction {

    private static final Logger logger = LogManager.getLogger(ElementAction.class);

    private Actions action = new Actions(DriverManager.getDriver());

    public ElementAction(WebDriver driver) {
    }


    private void waitForElementToBeDisplayed(By locator) {
        try {
            new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30))
                    .ignoring(StaleElementReferenceException.class)
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(DriverManager.getDriver().findElement(locator)));
        } catch (TimeoutException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }
    private void waitForElementToBeDisplayed(WebElement element) {
        try {
            new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30))
                    .ignoring(StaleElementReferenceException.class)
                    .pollingEvery(Duration.ofMillis(200))
                    .until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException | NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void waitExplicitlyInMilliseconds(long timeoutMilliseconds) {
        try {
            Thread.sleep(timeoutMilliseconds);
        } catch (InterruptedException e) {
            throw new ApplicationException("An error occurred while waiting explicitly for "+ timeoutMilliseconds +
                    " milliseconds.");
        }
    }

    public void waitForElementToBeClickable(By locator) {
        waitForElementToBeDisplayed(locator);
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(200))
                .until(ExpectedConditions.elementToBeClickable(locator));
    }
    public void waitForElementToBeClickable(WebElement element) {
        waitForElementToBeDisplayed(element);
        new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(200))
                .until(ExpectedConditions.elementToBeClickable(element));
    }

    public void clickElement(By locator) {
        verifyNotNull(locator);
        waitForElementToBeClickable(locator);
        WebElement elementToBeClicked = DriverManager.getDriver().findElement(locator);
        elementToBeClicked.click();
    }
    public void clickElement(WebElement element) {
        verifyNotNull(element);
        waitForElementToBeClickable(element);
        element.click();
    }

    public boolean isDisplayed(By locator) {
            waitForElementToBeDisplayed(locator);
            return DriverManager.getDriver().findElement(locator).isDisplayed();
    }

    public boolean isDisplayed(WebElement element) {
        waitForElementToBeDisplayed(element);
        return element.isDisplayed();
    }

    public boolean isDisplayedInTimeFrame(By locator, int timeFrameInSeconds) {
        if (timeFrameInSeconds < 1) {
            throw new AutomationImplementationException("Please specify a valid timeframe in seconds");
        }
        boolean isDisplayed = false;
        long startTime = System.currentTimeMillis();
        try {
            while ((System.currentTimeMillis() - startTime) < timeFrameInSeconds*1000) {
                try {
                    if (DriverManager.getDriver().findElement(locator).isDisplayed()) {
                        isDisplayed = true;
                        break;
                    }
                } catch (WebDriverException wde) {
                    logger.debug("Element was not displayed");
                }
                waitExplicitlyInMilliseconds(200);
            }
        } catch (Exception e) {
            throw new ApplicationException("Unexpected exception was thrown! " + e);
        }
        return isDisplayed;
    }

    public By buildCssSelectorByString(String locator, String string) {
        return By.cssSelector(String.format(locator, string));
    }

    private void verifyNotNull(By locator) {
        WebElement webElement = DriverManager.getDriver().findElement(locator);
        verifyNotNull(webElement);
    }
    private void verifyNotNull(WebElement element) {
        Assert.assertNotNull(element, "Element not found! Please check the element locator.");
    }
    public String getAttribute(By locator, String attribute) {
        verifyNotNull(locator);
        WebElement element = DriverManager.getDriver().findElement(locator);
        return element.getAttribute(attribute);
    }

    public Actions getActions() {
        return action;
    }

}
