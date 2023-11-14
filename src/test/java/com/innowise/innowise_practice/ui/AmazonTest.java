package com.innowise.innowise_practice.ui;

import com.innowise.innowise_practice.ui.pageobjects.amazon_page_objects.AmazonHeader;
import com.innowise.innowise_practice.ui.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.innowise.innowise_practice.ui.driver.Driver.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
@Disabled
public class AmazonTest extends BaseTest {

    private final AmazonHeader amazonHeader = new AmazonHeader(getDriver(), getWaiter(), getActions());

    private final String productName = "iPhone";

    @Override
    @BeforeEach
    public void openPage() {
        openLink(LinksForTestsEnum.AMAZON.getLink());
    }

    @Test
    public void loginAmazonTest() {
        var resultPage = amazonHeader
                .hoverAccountsAndListsAndClickLogin()
                .loginAmazon();
        assertTrue(resultPage.isHelloTextRight("Test"));
    }

    @Test
    public void searchTest() {
        var resultPage = amazonHeader
                .hoverAccountsAndListsAndClickLogin()
                .loginAmazon()
                .searchProduct(productName);
        assertTrue(resultPage.isProductNamesMatch(productName));
    }

    @Test
    public void addingToCartTest() {
        var resultPage = amazonHeader
                .hoverAccountsAndListsAndClickLogin()
                .loginAmazon()
                .searchProduct(productName)
                .openPageWithFirstProduct()
                .addToCart();
        Assertions.assertAll(
                () -> assertTrue(resultPage.isAddedToCartTextAppears()),
                () -> assertTrue(resultPage.isGreenIconAppears()),
                () -> assertTrue(resultPage.isQuantityOfItemsIsOne())
        );
    }

    @Test
    public void cartCheck() {
        var resultPage = amazonHeader
                .hoverAccountsAndListsAndClickLogin()
                .loginAmazon()
                .searchProduct(productName)
                .openPageWithFirstProduct()
                .addToCart()
                .openCart();
        Assertions.assertAll(
                () -> assertTrue(resultPage.isCartOpened()),
                () -> assertTrue(resultPage.isProductVisible())
        );
    }
}
