package com.pages;


import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
public class GithubPage {

    public static final String GITHUB_PAGE_URL = "https://github.com/SeleniumHQ/selenium/pulls";
    private static final String SEARCH_INPUT = "input#js-issues-search";
    private static final String OPEN_LABEL = "#js-issues-toolbar [data-ga-click='Pull Requests, Table state, Open']";
    private static final String CLOSED_LABEL = "#js-issues-toolbar [data-ga-click='Pull Requests, Table state, Closed']";
    private static final String AUTOR = " .opened-by a";
    private static final String TITLE = "[data-hovercard-type=\"pull_request\"]";
    private static final String DATE = "relative-time";
    private static final String ROWS = "div[data-id]";
    private static final String PAGINATION = "[data-total-com.pages]";
    private SelenideElement searchField = $(SEARCH_INPUT);
    // todo should I store here elements or locators: SelenideElement vs String Locator

    public GithubPage() {
    }

    public void navigate() {
        open(GITHUB_PAGE_URL);
    }

    public void searchFor(String text) {
        log.info("Searching for {}", text);
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

    public List<Map<String, String>> collectAllRows() {
        String PAGE_QUERY = "?q=is:pr is:open &page=";
        Integer pagination = Integer.parseInt($(PAGINATION).getAttribute("data-total-com.pages"));

        log.info("Collecting PR Autor,Title,Date rows, total com.pages to parse: [{}]", pagination);
        List<Map<String, String>> prData = new ArrayList<>();


        while (pagination > 0) {
            int pageRows = $$(ROWS).size();
            ElementsCollection authorsLocator = $$(AUTOR);
            ElementsCollection titles = $$(TITLE);
            ElementsCollection dates = $$(DATE);

            for (int i = 0; i < pageRows; i++) {
                Map<String, String> prDetails = new HashMap<>();
                prDetails.put("Author", authorsLocator.get(i).innerText().trim());
                prDetails.put("Title", titles.get(i).innerText().trim());
                prDetails.put("Date", dates.get(i).getAttribute("datetime").trim());
                prData.add(prDetails);
            }
            String nextPage = GITHUB_PAGE_URL + PAGE_QUERY + pagination;
            log.info("Goint to [{}] page", nextPage);
            open(nextPage);
            pagination--;
        }

        return prData;
    }


}
