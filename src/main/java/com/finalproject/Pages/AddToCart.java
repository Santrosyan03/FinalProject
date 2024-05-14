package com.finalproject.Pages;

import com.finalproject.Locators.SelectorsAddToCartPage;

import com.finalproject.Locators.SelectorsEditCartPage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class AddToCart {
    private WebDriver webDriver;
    private List<String> existingItems = new ArrayList<>();
    private int existingItemsLength = 0;

    public AddToCart(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void addItemToCart() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("span.dark_layer")));
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SelectorsAddToCartPage.add_to_cart)));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", addToCart);
        try {
            addToCart.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) webDriver).executeScript("arguments[0].click();", addToCart);
        }

        WebElement itemInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SelectorsAddToCartPage.item_info)));
        existingItems.add(itemInfo.getText());
        existingItemsLength++;
    }


    public void goToCart() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        int maxRetries = 5;

        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(SelectorsAddToCartPage.go_to_cart)));
                addToCart.click();
                System.out.println("Clicked on 'Go to Cart' button.");
                return;
            } catch (TimeoutException e) {
                System.out.println("Attempt " + (retry + 1) + " - TimeoutException: 'Go to Cart' button not visible, retrying...");
            }
        }

        System.out.println("Reached maximum number of retries. Unable to click on 'Go to Cart' button.");
    }


    public boolean verifyCartProducts() {
        List<WebElement> cartItems = webDriver.findElements(By.cssSelector(SelectorsAddToCartPage.cart_item));
        for (int i = 0; i < cartItems.size(); i++) {
            WebElement cartItem = cartItems.get(i);
            try {
                if (!cartItem.getText().contains(existingItems.get(i))) {
                    return false;
                }
            } catch (StaleElementReferenceException e) {
                cartItems = webDriver.findElements(By.cssSelector(SelectorsAddToCartPage.cart_item));
                i--;
            }
        }
        return true;
    }

    public void openItem() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        int maxRetries = 5;

        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                WebElement openItem = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(SelectorsAddToCartPage.item_picture)));
                openItem.click();
                System.out.println("Clicked on the item picture.");
                return;
            } catch (ElementNotInteractableException e) {
                System.out.println("Attempt " + (retry + 1) + " - ElementNotInteractableException: Item picture is not interactable, retrying...");
            }
        }

        System.out.println("Reached maximum number of retries. Unable to click on the item picture.");
    }


    public void selectFirst() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> clickableButtons = webDriver.findElements(By.className(SelectorsAddToCartPage.first));

        if (!clickableButtons.isEmpty()) {
            Random rand = new Random();
            WebElement el = clickableButtons.get(rand.nextInt(clickableButtons.size()));
            el.click();
            System.out.println(el.getText());
        }
    }

    public void selectSecond() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> clickableButtons = getWebElements("Second");

        if (!clickableButtons.isEmpty()) {
            Random rand = new Random();
            WebElement el = clickableButtons.get(rand.nextInt(clickableButtons.size()));
            el.click();
            System.out.println(el.getText());
        }
    }

    public void selectThird() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<WebElement> clickableButtons = getWebElements("Third");

        if (!clickableButtons.isEmpty()) {
            Random rand = new Random();
            WebElement el = clickableButtons.get(rand.nextInt(clickableButtons.size()));
            el.click();
            System.out.println(el.getText());
        }
    }

    public int selectIncreaseCountBy(int c) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
        WebElement el = null;
        int maxRetries = 5;

        for (int retry = 0; retry < maxRetries; retry++) {
            try {
                el = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SelectorsAddToCartPage.select_increase_count_of_item)));
                break;
            } catch (TimeoutException e) {
                System.out.println("Attempt " + (retry + 1) + " - TimeoutException: Element not clickable, retrying...");
                if (retry == maxRetries - 1) {
                    System.out.println("Reached maximum number of retries. Exiting...");
                    return -1;
                }
            }
        }

        if (el != null) {
            for (int i = 0; i < c; i++) {
                el.click();
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            System.out.println(el.getText());
            return 0;
        } else {
            return -1;
        }
    }



    public void selectDecreaseCountBy3() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        WebElement el = webDriver.findElement(By.cssSelector(SelectorsAddToCartPage.select_decrease_count_of_item));

        int count = 3;
        for (int i = 0; i < count; i++) {
            el.click();
        }
        System.out.println(el.getText());
    }

    public List<WebElement> getWebElements(String item) {
        By xpath;
        switch (item) {
            case "First":
                xpath = By.xpath(SelectorsAddToCartPage.select_first_of_item);
                break;
            case "Second":
                xpath = By.xpath(SelectorsAddToCartPage.select_second_of_item);
                break;
            case "Third":
                xpath = By.xpath(SelectorsAddToCartPage.select_third_of_item);
                break;
            default:
                return new ArrayList<>();
        }

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(xpath))
                .stream()
                .filter(element -> !element.getAttribute("class").contains("disabled"))
                .collect(Collectors.toList());
    }


    public void clickOnItem_v2() {
        int quantity = 4;
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        try {
            try {
                Thread.sleep(3500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            WebElement itemInfo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(SelectorsAddToCartPage.item_info_2)));
            for (int i = 0; i < quantity; i++) {
                existingItems.add(itemInfo.getText());
                existingItemsLength++;
            }

            WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(By.id(SelectorsAddToCartPage.add_to_cart_v2)));
            addToCart.click();
        } catch (NoSuchElementException e) {
            System.out.println("Unable to locate an element: " + e.getMessage());
            throw e;
        }
    }



    public void deleteItem() {
        try {
            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
            WebElement deleteFromCart = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SelectorsEditCartPage.delete)));
            deleteFromCart.click();
            Thread.sleep(2000);
            WebElement deleteConfirmation = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(SelectorsEditCartPage.ok)));
            deleteConfirmation.click();

            existingItemsLength--;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            System.out.println("TimeoutException: Delete button or confirmation button not clickable within the specified time.");
            e.printStackTrace();
        } catch (ElementNotInteractableException e) {
            System.out.println("ElementNotInteractableException: Delete button or confirmation button not interactable.");
            e.printStackTrace();
        }
    }


    public boolean isDeleted() {
        return existingItemsLength == existingItems.size() - 1;
    }
}
