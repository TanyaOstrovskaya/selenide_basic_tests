package com.github;

import com.BaseTest;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.pages.GithubPage;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class GithubPageTest extends BaseTest {

    @Test
    public void userCanSearch() {
        GithubPage page = new GithubPage();
        page.navigate();
        page.searchFor("is:pr is:open ");
        Integer prCountLabel = Integer.parseInt(page.getOpenIssuesCount());
        assertThat(prCountLabel).isEqualTo(61); // Flaky
    }

    @Test
    public void checkClosedIssues() {
        GithubPage page = new GithubPage();
        page.navigate();
        page.searchFor("is:pr is:close ");
        Integer prCountLabel = Integer.parseInt(page.getClosedLabel());
        assertThat(prCountLabel).isEqualTo(4086); // Flaky
    }

    @Test
    public void checkPRrows() {
        GithubPage page = new GithubPage();
        page.navigate();
        page.searchFor(" is:pr is:open  ");
        Integer prCountLabel = Integer.parseInt(page.getOpenIssuesCount());
        List<Map<String, String>> rows = page.collectAllRows();
        log.info("Check number of PRs correspond to com.pages rows:  {}", prCountLabel);
        assertThat(rows.size()).isEqualTo(prCountLabel);
        assertThat(rows).contains(Map.of("Author","RenderMichael","Title","[dotnet] Add nullability annotations to print types","Date","2024-11-18T18:19:12Z"));
    }


}



