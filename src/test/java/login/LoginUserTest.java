package login;

import api.model.User;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import page_object.*;

import static api.client.CreateUser.createUser;
import static api.client.DeleteUser.deleteUser;
import static api.client.RestAssuredClient.BASE_URL;
import static com.codeborne.selenide.Selenide.open;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginUserTest {

    private MainScreenPage mainScreenPage;
    private LoginScreenPage loginScreenPage;
    private RegistrationScreenPage registrationScreenPage;

    private PersonalAreaScreenPage personalAreaScreenPage;

    private PasswordRecoveryScreenPage passwordRecoveryScreenPage;

    private User user;

    private String token = "";

    @Before
    public void setUp() throws InterruptedException {
        mainScreenPage = open(BASE_URL, MainScreenPage.class);
        loginScreenPage = open(BASE_URL, LoginScreenPage.class);
        registrationScreenPage = open(BASE_URL, RegistrationScreenPage.class);
        personalAreaScreenPage = open(BASE_URL,PersonalAreaScreenPage.class);
        passwordRecoveryScreenPage = open(BASE_URL,PasswordRecoveryScreenPage.class);
        WebDriverRunner.getWebDriver().manage().window().fullscreen();

        user = User.builder()
                .email(new StringBuilder(RandomStringUtils.randomAlphabetic(5) + "@ggmail.com").toString().toLowerCase())
                .password(RandomStringUtils.randomAlphabetic(7))
                .name(RandomStringUtils.randomAlphabetic(7))
                .build();

        token = createUser(user).extract().path("accessToken");
    }

    @After
    public void shutDown(){
        WebDriverRunner.closeWebDriver();

        if(!token.isEmpty()){
            deleteUser(token);
        }
    }

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    public void loginByClickingTheLoginButtonOnTheMainPage(){
        mainScreenPage.clickButtonLoginOnTheMainPage();
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
    @DisplayName("Вход через кнопку «Личный кабинет»")
    public void loginByClickingButtonPersonalAreaOnTheMainPage(){
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
    @DisplayName("Вход через кнопку в форме регистрации")
    public void loginByClickingButtonLoginOnTheRegistrationPage(){
        mainScreenPage.clickButtonLoginOnTheMainPage();

        loginScreenPage.clickLinkRegistration();

        registrationScreenPage.clickButtonLoginOnRegistration();

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
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    public void loginByClickingButtonLoginOnThePasswordRecoveryPage(){
        mainScreenPage.clickButtonLoginOnTheMainPage();

        loginScreenPage.clickButtonPasswordRecoveryOnTheLoginPage();

        passwordRecoveryScreenPage.clickButtonLoginOnThePasswordRecoveryPage();

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

}
