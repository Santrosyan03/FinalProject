package com.finalproject;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertTrue;

public class TestForFiltering extends RootTest {

    private final String item1 = "Samsung";
    private final String item2 = "Xiaomi";
    private final String item3 = "Iconix";

    @Test
    void samsung_products_descending_order_check() {
        search.searching(item1);
        filter.filterByPrice();
        filter.toggleOrder(false);
        assertTrue(filter.checkOrder(true));
    }

    @Test
    void samsung_products_ascending_order_check() {
        search.searching(item1);
        filter.filterByPrice();
        filter.toggleOrder(true);
        assertTrue(filter.checkOrder(false));
    }

    @Test
    void xiaomi_products_descending_order_check() {
        search.searching(item2);
        filter.filterByPrice();
        filter.toggleOrder(false);
        assertTrue(filter.checkOrder(true));
    }

    @Test
    void xiaomi_products_ascending_order_check() {
        search.searching(item2);
        filter.filterByPrice();
        filter.toggleOrder(true);
        assertTrue(filter.checkOrder(false));
    }

    @Test
    void iconix_products_descending_order_check() {
        search.searching(item3);
        filter.filterByPrice();
        filter.toggleOrder(false);
        assertTrue(filter.checkOrder(true));
    }

    @Test
    void iconix_products_ascending_order_check() {
        search.searching(item3);
        filter.filterByPrice();
        filter.toggleOrder(true);
        assertTrue(filter.checkOrder(false));
    }
}
