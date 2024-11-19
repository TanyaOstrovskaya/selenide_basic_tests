import org.junit.jupiter.api.Test;
import pages.GithubPage;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.assertj.core.api.Assertions.assertThat;


public class GithubPageTest {

    @Test
    public void userCanSearch() {
        GithubPage page = new GithubPage();
        page.navigate();
        page.searchFor("is:pr is:open ");
        Integer prCountLabel = Integer.parseInt(page.getOpenIssuesCount());
        assertThat(prCountLabel).isEqualTo(67);
    }

    @Test
    public void checkClosedIssues() {
        GithubPage page = new GithubPage();
        page.navigate();
        page.searchFor("is:pr is:close ");
        Integer prCountLabel = Integer.parseInt(page.getClosedLabel());
        assertThat(prCountLabel).isEqualTo(4075);
    }

    @Test
    public void selenideBasicSandbox() {
        open("https://the-internet.herokuapp.com/dynamic_loading/1");
        $("#start button").click();
        $("div #finish").shouldHave(text("Hello World!"), Duration.ofSeconds(6)); // Waits until element gets text
    }


}



