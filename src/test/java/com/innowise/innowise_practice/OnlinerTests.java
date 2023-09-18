package com.innowise.innowise_practice;

import com.innowise.innowise_practice.driver.Driver;
import com.innowise.innowise_practice.pageobjects.onliner_page_objects.MainPageOnlinerObject;
import com.innowise.innowise_practice.pageobjects.onliner_page_objects.ResultProductPage;
import com.innowise.innowise_practice.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OnlinerTests extends BaseTest {

    private static final WebDriver driver = Driver.getDriver();

    private final MainPageOnlinerObject mainPageOnliner = new MainPageOnlinerObject(driver);

    private final ResultProductPage resultProductPage = new ResultProductPage(driver);

    private static final String request = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)";

    private static final String requestedProductLink = "https://catalog.onliner.by/mobile/samsung/sma525fzkdser";

    @Test
    public void searchTest() {
        Driver.openLink(LinksForTestsEnum.ONLINER.getLink());
        mainPageOnliner
                .clickOnSearchFieldAndEnterText(request)
                .openSearchResult();
        assertEquals(Driver.getDriver().getCurrentUrl(), "https://catalog.onliner.by/mobile/samsung/sma525fzkdser");
    }

    @Test
    public void addingTheProductToCartTest() {
        Driver.openLink(LinksForTestsEnum.ONLINER.getLink());
        mainPageOnliner
                .clickOnSearchFieldAndEnterText(request)
                .openSearchResult();
        Assertions.assertAll(
                () -> assertEquals(resultProductPage.getProductResultTittleText(), request),
                () -> assertEquals(resultProductPage.getSelectedItemText(), "Описание и фото")
        );
    }
}
