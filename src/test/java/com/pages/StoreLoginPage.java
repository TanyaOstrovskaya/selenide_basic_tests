package com.pages;


import lombok.extern.slf4j.Slf4j;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class StoreLoginPage {

    public static final String PAGE_URL = "https://www.saucedemo.com/";
    private static final String LOGIN_INPUT = "input[data-test=\"username\"]";
    private static final String PASSWORD_INPUT = "input[data-test=\"password\"]";
    private static final String SUBMIT_BTN = "input[data-test=\"login-button\"]";
    private static final String USER_LOGGED = ".shopping_cart_link";
    private static final String ERROR_LABEL = "h3[data-test='error']";

    public StoreLoginPage() {
    }

    public void navigate() {
        open(PAGE_URL);
    }

    public void login(String login, String pswd) {
        log.info("Signing in as user: {}", login);
        $(LOGIN_INPUT).val(login);
        $(PASSWORD_INPUT).val(pswd);
        $(SUBMIT_BTN).click();
    }

    public void checkUserLogged() {
        $(USER_LOGGED).shouldBe(visible);
    }

    public String getLoginError() {
        return $(ERROR_LABEL).text();
    }

}
