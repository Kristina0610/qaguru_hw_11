package qa.guru.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideListenerTest extends TestBase {
  @Test
  @DisplayName("Тест нахождения элемента в issue (чистый Selenide (с Listener))")
  @Feature("Issue в репозитории")
  @Story("Отображение Issue во вкладке Issue")
  @Owner("Kristina")
  @Severity(SeverityLevel.CRITICAL)
  @Link(value = "prod", url = "https://github.com")
  public void testIssueSearch() {
    open("/");
    $(".header-search-button").click();
    $("#query-builder-test").setValue("Kristina0610/qaguru_hw_11");
    $("#query-builder-test").submit();

    $(linkText("Kristina0610/qaguru_hw_11")).click();
    $("#issues-tab").click();
    $(withText("#2")).should(Condition.exist);
  }

}
