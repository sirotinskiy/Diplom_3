package page_object;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

public class MainScreenPage {
    @FindBy(xpath = ".//p[contains(text(),'Личный Кабинет')]")
    private SelenideElement buttonPersonalArea;

    @FindBy(xpath = ".//p[text()='Конструктор']")
    private SelenideElement buttonConstructor;

    @FindBy(xpath = ".//span[text()='Булки']")
    private SelenideElement sectionBun;

    @FindBy(xpath = ".//span[text()='Соусы']")
    private SelenideElement sectionSouses;

    @FindBy(xpath = ".//span[text()='Начинки']")
    private SelenideElement sectionToppings;

    @FindBy(xpath = ".//p[text()='Флюоресцентная булка R2-D3']")
    private SelenideElement fluorescentBun;

    @FindBy(xpath = ".//p[text()='Соус Spicy-X']")
    private SelenideElement souseSpicy;

    @FindBy(xpath = ".//p[text()='Мясо бессмертных моллюсков Protostomia']")
    private SelenideElement meatOfImmortalClamsProtostomia;

    @FindBy(xpath = ".//button[text()='Войти в аккаунт']")
    private SelenideElement buttonLoginOnTheMainPage;

    @FindBy(xpath = ".//h1[text()='Соберите бургер']")
    private SelenideElement textAssembleABurger;

    @FindBy(css = ".AppHeader_header__logo__2D0X2")
    private SelenideElement logoBurger;


    public void clickButtonPersonalArea() {
        buttonPersonalArea.shouldBe(Condition.visible).click();
    }

    public void clickButtonLoginOnTheMainPage() {
        buttonLoginOnTheMainPage.shouldBe(Condition.visible).click();
    }

    public String getTextAssembleABurger() {
        return textAssembleABurger.shouldBe(Condition.visible).getText();
    }

    public void clickButtonConstructor(){
        buttonConstructor.shouldBe(Condition.visible).click();
    }

    public void clickLogoBurger(){
        logoBurger.shouldBe(Condition.visible).click();
    }

    public void clickSectionBun() {
        sectionBun.shouldBe(Condition.visible).click();
    }

    public void clickSectionSouses() {
        sectionSouses.shouldBe(Condition.visible).click();
    }

    public void clickSectionToppings() {
        sectionToppings.shouldBe(Condition.visible).click();
    }

    public String getFluorescentBun() {
        return fluorescentBun.shouldBe(Condition.visible).getText();
    }

    public String getSouseSpicy() {
        return souseSpicy.shouldBe(Condition.visible).getText();
    }

    public String getMeatOfImmortalClamsProtostomia() {
        return meatOfImmortalClamsProtostomia.shouldBe(Condition.visible).getText();
    }
}
