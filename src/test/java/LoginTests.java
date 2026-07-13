import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginTests extends TestBase {

    @Test
    void loginTest() {
        open("/login.html");
        $("#login-input").setValue("user1");
        $("#password-input").setValue("password1");
        $("#submit-button").click();
        $("#login-form").shouldNotBe(visible);
    }

    @Test
    @Disabled("Login by press Enter is not implemented yet")
    void successfulAuthorizationWithPressEnterTest() {
        open("/login.html");
        $("#login-input").setValue("user1");
        $("#password-input").setValue("password1");
        $("#submit-button").pressEnter();
        $("[data-testid=welcome-message]").shouldHave(text("Welcome, user1"));
    }

    @Test
    void wrongPasswordAuthorizationTest() {
        open("/login.html");
        $("#login-input").setValue("user1");
        $("#password-input").setValue("wrong password");
        $("#submit-button").pressEnter();
        $("[data-testid=error-message]").shouldHave(
                text("Wrong login or password"));
    }

    //Wrong name test
    @Test
    void emptyLoginTest() {
        open("/login.html");
        $("#password-input").setValue("password1");
        $("#submit-button").pressEnter();
        $("[data-testid=error-message]").shouldHave(
                text("Login is required (minimum 3 characters)"));
    }

    @Test
    void emptyPasswordTest() {
        //Придерживаться одного стиля
        open("/login.html");
        $("#login-input").setValue("user1");
        $("#submit-button").pressEnter();
        $("[data-testid=error-message]").shouldHave(
                text("Password is required (minimum 6 characters)"));
    }

    @Test
    void emptyLoginAndPasswordTest() {
        open("/login.html");
        $("#submit-button").click();
        $("[data-testid=error-message]").shouldHave(
                text("Login and password are required (minimum 3 and 6 characters)"));
    }

    @Test
    void shortLoginTest() {
        open("/login.html");
        $("#login-input").setValue("u1");
        $("#password-input").setValue("password1");
        $("#submit-button").click();
        $("[data-testid=error-message]").shouldHave(
                text("Login must be at least 3 characters"));
    }

    @Test
    void shortPasswordTest() {
        open("/login.html");
        $("#login-input").setValue("user1");
        $("#password-input").setValue("p1");
        $("#submit-button").click();
        $("[data-testid=error-message]").shouldHave(
                text("Password must be at least 6 characters"));
    }

}
//comments for test file delete in git
