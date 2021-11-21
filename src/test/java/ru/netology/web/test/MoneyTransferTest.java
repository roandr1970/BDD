package ru.netology.web.test;

import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashBoardPage;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

     @Test
     void shouldTransferMoneyBetweenOwnCards(){
         open("http://localhost:9999/");
         var loginPage = new LoginPage();
         var authInfo = DataHelper.getAuthInfo();
         var verificationPage = loginPage.validLogin(authInfo);
         var verificationInfo = DataHelper.getVerificationCodeFor(authInfo);
         verificationPage.validVerify(verificationInfo);
         var dashBoardPage = new DashBoardPage();
         var transferInfo = DataHelper.getCard1();
         dashBoardPage.transferFromCard1(transferInfo);
     }
}
