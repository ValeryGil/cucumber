package steps;

import com.codeborne.selenide.Selenide;
import data.DataHelper;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Пусть;
import io.cucumber.java.ru.Тогда;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import page.DashboardPage;
import page.LoginPage;
import page.RefillCardPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class TransferMoneySteps {
    public static DashboardPage dashboardPage;
    public static LoginPage loginPage;
    public static RefillCardPage refillCardPage;
    public static VerificationPage verificationPage;

    @Пусть("пользователь залогинен с именем {string} и паролем {string}")
    public void authUser(String login, String password) {
        loginPage = Selenide.open("http://localhost:9999", LoginPage.class);
        verificationPage = loginPage.validLogin(login, password);
        var authInfo = DataHelper.getAuthInfo();
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Когда("пользователь переводит {int} рублей с карты с номером {string} на свою {int} карту с главной страницы")
    public void transferMoney(int amount, String cardNumber, int cardIndex) {
        refillCardPage = dashboardPage.selectCard(cardIndex);
        dashboardPage = refillCardPage.refillCard(String.valueOf(amount), cardNumber);
    }

    @Тогда("баланс его {int} карты из списка на главной странице должен стать {int} рублей")
    public void totalBalance(int cardIndex, int balance) {
        int actualBalance = dashboardPage.firstBalanceCard(cardIndex);
        int expectedBalance = balance;
        Assert.assertEquals(expectedBalance, actualBalance);
    }
}
