package com.estore;

import com.BaseTest;
import com.pages.CartPage;
import com.pages.StoreLoginPage;
import com.pages.StoreProductsPage;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

@Slf4j
public class StoreProductsTest extends BaseTest {

    public static final String ITEMTOADD = "Sauce Labs Onesie";
    public static final String SECOND_ITEM = "Sauce Labs Bike Light";
    public static final String MORE_ITEM = "Sauce Labs Bolt T-Shirt";

    @Test
    public void shouldAddItemToCart() {
        StoreLoginPage page = new StoreLoginPage();
        page.navigate();
        page.login(STANDARD_USER, SECRET);
        page.checkUserLogged();

        StoreProductsPage products = new StoreProductsPage();
        products.addToCartByItemName(ITEMTOADD);
        products.addToCartByItemName(SECOND_ITEM);
        assertThat(products.getCartBadgeCount()).as("Badge should have the same items count").isEqualTo(2);
        CartPage cartDetails = products.gotoCart();
        cartDetails.checkOnCartPage();
        Map<String, String> actualItems = cartDetails.getCartItems();
        assertThat(actualItems).containsOnly(entry(ITEMTOADD, "$7.99"), entry(SECOND_ITEM, "$9.99"));
    }

    @Test
    public void shouldRemoveItemFromCart() {
        StoreLoginPage page = new StoreLoginPage();
        page.navigate();
        page.login(STANDARD_USER, SECRET);
        page.checkUserLogged();

        StoreProductsPage products = new StoreProductsPage();
        products.addToCartByItemName(ITEMTOADD);
        products.addToCartByItemName(SECOND_ITEM);
        products.addToCartByItemName(MORE_ITEM);
        assertThat(products.getCartBadgeCount()).as("Badge should have the same items count").isEqualTo(3);
        CartPage cartDetails = products.gotoCart();
        cartDetails.checkOnCartPage();
        cartDetails.removeItem(SECOND_ITEM);
        Map<String, String> actualItems = cartDetails.getCartItems();
        assertThat(actualItems).containsOnly(entry(ITEMTOADD, "$7.99"), entry(MORE_ITEM, "$15.99"));
    }




}



