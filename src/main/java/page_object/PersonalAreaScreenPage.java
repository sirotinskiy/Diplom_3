package page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class PersonalAreaScreenPage {
    @FindBy(xpath = ".//input[@name ='Name']")
    private SelenideElement fieldName;

    @FindBy(xpath = ".//input[@name ='name']")
    private SelenideElement fieldLogin;

    @FindBy(xpath = ".//button[text()='Выход']")
    private SelenideElement buttonLogOut;

    public String getFieldName() {
        return fieldName.shouldBe(Condition.visible).getValue();
    }

    public String getFieldLogin() {
        return fieldLogin.shouldBe(Condition.visible).getValue();
    }

    public void clickButtonLogOut(){
        buttonLogOut.shouldBe(Condition.visible).click();
    }
}
