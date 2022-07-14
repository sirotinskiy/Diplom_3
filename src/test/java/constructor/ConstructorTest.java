package constructor;

import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page_object.MainScreenPage;

import static api.client.RestAssuredClient.BASE_URL;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConstructorTest {
    private MainScreenPage mainScreenPage;
    @Before
    public void setUp() {
        mainScreenPage = open(BASE_URL, MainScreenPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
    }
    @After
    public void shutDown(){
        WebDriverRunner.closeWebDriver();
    }

    @Test
    @DisplayName("Переход к разделу булки")
    public void goToTheBunsSection(){
        String expectedTextFluorescentBun = "Флюоресцентная булка R2-D3";
        String actualTextFluorescentBun = mainScreenPage.getFluorescentBun();

        assertThat(expectedTextFluorescentBun, equalTo(actualTextFluorescentBun));
    }

    @Test
    @DisplayName("Переход к разделу соусы")
    public void goToTheSousesSection(){
        mainScreenPage.clickSectionSouses();
        String expectedTextSouseSpicy = "Соус Spicy-X";
        String actualTextSouseSpicy = mainScreenPage.getSouseSpicy();

        assertThat(expectedTextSouseSpicy, equalTo(actualTextSouseSpicy));
    }

    @Test
    @DisplayName("Переход к разделу начинки")
    public void goToTheToppingSection(){
        mainScreenPage.clickSectionToppings();
        String expectedTextMeatOfImmortalClamsProtostomia = "Мясо бессмертных моллюсков Protostomia";
        String actualTextMeatOfImmortalClamsProtostomia = mainScreenPage.getMeatOfImmortalClamsProtostomia();

        assertThat(expectedTextMeatOfImmortalClamsProtostomia, equalTo(actualTextMeatOfImmortalClamsProtostomia));
    }

}
