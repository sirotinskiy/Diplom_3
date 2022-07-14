package page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.atomic.AtomicReference;

public class RegistrationScreenPage {
    @FindBy(xpath = ".//fieldset[1]//input")
    private SelenideElement inputFieldName;

    @FindBy(xpath = ".//fieldset[2]//input")
    private SelenideElement inputFieldEmail;

    @FindBy(xpath = ".//input[@type='password']")
    private SelenideElement inputFieldPassword;

    @FindBy(xpath = ".//button[text()='Зарегистрироваться']")
    private SelenideElement buttonRegistration;

    @FindBy(xpath = ".//a[text()='Войти']")
    private SelenideElement buttonLoginOnRegistration;

    @FindBy(xpath = ".//p[text()='Войти']")
    private SelenideElement linkLogin;

    @FindBy(xpath = ".//*[contains (text(), 'Некорректный пароль')]")
    private SelenideElement errorMessage;


    public void setInputFieldName(String name) {
        inputFieldName.shouldBe(Condition.visible).setValue(name);
    }

    public void setInputFieldEmail(String email) {
        inputFieldEmail.shouldBe(Condition.visible).setValue(email);
    }

    public void setInputFieldPassword(String password) {
        inputFieldPassword.shouldBe(Condition.visible).setValue(password);
    }

    public void clickButtonRegistration() {
        buttonRegistration.shouldBe(Condition.visible).click();
    }

    public void clickLinkLogin() {
        linkLogin.shouldBe(Condition.visible).click();
    }

    public void clickButtonLoginOnRegistration(){
        buttonLoginOnRegistration.shouldBe(Condition.visible).click();
    }

    public String  getErrorMessage() {
        return errorMessage.shouldBe(Condition.visible).getText();
    }

    @Step("Create user")
    public void createUser(String name, String email, String password){
        setInputFieldName(name);
        setInputFieldEmail(email);
        setInputFieldPassword(password);
        clickButtonRegistration();
    }
}
