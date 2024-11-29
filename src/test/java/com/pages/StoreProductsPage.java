package com.pages;


import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.xpath;

@Slf4j
public class StoreProductsPage {

    public static final String PAGE_URL = "https://www.saucedemo.com/inventory.html/";
    private static final String ITEM_CARD = "//div[@data-test='inventory-item'][contains(.,'%s')]";
    private static final String ADD_TO_CART_BTN = "button.btn_inventory";
    private static final String CART_COUNTER = "span[data-test=\"shopping-cart-badge\"]";
    private static final String GOTO_CART_ICON = "a.shopping_cart_link";


    public StoreProductsPage() {
    }

    public void navigate() {
        open(PAGE_URL);
    }

    public void addToCartByItemName(String productName) {
        log.info("Adding product to cart: {}", productName);
        String findItemCardByText = String.format(ITEM_CARD, productName);
        $(xpath(findItemCardByText)).find(ADD_TO_CART_BTN).click();
        $(xpath(findItemCardByText)).shouldHave(text("Remove"));
    }

    public void checkOnProductsPage() {
        $(byText("Products")).shouldBe(visible);
    }

    public CartPage gotoCart() {
        $(GOTO_CART_ICON).click();
        return new CartPage();
    }

    public int getCartBadgeCount() {
        return Integer.parseInt($(CART_COUNTER).text());
    }

}
