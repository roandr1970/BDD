package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashBoardPage;
import ru.netology.web.page.LoginPage;
import ru.netology.web.data.DataHelper;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.web.data.DataHelper.getFirstCardInfo;
import static ru.netology.web.data.DataHelper.getSecondCardInfo;
import static org.junit.jupiter.api.Assertions.assertEquals;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

     @Test
     void shouldTransferFromFirstToSecond(){
         var loginPage = open("http://localhost:9999", LoginPage.class);
         var authInfo = DataHelper.getAuthInfo();
         var verificationPage = loginPage.validLogin(authInfo);
         var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
         var dashboardPage = verificationPage.validVerify(verificationCode);
         var firstCardInfo = getFirstCardInfo();
         var secondCardInfo = getSecondCardInfo();
         int amount = 1000;
         var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
         var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
         var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
         dashboardPage = transferPage.makeTransfer(String.valueOf(amount), firstCardInfo);
         var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
         var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
         assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
         assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
     }

    @Test
    void shouldTransferFromSecondToFirst(){
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int amount = 1000;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) - amount;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(firstCardInfo);
        dashboardPage = transferPage.makeTransfer(String.valueOf(amount), secondCardInfo);
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
    }

    @Test
    void shouldTransferFromFirstToSecondMoreBalance(){
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        var firstCardInfo = getFirstCardInfo();
        var secondCardInfo = getSecondCardInfo();
        int amount = 11000;
        var expectedBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) - amount;
        var expectedBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) + amount;
        var transferPage = dashboardPage.selectCardToTransfer(secondCardInfo);
        dashboardPage = transferPage.makeTransfer(String.valueOf(amount), firstCardInfo);
        var actualBalanceFirstCard = dashboardPage.getCardBalance(firstCardInfo) + amount;
        var actualBalanceSecondCard = dashboardPage.getCardBalance(secondCardInfo) - amount;
        assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
}
