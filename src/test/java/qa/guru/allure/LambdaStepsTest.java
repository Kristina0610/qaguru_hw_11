package qa.guru.allure;

import com.codeborne.selenide.Condition;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class LambdaStepsTest extends TestBase {

  private static final String REPOSITORY = "Kristina0610/qaguru_hw_11";
  private static final int ISSUE = 2;

  @Test
  @DisplayName("Тест нахождения элемента в issue (лямбда шаги через step (name, () -> {}))")
  @Feature("Issue в репозитории")
  @Story("Отображение Issue во вкладке Issue")
  @Owner("Kristina")
  @Severity(SeverityLevel.CRITICAL)
  @Link(value = "prod", url = "https://github.com")
  public void testLambdaStep() {
    step("Открывем главную страницу", () -> {
      open("/");
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
  @Feature("Issue в репозитории")
  @Story("Отображение Issue во вкладке Issue")
  @Owner("Kristina")
  @Severity(SeverityLevel.CRITICAL)
  @Link(value = "prod", url = "https://github.com")
  public void testAnnotatedStep() {
    AnnotationSteps steps = new AnnotationSteps();

    steps.openMainPage();
    steps.searchForRepository(REPOSITORY);
    steps.clickOnRepositoryLink(REPOSITORY);
    steps.openIssueTab();
    steps.shouldSeeIssueWithNumber(ISSUE);
    steps.takeScreenshot();
  }
}
