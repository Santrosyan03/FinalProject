package com.finalproject;

import org.testng.annotations.Test;

public class TestForAddingToCart extends RootTest {

    @Test
    void verify_samsung_laptop_existence() {
        String item = "Samsung";
        search.searching(item);
        filter.filterByPrice();
        filter.toggleOrder(false);
        addToCart.addItemToCart();
        addToCart.goToCart();
        addToCart.verifyCartProducts();
    }

    @Test
    void verify_lenovo_laptop_existence() {
        String item = "Lenovo LEGION 5";
        search.searching(item);
        filter.filterByPrice();
        filter.toggleOrder(false);
        addToCart.addItemToCart();
        addToCart.goToCart();
        addToCart.verifyCartProducts();
    }

    @Test
    void verify_lenovo_laptop_existence_with_selected_count() {
        String item = "Lenovo LEGION 5";
        search.searching(item);
        filter.filterByPrice();
        filter.toggleOrder(false);
        addToCart.openItem();
        addToCart.selectFirst();
        addToCart.selectSecond();
        addToCart.selectThird();
        addToCart.selectIncreaseCountBy(7);
        addToCart.selectDecreaseCountBy3();
        addToCart.clickOnItem_v2();
        addToCart.goToCart();
        addToCart.verifyCartProducts();
    }

    @Test
    void verify_samsung_and_lenovo_laptop_existence_with_color_ram_memory() {
        String item1 = "Samsung";
        search.searching(item1);
        filter.filterByPrice();
        filter.toggleOrder(false);
        addToCart.addItemToCart();
        String item2 = "Lenovo LEGION 5";
        search.searching(item2);
        filter.filterByPrice();
        filter.toggleOrder(false);
        addToCart.openItem();
        addToCart.selectFirst();
        addToCart.selectSecond();
        addToCart.selectThird();
        addToCart.clickOnItem_v2();
        addToCart.goToCart();
        addToCart.verifyCartProducts();
    }
}
