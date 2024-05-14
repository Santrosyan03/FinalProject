package com.finalproject;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class TestForEditingCartPage extends RootTest {

    @Test
    void verify_samsung_laptop_deletion() {
        String item = "Samsung Galaxy Tab S9 FE Plus WiFi + 5G";
        search.searching(item);
        addToCart.addItemToCart();
        addToCart.goToCart();
        addToCart.deleteItem();
        assertTrue(addToCart.isDeleted());
    }

    @Test
    void verify_iconix_update_count_increase() {
        String item = "Iconix";
        search.searching(item);
        addToCart.openItem();
        editCartPage.select();
        addToCart.clickOnItem_v2();
        addToCart.goToCart();
        addToCart.selectIncreaseCountBy(2);
        editCartPage.clickOnEdit();
        addToCart.goToCart();
        assertEquals(editCartPage.verifyUpdate(), 3);
        addToCart.goToCart();
        addToCart.deleteItem();
    }

    @Test
    void verify_iconix_update_count_increase_decrease() {
        String item = "Iconix";
        search.searching(item);
        addToCart.openItem();
        editCartPage.select();
        addToCart.clickOnItem_v2();
        addToCart.goToCart();
        addToCart.selectIncreaseCountBy(7);
        addToCart.selectDecreaseCountBy3();
        addToCart.selectDecreaseCountBy3();
        editCartPage.clickOnEdit();
        assertEquals(editCartPage.verifyUpdate(), 2);
        addToCart.goToCart();
        addToCart.deleteItem();
    }

    @Test
    void verify_iconix_update_count_increase_decrease_v2() {
        String item = "Iconix";
        search.searching(item);
        addToCart.openItem();
        editCartPage.select();
        addToCart.clickOnItem_v2();
        addToCart.goToCart();
        addToCart.selectIncreaseCountBy(7);
        addToCart.selectDecreaseCountBy3();
        addToCart.selectDecreaseCountBy3();
        addToCart.selectDecreaseCountBy3();
        editCartPage.clickOnEdit();
        assertEquals(editCartPage.verifyUpdate(), 1);
        addToCart.goToCart();
        addToCart.deleteItem();
    }

    @Test
    void verify_iconix_update_count() {
        String item = "Iconix";
        search.searching(item);
        addToCart.openItem();
        editCartPage.select();
        addToCart.clickOnItem_v2();
        addToCart.goToCart();
        editCartPage.goToEditPage();
        editCartPage.select();
        editCartPage.edit();
        assertEquals(editCartPage.verifyUpdate(), 13);
        addToCart.goToCart();
        addToCart.deleteItem();
    }

}
