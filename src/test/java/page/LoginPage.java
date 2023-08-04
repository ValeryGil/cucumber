package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private SelenideElement loginField = $("[data-test-id='login'] input");
    private SelenideElement passwordField = $("[data-test-id='password'] input");
    private SelenideElement loginButton = $("[data-test-id='action-login']");

    LoginPage() {
        $(byText("Интернет банк"));
    }
    public VerificationPage validLogin(String login, String password) {
        loginField.shouldBe(Condition.visible).setValue(login);
        passwordField.shouldBe(Condition.visible).setValue(password);
        loginButton.shouldBe(Condition.visible).click();
        return new VerificationPage();
    }
}
