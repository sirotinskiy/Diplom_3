package page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

public class LoginScreenPage {
    // локатор поля "Email"
    @FindBy(xpath = ".//input[@name ='name']")
    private SelenideElement inputFieldEmail;

    // локатор поля "Пароль"
    @FindBy(xpath = ".//input[@name ='Пароль']")
    private SelenideElement inputFieldPassword;

    //локатор кнопки Войти
    @FindBy(xpath = ".//button[text()='Войти']")
    private SelenideElement buttonLogin;
    @FindBy(xpath = ".//a[text()='Зарегистрироваться']")
    private SelenideElement linkRegistration;

    @FindBy(xpath = ".//h2[text()='Вход']")
    private SelenideElement textLogin;

    @FindBy(xpath = ".//a[text()='Восстановить пароль']")
    private SelenideElement buttonPasswordRecoveryOnTheLoginPage;


    public void setInputFieldEmail(String email) {
        inputFieldEmail.shouldBe(Condition.visible).setValue(email);
    }

    public void setInputFieldPassword(String password) {
        inputFieldPassword.shouldBe(Condition.visible).setValue(password);
    }

    public void clickButtonLogin() {
        buttonLogin.shouldBe(Condition.visible).click();
    }

    public void clickLinkRegistration() {
        linkRegistration.shouldBe(Condition.visible).click();
    }

    public String getTextLogin() {
        return textLogin.shouldBe(Condition.visible).getText();
    }

    public void clickButtonPasswordRecoveryOnTheLoginPage() {
        buttonPasswordRecoveryOnTheLoginPage.shouldBe(Condition.visible).click();
    }

    @Step("Login user")
    public void loginUser(String email, String password){
        setInputFieldEmail(email);
        setInputFieldPassword(password);
        clickButtonLogin();
    }
}
