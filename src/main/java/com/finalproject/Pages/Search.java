package com.finalproject.Pages;

import com.finalproject.Locators.SelectorsSearchPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Search {

    private WebDriver webDriver;

    public Search(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public Search searching(String itemToBeSearched) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.id(SelectorsSearchPage.search_bar)));
        element.clear();

        try {
            element = wait.until(ExpectedConditions.elementToBeClickable(By.id(SelectorsSearchPage.search_bar)));
            element.sendKeys(itemToBeSearched);
            element.sendKeys(Keys.ENTER);
        } catch (StaleElementReferenceException e) {
            element = wait.until(ExpectedConditions.elementToBeClickable(By.id(SelectorsSearchPage.search_bar)));
            element.sendKeys(itemToBeSearched);
            element.sendKeys(Keys.ENTER);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return new Search(webDriver);
    }



    public boolean getFirstProduct(String pattern) {
        WebElement productsContainer = webDriver.findElement(By.cssSelector(SelectorsSearchPage.list_of_products));
        List<WebElement> productList = productsContainer.findElements(By.cssSelector(SelectorsSearchPage.info_of_lenovo_products));

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return isSequenceContained(productList.get(0).getText(), pattern);
    }

    public List<WebElement> getAllProducts(String pattern) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebElement productsContainer = webDriver.findElement(By.cssSelector(SelectorsSearchPage.list_of_products));
        List<WebElement> productList = new ArrayList<>();

        if (pattern.toLowerCase().contains("lenovo")) {
            productList = productsContainer.findElements(By.cssSelector(SelectorsSearchPage.info_of_lenovo_products));
        } else if (pattern.toLowerCase().contains("samsung")) {
            productList = productsContainer.findElements(By.cssSelector(SelectorsSearchPage.info_of_samsung_products));
        }  else if (pattern.toLowerCase().contains("iconix")) {
            productList = productsContainer.findElements(By.cssSelector(SelectorsSearchPage.info_of_iconix_products));
        }

        return productList;
    }

    public boolean verifyProductListingsByBrand(String pattern) {
        List<WebElement> productList = getAllProducts(pattern);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        if (productList.isEmpty()) {
            return false;
        }

        for (WebElement product : productList) {
            if (!isSequenceContained(product.getText().toLowerCase(), pattern.toLowerCase())) {
                return false;
            }
        }
        return true;
    }

    public static boolean isSequenceContained(String source, String sequence) {
        int seqIndex = 0;
        for (int i = 0; i < source.length() && seqIndex < sequence.length(); i++) {
            if (source.charAt(i) == sequence.charAt(seqIndex)) {
                seqIndex++;
            }
        }
        return seqIndex == sequence.length();
    }




}
