package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class RefillCardPage {
    private SelenideElement heading = $(withText("Пополнение карты"));
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement fromField = $("[data-test-id='from'] input");
    private SelenideElement toField = $("[data-test-id='to'] input");
    private SelenideElement refillButton = $("[data-test-id='action-transfer']");
    private SelenideElement cancelButton = $("[data-test-id='action-cancel']");
    private SelenideElement errorMessage = $("[data-test-id='error-notification'] .notification__content");

    public RefillCardPage() {
        heading.shouldBe(Condition.visible);
    }

    public DashboardPage refillCard(String amount, String cardNumber) {
        amountField.setValue(amount);
        fromField.setValue(cardNumber);
        refillButton.click();
        return new DashboardPage();
    }
}
