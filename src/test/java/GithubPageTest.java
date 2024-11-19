import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


public class GithubPageTest {
//    @Test
//    public void userCanSearch() {
//        new GithubPage().searchFor("selenide java");
//
//        SearchResultsPage results = new SearchResultsPage();
//        results.checkResultsSizeIsAtLeast(1);
//        results.checkResultHasTest(0, "Selenide: concise UI tests in Java");
//    }

    @Test
    public void selenideBasicSandbox() {
        open("https://the-internet.herokuapp.com/dynamic_loading/1");
        $("#start button").click();

        $("div #finish").shouldHave(text("Hello World!"), Duration.ofSeconds(6)); // Waits until element gets text

    }


}



