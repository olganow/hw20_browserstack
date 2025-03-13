package tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.*;
import static io.qameta.allure.Allure.step;

public class SearchTest extends TestBase {
    @Test
    @DisplayName("Validate that articles are presented on the search list")
    void successfulSearchTest() {
        step("SSearch Wikipedia", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("Appium");
        });
        step("The list with articles isn't empty", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_title"))
                        .shouldHave(sizeGreaterThan(0)));
    }

    @Test
    @DisplayName("Navigate to the article from the search list")
    void openArticleFromSearchTest() {
        step("Type search", () -> {
            $(accessibilityId("Search Wikipedia")).click();
            $(id("org.wikipedia.alpha:id/search_src_text")).sendKeys("algol");
        });
        step("Open the first article", () ->
                $$(id("org.wikipedia.alpha:id/page_list_item_container")).first().click());
        step("Check opening", () ->
                $(id("org.wikipedia.alpha:id/view_wiki_error_text"))
                        .shouldHave(text("An error occurred")));
    }


}
