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
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LambdaStepsTest {

  private static final String REPOSITORY = "Kristina0610/qaguru_hw_11";
  private static final int ISSUE = 2;

  @Test
  @DisplayName("Тест нахождения элемента в issue (лямбда шаги через step (name, () -> {}))")
  @Owner("Kristina")
  @Severity(SeverityLevel.CRITICAL)
  public void testLambdaStep() {
    SelenideLogger.addListener("allure", new AllureSelenide());

    step("Открывем главную страницу", () -> {
      browser = "firefox";
      open("https://github.com/");
    });
    step("Ищем репозиторий " + REPOSITORY, () -> {
      $(".header-search-button").click();
      $("#query-builder-test").setValue(REPOSITORY);
      $("#query-builder-test").submit();
    });
    step("Кликаем по ссылке репозитория", () -> {
      $(linkText(REPOSITORY)).click();
    });
    step("Открываем таб Issues", () -> {
      $("#issues-tab").click();
    });
    step("Проверяем наличие Issue с номером " + ISSUE, () -> {
      $(withText("#" + ISSUE)).should(Condition.exist);
    });

  }

  @Test
  @DisplayName("Тест нахождения элемента в issue (шаги с аннотацией @Step)")
  @Owner("Kristina")
  @Severity(SeverityLevel.CRITICAL)
  public void testAnnotatedStep() {
    SelenideLogger.addListener("allure", new AllureSelenide());
    AnnotationSteps steps = new AnnotationSteps();

    steps.openMainPage();
    steps.searchForRepository(REPOSITORY);
    steps.clickOnRepositoryLink(REPOSITORY);
    steps.openIssueTab();
    steps.shouldSeeIssueWithNumber(ISSUE);
    steps.takeScreenshot();
  }
}
