package pages;


import com.codeborne.selenide.SelenideElement;

import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GithubPage {

    public static final String GITHUB_PAGE_URL = "https://github.com/SeleniumHQ/selenium/pulls";
    private static final String SEARCH_INPUT = "input#js-issues-search";
    private static final String OPEN_LABEL = "#js-issues-toolbar [data-ga-click='Pull Requests, Table state, Open']";
    private static final String CLOSED_LABEL = "#js-issues-toolbar [data-ga-click='Pull Requests, Table state, Closed']";
    private static final String AUTOR = " .opened-by a";
    private static final String TITLE = "[data-hovercard-type=\"pull_request\"]";
    private static final String DATE = "relative-time";
    private static final String ROWS = "div[data-id]";
    private static final String PAGINATION = "[data-total-pages]";
    private SelenideElement searchField = $(SEARCH_INPUT);


    public GithubPage() {
    }

    public void navigate() {
        open(GITHUB_PAGE_URL);
    }

    public void searchFor(String text) {
        searchField.val(text);
        searchField.pressEnter();
    }

    public String getOpenIssuesCount() {
        return $(OPEN_LABEL).text().chars()
                .filter(Character::isDigit)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

    public String getClosedLabel() {
        return $(CLOSED_LABEL).text().chars()
                .filter(Character::isDigit)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
    }

}
