package com.finalproject.Pages;

import com.finalproject.Locators.SelectorsEditCartPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class EditCartPage {

    private WebDriver webDriver;

    public EditCartPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void clickOnEdit() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        int maxRetries = 5;
        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                WebElement edit = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(SelectorsEditCartPage.update)));
                edit.click();
                System.out.println("Clicked on the 'edit' element.");
                return;
            } catch (TimeoutException e) {
                System.out.println("Attempt " + (retry + 1) + " - TimeoutException");
            } catch (ElementNotInteractableException e) {
                System.out.println("Attempt " + (retry + 1) + " - ElementNotInteractableException");
            }
        }
    }

    public int verifyUpdate() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement el = webDriver.findElement(By.cssSelector(SelectorsEditCartPage.item_qty));
        return Integer.parseInt(el.getAttribute("data-item-qty"));
    }


    public void select() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            System.out.println("Starting to wait for the element...");
            System.out.println("Element is clickable");
            List<WebElement> clickableButtons = webDriver.findElements(By.cssSelector(SelectorsEditCartPage.memory));
            clickableButtons = clickableButtons.stream()
                    .filter(element -> !element.getAttribute("class").contains("disabled"))
                    .collect(Collectors.toList());

            if (!clickableButtons.isEmpty()) {
                Random rand = new Random();
                WebElement el = clickableButtons.get(rand.nextInt(clickableButtons.size()));
                el.click();
                System.out.println("Clicked on: " + el.getText());
            }
        } catch (TimeoutException e) {
            System.out.println("TimeoutException: Element not clickable within the specified time.");
            e.printStackTrace();
        }
    }


    public void goToEditPage() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        int maxRetries = 5;
        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                WebElement edit = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SelectorsEditCartPage.pencil)));
                edit.click();
                return;
            } catch (TimeoutException e) {
                System.out.println("Attempt " + (retry + 1) + " - TimeoutException");
            } catch (ElementNotInteractableException e) {
                System.out.println("Attempt " + (retry + 1) + " - ElementNotInteractableException");
            }
        }
    }

    public void edit() {
        WebElement webElement;
        try {
            webElement = webDriver.findElement(By.cssSelector(SelectorsEditCartPage.item_qty));
            webElement.sendKeys("3");
            webElement.sendKeys(Keys.ENTER);
        } catch (Exception e) {
            try {
                JavascriptExecutor js = (JavascriptExecutor) webDriver;
                WebElement elementToInteract = webDriver.findElement(By.cssSelector(SelectorsEditCartPage.item_qty));

                js.executeScript("arguments[0].click();", elementToInteract);
                js.executeScript("arguments[0].value='13';", elementToInteract);
                js.executeScript("arguments[0].dispatchEvent(new KeyboardEvent('keypress', {'key':'Enter'}));", elementToInteract);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


}
