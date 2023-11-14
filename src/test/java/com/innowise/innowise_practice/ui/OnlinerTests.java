package com.innowise.innowise_practice.ui;

import com.innowise.innowise_practice.ui.pageobjects.onliner_page_objects.MainPageOnlinerObject;
import com.innowise.innowise_practice.ui.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.innowise.innowise_practice.ui.driver.Driver.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
@Disabled
public class OnlinerTests extends BaseTest {

    private final MainPageOnlinerObject mainPageOnliner = new MainPageOnlinerObject(getDriver(), getWaiter(), getActions());

    private static final String request = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)";

    private static final String requestedProductLink = "https://catalog.onliner.by/mobile/samsung/sma525fzkdser";

    @Test
    public void searchTest() {
        var resultPage = mainPageOnliner
                .clickOnSearchFieldAndEnterText(request)
                .openSearchResult();
        assertEquals(requestedProductLink, resultPage.getPageUrl());
    }

    @Test
    public void addingTheProductToCartTest() {
        var resultPage = mainPageOnliner
                .clickOnSearchFieldAndEnterText(request)
                .openSearchResult();
        Assertions.assertAll(
                () -> assertEquals(resultPage.getProductResultTittleText(), request),
                () -> assertEquals(resultPage.getSelectedItemText(), "Описание и фото")
        );
        resultPage
                .addProductWithLowestPriceToCart();
        assertTrue(resultPage.isAllOfRightButtonsAppears());
    }

    @Test
    public void shoppingCartTest() {
        var resultPage = mainPageOnliner
                .clickOnSearchFieldAndEnterText(request)
                .openSearchResult()
                .addProductWithLowestPriceToCart()
                .clickOnGoToCartButton();
        Assertions.assertAll(
                () -> assertEquals("https://cart.onliner.by/", resultPage.getPageUrl()),
                () -> assertEquals(resultPage.getProductName(), request)
        );
    }

    @Test
    public void removingProductFromCart() {
        String textForAssert = mainPageOnliner
                .clickOnSearchFieldAndEnterText(request)
                .openSearchResult()
                .addProductWithLowestPriceToCart()
                .clickOnGoToCartButton()
                .clickOnDeleteButton()
                .clickOnCloseButton()
                .getResultText();
        assertEquals("Ваша корзина пуста", textForAssert);
    }

    @Override
    @BeforeEach
    public void openPage() {
        openLink(LinksForTestsEnum.ONLINER.getLink());
    }
}
