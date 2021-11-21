package ru.netology.web.data;

import lombok.Value;

public class DataHelper {
    private DataHelper() {}

    @Value
    public static class AuthInfo{
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo(){ return new AuthInfo("vasya", "qwerty123"); }

    public static AuthInfo getOtherAuthInfo(AuthInfo original){ return new AuthInfo("petya", "123qwerty"); }

    @Value
    public static class VerificationCode{
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo){
        return new VerificationCode("12345");
    }
    @Value
    public static class Card1 {
        private String amount;
        private String card1;
    }
    public static Card1 getCard1(){ return new Card1("500","5559000000000002"); }

    @Value
    public static class Card2 {
        private String amount;
        private String card2;
    }
    public static Card2 getCard2(){ return new Card2("300","5559000000000001"); }

}
