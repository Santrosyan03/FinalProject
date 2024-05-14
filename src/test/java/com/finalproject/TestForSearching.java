package com.finalproject;

import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertFalse;

public class TestForSearching extends RootTest {

    @Test
    void search_samsung_successful() {
        String item = "Samsung";
        assertTrue(search.searching(item).verifyProductListingsByBrand(item));
    }

    @Test
    void search_uppercase_samsung_successful() {
        String item = "SAMSUNG";
        assertTrue(search.searching(item).verifyProductListingsByBrand(item));
    }

    @Test
    void search_lowercase_samsung_successful() {
        String item = "samsung";
        assertTrue(search.searching(item).verifyProductListingsByBrand(item));
    }

    @Test
    void search_uppercase_iconix_successful() {
        String item = "ICONIX";
        assertTrue(search.searching(item).verifyProductListingsByBrand(item));
    }

    @Test
    void search_lowercase_iconix_successful() {
        String item = "iconix";
        assertTrue(search.searching(item).verifyProductListingsByBrand(item));
    }

    @Test
    void search_half_name_lenovo_unsuccessful() {
        assertThrows(NoSuchElementException.class, () -> {
            String item = "Leno";
            search.searching(item).verifyProductListingsByBrand(item);
        });
    }

    @Test
    void search_unordered_letters_signs_digits_unsuccessful() {
        assertThrows(NoSuchElementException.class, () -> {
            String item = "dkjjAIG69)@*$XXiiej96";
            search.searching(item).verifyProductListingsByBrand(item);
        });
    }

    @Test
    void search_empty_space_lenovo_unsuccessful() {
        assertThrows(NoSuchElementException.class, () -> {
            String item = " ";
            search.searching(item).verifyProductListingsByBrand(item);
        });
    }

    @Test
    void search_misspelled_lenovo_unsuccessful() {
        assertThrows(NoSuchElementException.class, () -> {
            String item = "Lnvo";
            search.searching(item).verifyProductListingsByBrand(item);
        });
    }

    @Test
    void search_lowercase_lenovo_unsuccessful() {
        assertThrows(NoSuchElementException.class, () -> {
            String item = "lenovo";
            search.searching(item).verifyProductListingsByBrand(item);
        });
    }

    @Test
    void search_lowercase_mac_book_successful() {
        String item = "Mac Book";
        assertFalse(search.searching(item).verifyProductListingsByBrand(item));
    }

    @Test
    void search_lenovo_armenian_unsuccessful() {
        assertThrows(NoSuchElementException.class, () -> {
            String item = "Լենովո";
            search.searching(item).verifyProductListingsByBrand(item);
        });
    }
}
