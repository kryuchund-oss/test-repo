import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class LogoutTests extends TestBase{

    @Test
    @Disabled("Bad practice - long e2e test, split to atomic tests")
    void successfulLogoutTest(){
        open("/login.html");
        $("#login-input").setValue("user1");
        $("#password-input").setValue("password1");
        $("#submit-button").click();
        $("#welcome-message").shouldHave(text("Welcome, user1"));

        $("#logout-button").click();
        $("#success-panel").shouldNotBe(visible);
    }

    @Test
    void successfulLogoutWithLocalStorage(){
        open("/login.html");
        localStorage().setItem("authUser", "user1");
        open("/login.html");

        $("#logout-button").click();
        $("#success-panel").shouldNotBe(visible);
    }


}
