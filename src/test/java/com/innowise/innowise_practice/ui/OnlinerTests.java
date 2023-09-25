package com.innowise.innowise_practice.ui;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.onliner_page_objects.CartPage;
import com.innowise.innowise_practice.ui.pageobjects.onliner_page_objects.MainPageOnlinerObject;
import com.innowise.innowise_practice.ui.pageobjects.onliner_page_objects.ResultProductPage;
import com.innowise.innowise_practice.ui.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static com.innowise.innowise_practice.ui.driver.Driver.openLink;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OnlinerTests extends BaseTest {


    private final MainPageOnlinerObject mainPageOnliner = new MainPageOnlinerObject(Driver.getDriver());

    private final ResultProductPage resultProductPage = new ResultProductPage(Driver.getDriver());

    private final CartPage cartPage = new CartPage(Driver.getDriver());

    private static final String request = "Смартфон Samsung Galaxy A52 SM-A525F/DS 4GB/128GB (черный)";

    private static final String requestedProductLink = "https://catalog.onliner.by/mobile/samsung/sma525fzkdser";

    @Test
//    @RepeatedTest(3)
    public void searchTest() {
        openLink(LinksForTestsEnum.ONLINER.getLink());
        mainPageOnliner
                .clickOnSearchFieldAndEnterText(request)
                .openSearchResult();
        assertEquals(requestedProductLink, Driver.getDriver().getCurrentUrl());
    }

    @Test
    public void addingTheProductToCartTest() {
        openLink(LinksForTestsEnum.ONLINER.getLink());
        mainPageOnliner
                .clickOnSearchFieldAndEnterText(request)
                .openSearchResult();
        Assertions.assertAll(
                () -> assertEquals(resultProductPage.getProductResultTittleText(), request),
                () -> assertEquals(resultProductPage.getSelectedItemText(), "Описание и фото")
        );
        resultProductPage
                .addProductWithLowestPriceToCart();
        assertTrue(resultProductPage.isAllOfRightButtonsAppears());
    }

    @Test
    public void shoppingCartTest() {
        openLink(LinksForTestsEnum.ONLINER.getLink());
        mainPageOnliner
                .clickOnSearchFieldAndEnterText(request)
                .openSearchResult()
                .addProductWithLowestPriceToCart()
                .clickOnGoToCartButton();
        Assertions.assertAll(
                () -> assertEquals("https://cart.onliner.by/", Driver.getDriver().getCurrentUrl()) ,
                () -> assertEquals(cartPage.getProductName(), request)
        );
    }

    @Test
    public void removingProductFromCart() {
        openLink(LinksForTestsEnum.ONLINER.getLink());
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
}
