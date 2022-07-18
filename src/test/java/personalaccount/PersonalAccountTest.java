package personalaccount;

import api.model.User;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import page_object.*;

import static api.client.CreateUser.createUser;
import static api.client.DeleteUser.deleteUser;
import static api.client.RestAssuredClient.BASE_URL;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonalAccountTest {
    private  MainScreenPage mainScreenPage;
    private  LoginScreenPage loginScreenPage;
    private  RegistrationScreenPage registrationScreenPage;

    private  PersonalAreaScreenPage personalAreaScreenPage;

    private User user;

    private String token = "";

    @Before
    public void setUp(){
        user = User.builder()
                .email(new StringBuilder(RandomStringUtils.randomAlphabetic(5) + "@ggmail.com").toString().toLowerCase())
                .password(RandomStringUtils.randomAlphabetic(7))
                .name(RandomStringUtils.randomAlphabetic(7))
                .build();

        token = createUser(user).extract().path("accessToken");

        Configuration.browser = "chrome";

        mainScreenPage = open(BASE_URL, MainScreenPage.class);
        loginScreenPage = open(BASE_URL, LoginScreenPage.class);
        registrationScreenPage = open(BASE_URL, RegistrationScreenPage.class);
        personalAreaScreenPage = open(BASE_URL,PersonalAreaScreenPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();
    }

    @After
    public void shutDown(){
        WebDriverRunner.closeWebDriver();
        if(!token.isEmpty()){
            deleteUser(token);
        }
    }


    @Test
    @DisplayName("Переход по клику на «Личный кабинет»")
    public void clickingOnMyAccount(){

        mainScreenPage.clickButtonPersonalArea();


        loginScreenPage.loginUser(user.getEmail(),
                user.getPassword());

        mainScreenPage.clickButtonPersonalArea();

        String expectedName = user.getName();
        String expectedLogin = user.getEmail();

        String actualName = personalAreaScreenPage.getFieldName();
        String actualLogin = personalAreaScreenPage.getFieldLogin();

        assertThat(expectedName, equalTo(actualName));
        assertThat(expectedLogin, equalTo(actualLogin));
    }

    @Test
    @DisplayName("Переход по клику на «Конструктор» из личного кабинета.")
    public void clickingOnConstructor(){

        mainScreenPage.clickButtonPersonalArea();


        loginScreenPage.loginUser(user.getEmail(),
                user.getPassword());

        mainScreenPage.clickButtonPersonalArea();

        mainScreenPage.clickButtonConstructor();

        String expectedTextAssembleABurger = "Соберите бургер";


        String actualTextAssembleABurger = mainScreenPage.getTextAssembleABurger();


        assertThat(expectedTextAssembleABurger, equalTo(actualTextAssembleABurger));
    }

    @Test
    @DisplayName("Переход по клику на «Stellar Burgers» из личного кабинета.")
    public void clickingOnStellarBurgers(){

        mainScreenPage.clickButtonPersonalArea();


        loginScreenPage.loginUser(user.getEmail(),
                user.getPassword());

        mainScreenPage.clickButtonPersonalArea();

        mainScreenPage.clickLogoBurger();

        String expectedTextAssembleABurger = "Соберите бургер";


        String actualTextAssembleABurger = mainScreenPage.getTextAssembleABurger();


        assertThat(expectedTextAssembleABurger, equalTo(actualTextAssembleABurger));
    }

    @Test
    @DisplayName("Выход по кнопке «Выйти» в личном кабинете")
    public void logOutAccount(){

        mainScreenPage.clickButtonPersonalArea();


        loginScreenPage.loginUser(user.getEmail(),
                user.getPassword());

        mainScreenPage.clickButtonPersonalArea();

        personalAreaScreenPage.clickButtonLogOut();

        String expectedTextLogin = "Вход";

        String actualTextLogin = loginScreenPage.getTextLogin();

        assertThat(expectedTextLogin, equalTo(actualTextLogin));
    }

}
