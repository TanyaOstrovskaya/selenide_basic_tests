package com.pages;


import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.xpath;

@Slf4j
public class CartPage {

    public static final String PAGE_URL = "https://www.saucedemo.com/cart.html/";
    private static final String ITEM_CARD = "div[data-test='inventory-item']";
    private static final String ITEM_CARD_BYTEXT = "//div[@data-test='inventory-item'][contains(.,'%s')]";

    private static final String ITEM_NAME = "div[data-test='inventory-item-name']";
    private static final String ITEM_PRICE = "div[data-test='inventory-item-price']";
    private static final String REMOVE_BTN = "button.cart_button";
    private static final String CART_COUNTER = "span[data-test='shopping-cart-badge']";


    public CartPage() {
    }

    public void navigate() {
        open(PAGE_URL);
    }

    public void checkOnCartPage() {
        $(byText("Your Cart")).shouldBe(visible);
    }


    public Map<String, String> getCartItems() {
        return $$(ITEM_CARD).stream().collect(Collectors.toMap(
                e -> e.find(ITEM_NAME).text(),
                e -> e.find(ITEM_PRICE).text()
        ));
    }

    public void removeItem(String itemName) {
        $(xpath(String.format(ITEM_CARD_BYTEXT, itemName))).find(REMOVE_BTN).click();
    }
}
