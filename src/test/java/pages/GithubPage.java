package pages;



import java.util.stream.Collectors;

public class GithubPage {

    public static final String GITHUB_PAGE_URL = "https://github.com/SeleniumHQ/selenium/pulls";
    private static final String SEARCH_INPUT = "input#js-issues-search";
    private static final String OPEN_LABEL = "#js-issues-toolbar [data-ga-click='Pull Requests, Table state, Open']";
    private static final String AUTOR = " .opened-by a";
    private static final String TITLE = "[data-hovercard-type=\"pull_request\"]";
    private static final String DATE = "relative-time";
    private static final String ROWS = "div[data-id]";
    private static final String PAGINATION = "[data-total-pages]";

//    private Page page;
//    private Locator searchInput;
//    private Locator openIssues;
//
//
//    public GithubPage(Page page) {
//        this.page = page;
//        this.searchInput = page.locator(SEARCH_INPUT);
//        this.openIssues = page.locator(OPEN_LABEL);
//    }
//
//    @Step
//    public void navigate() {
//        page.navigate(GITHUB_PAGE_URL);
//    }
//
//    @Step
//    public void searchFor(String text) {
//        searchInput.fill(text);
//        searchInput.press("Enter");
//        page.waitForLoadState(LoadState.NETWORKIDLE);
//
//    }







}
