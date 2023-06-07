import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class myapp {

    @Test
    public void testWikipediaSearch() {
        Selenide.open("https://www.wikipedia.org/");
        SelenideElement searchInput = Selenide.$("#searchInput");
        searchInput.setValue("Selenide").pressEnter();
        Selenide.$("#firstHeading").shouldHave(Condition.text("Результаты поиска"));
    }

    @Test
    public void testLinkToReturnToMainPage() {
        Selenide.open("https://www.wikipedia.org/wiki");
        Selenide.$(".vector-menu-tabs").shouldHave(Condition.text("Main Page")).click();
        Selenide.$(".vector-menu-tabs").should(Condition.exist);
    }

    @Test
    public void testArticleParagraphs() {
        Selenide.open("https://en.wikipedia.org/wiki/Selenide");

        Selenide.$(".mw-parser-output p").shouldHave(Condition.exist);
    }

    @Test
    public void testArticleContent() {
        Selenide.open("https://en.wikipedia.org/wiki/Selenide");

        Selenide.$(".mw-parser-output").shouldHave(Condition.exist);
    }


    @Test
    public void testLanguageChange() {
        Selenide.open("https://www.wikipedia.org/");
        Selenide.$("#js-link-box-en").click();
        String currentUrl = WebDriverRunner.url();
        Assertions.assertTrue(currentUrl.contains("/wiki/Main_Page"));
    }
}
