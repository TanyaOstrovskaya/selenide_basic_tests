package com;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {

    public static final String STANDARD_USER = "standard_user";
    public static final String SECRET = "secret_sauce";


    @BeforeAll
    public static void setUp() {
        Configuration.headless = false;
        Configuration.timeout = 5000;
    }

    @AfterEach
    public void tearDown() {
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
