package com.finalproject.Pages;

import com.finalproject.Locators.SelectorsFilterPage;
import com.finalproject.Locators.SelectorsSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Filter {
    private WebDriver webDriver;
    private Search search;

    public Filter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void filterByPrice() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(SelectorsFilterPage.filter_bar)));
        element.click();

        WebElement price = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SelectorsFilterPage.filter_by_price)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", price);
        price.click();
    }


    public boolean checkOrder(boolean descending) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<WebElement> prices = webDriver.findElements(By.cssSelector(SelectorsSearchPage.prices_of_samsung_products));
        for (int i = 0; i < prices.size() - 1; i++) {
            if (descending && Integer.parseInt(prices.get(i).getText().substring(2).replaceAll("\\D", "")) < Integer.parseInt(prices.get(i+1).getText().substring(2).replaceAll("\\D", ""))) {
                return false;
            } else if (!descending && Integer.parseInt(prices.get(i).getText().substring(2).replaceAll("\\D", "")) > Integer.parseInt(prices.get(i+1).getText().substring(2).replaceAll("\\D", ""))) {
                return false;
            }
        }
        return true;
    }

    public void toggleOrder(boolean setAscending) {
        WebElement toggleButton = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector(SelectorsFilterPage.ascending_descending_button)));

        toggleButton.click();

        if (!setAscending) {
            toggleButton = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(By.cssSelector(SelectorsFilterPage.ascending_descending_button)));
            toggleButton.click();
        }
    }



}
