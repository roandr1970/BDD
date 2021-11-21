package ru.netology.web.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashBoardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private SelenideElement firstButton = $$("[data-test-id=action-deposit]").first();
    private SelenideElement twoButton = $$("[data-test-id=action-deposit]").last();
    private SelenideElement inputMoney = $("money-input__value");
    private SelenideElement whereFrom = $("[data-test-id=from] value");
    private SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public DashBoardPage(){
        heading.shouldBe(visible);
    }

    public DashBoardPage transferFromCard1(DataHelper.Card1 amount, DataHelper.Card1 card1) {
        firstButton.click();
        inputMoney.shouldBe(visible);
        inputMoney.setValue(amount.getCard1());
        whereFrom.setValue(card1.getCard1());
        transferButton.click();
        return new DashBoardPage();
    }

    public int getCardBalance(String id) {
        val text = cards.findBy(cssValue("data-test-id",id));
        return extractBalance(text.text());
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}

