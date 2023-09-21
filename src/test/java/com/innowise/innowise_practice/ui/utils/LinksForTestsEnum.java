package com.innowise.innowise_practice.ui.utils;

public enum LinksForTestsEnum {
    ONLINER("https://www.onliner.by/"),

    AMAZON("https://www.amazon.com/"),

    GOOGLE_TRANSLATE("https://translate.google.com/"),

    RELAX_BY("https://www.relax.by/");
    private String link;

    LinksForTestsEnum(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }
}
