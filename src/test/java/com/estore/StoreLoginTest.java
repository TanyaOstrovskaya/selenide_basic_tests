package com.estore;

import com.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import com.pages.StoreLoginPage;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class StoreLoginTest extends BaseTest {

    public static final String BLOCKED_USER = "locked_out_user";



    @Test
    public void userCanLogin() {
        StoreLoginPage page = new StoreLoginPage();
        page.navigate();
        page.login(STANDARD_USER, SECRET);
        page.checkUserLogged();
    }

    @Test
    public void cannotLoginAsBlockedUser() {
        StoreLoginPage page = new StoreLoginPage();
        page.navigate();
        page.login(BLOCKED_USER, SECRET);
        assertThat(page.getLoginError()).isEqualTo("Epic sadface: Sorry, this user has been locked out.");

    }


    static Stream<Arguments> invalidLoginDataProvider() {
        return Stream.of(
                Arguments.of("", SECRET, "Epic sadface: Username is required"),
                Arguments.of(STANDARD_USER, "", "Epic sadface: Password is required"),
                Arguments.of("invalidUser", SECRET, "Epic sadface: Username and password do not match any user in this service"),
                Arguments.of(STANDARD_USER, "wrong", "Epic sadface: Username and password do not match any user in this service"),
                Arguments.of("", "", "Epic sadface: Username is required")
        );
    }
    @ParameterizedTest(name = "Test {index}: username={0}, password={1}, errormsg={2} ")
    @MethodSource("invalidLoginDataProvider")
    public void shouldNotLoginWithInvalidFields(String username, String password, String errormsg) {
        StoreLoginPage page = new StoreLoginPage();
        page.navigate();
        page.login(username, password);
        assertThat(page.getLoginError()).isEqualTo(errormsg);
    }




}



