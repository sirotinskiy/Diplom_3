package registration;

import api.model.User;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.*;
import page_object.LoginScreenPage;
import page_object.MainScreenPage;
import page_object.RegistrationScreenPage;

import static api.client.DeleteUser.deleteUser;
import static api.client.LoginUser.loginUser;
import static api.client.RestAssuredClient.BASE_URL;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RegistrationTest {

    private static MainScreenPage mainScreenPage;
    private static LoginScreenPage loginScreenPage;
    private static RegistrationScreenPage registrationScreenPage;

    private User user;

    private String token = "";

    @BeforeClass
    public static void setUp(){

        Configuration.browser = "chrome";


        mainScreenPage = open(BASE_URL, MainScreenPage.class);
        loginScreenPage = open(BASE_URL, LoginScreenPage.class);
        registrationScreenPage = open(BASE_URL, RegistrationScreenPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();

    }

    @After
    public void shutDown(){
        if(!token.isEmpty()){
            deleteUser(token);
        }
    }

    @AfterClass
    public static void shutDownClass(){
        WebDriverRunner.closeWebDriver();
    }

    @Test
    @DisplayName("Регистрация с валидными данными")
    public void successRegistrationUser(){
        String expectTextLogin = "Вход";

        user = User.builder()
                .email(new StringBuilder(RandomStringUtils.randomAlphabetic(5) + "@ggmail.com").toString().toLowerCase())
                .password(RandomStringUtils.randomAlphabetic(7))
                .name(RandomStringUtils.randomAlphabetic(7))
                .build();


        mainScreenPage.clickButtonPersonalArea();
        loginScreenPage.clickLinkRegistration();

        registrationScreenPage.createUser(user.getName(),
                                          user.getEmail(),
                                          user.getPassword());
        String actualTextLogin = loginScreenPage.getTextLogin();

        assertThat(expectTextLogin, equalTo(actualTextLogin));

        token = loginUser(User.builder()
                .email(user.getEmail())
                .password(user.getPassword()).build())
                .extract().path("accessToken");
    }

    @Test
    @DisplayName("Регистрация с невалидными паролем (менее 5 знаков)")
    public void registrationUserWithAShortPassword(){
        String expectErrorMessage = "Некорректный пароль";

        user = User.builder()
                .email(new StringBuilder(RandomStringUtils.randomAlphabetic(5) + "@ggmail.com").toString().toLowerCase())
                .password(RandomStringUtils.randomAlphabetic(3))
                .name(RandomStringUtils.randomAlphabetic(7))
                .build();


        mainScreenPage.clickButtonPersonalArea();
        loginScreenPage.clickLinkRegistration();

        registrationScreenPage.createUser(user.getName(),
                user.getEmail(),
                user.getPassword());

        String actualErrorMessage = registrationScreenPage.getErrorMessage();

        assertThat(expectErrorMessage, equalTo(actualErrorMessage));
    }
}
