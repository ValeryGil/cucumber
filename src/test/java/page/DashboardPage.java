package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id='dashboard']");
    private static ElementsCollection cards = $$(".list__item div");
    private static final String balanceStart = "баланс: ";
    private static final String balanceFinish = " р.";
    private ElementsCollection deposit = $$("[data-test-id='action-deposit']");
    private SelenideElement reload = $("[data-test-id='action-reload']");

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public RefillCardPage selectCard(String cardIndex) {
        cards.get(Integer.parseInt(cardIndex) - 1).find("button").click();
        return new RefillCardPage();
    }

    public void firstBalanceCard(String index, String balance) {
        if (index == "1") {
            $(withText(balance)).shouldHave(attribute("data-test-id", "92df3f1c-a033-48e6-8390-206f6b1f56c0"));
        } else if (index == "2") {
            $(withText(balance)).shouldHave(attribute("data-test-id", "0f3f5c2a-249e-4c3d-8287-09f7a039391d"));
        }
    }
}
