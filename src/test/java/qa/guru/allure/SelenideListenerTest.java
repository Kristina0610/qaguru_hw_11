package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Configuration.browser;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideListenerTest {
  @Test
  @DisplayName("Тест нахождения элемента в issue (чистый Selenide (с Listener))")
  @Owner("Kristina")
  @Severity(SeverityLevel.CRITICAL)
  public void testIssueSearch() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    browser = "firefox";
    open("https://github.com/");
    $(".header-search-button").click();
    $("#query-builder-test").setValue("Kristina0610/qaguru_hw_11");
    $("#query-builder-test").submit();

    $(linkText("Kristina0610/qaguru_hw_11")).click();
    $("#issues-tab").click();
    $(withText("#2")).should(Condition.exist);
  }

}
