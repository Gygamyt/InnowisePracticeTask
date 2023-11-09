package com.innowise.innowise_practice.ui.utils;

public enum CredentialsEnum {

    AMAZON_PASS("DuZMu3iR01K9"),

    AMAZON_LOG("22testtesttesttesttesttest22@gmail.com");

    private String inf;

    CredentialsEnum(String inf) {
        this.inf = inf;
    }

    public String getInf() {
        return inf;
    }
}
