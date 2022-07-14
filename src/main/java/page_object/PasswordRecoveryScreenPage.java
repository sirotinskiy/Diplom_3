package page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class PasswordRecoveryScreenPage {
    @FindBy(xpath = ".//a[text()='Войти']")
    private SelenideElement buttonLoginOnThePasswordRecoveryPage;

    public void clickButtonLoginOnThePasswordRecoveryPage() {
        buttonLoginOnThePasswordRecoveryPage.shouldBe(Condition.visible).click();
    }
}
