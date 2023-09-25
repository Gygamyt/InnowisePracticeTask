package com.innowise.innowise_practice.ui;

import com.innowise.innowise_practice.ui.driver.Driver;
import com.innowise.innowise_practice.ui.pageobjects.amazon_page_objects.*;
import com.innowise.innowise_practice.ui.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.innowise.innowise_practice.ui.driver.Driver.openLink;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AmazonTest extends BaseTest {

    private final WebDriver driver = Driver.getDriver();

    private final AmazonHeader amazonHeader = new AmazonHeader(driver);

    private final AmazonMainPage amazonMainPage = new AmazonMainPage(driver);

    private final AmazonProductsSearchPage amazonProductsSearchPage = new AmazonProductsSearchPage(driver);

    private final AmazonProductPage amazonProductPage = new AmazonProductPage(driver);

    private final AmazonCartPage amazonCartPage = new AmazonCartPage(driver);

    private final String productName = "iPhone";

    @Test
    public void loginAmazonTest() {
        openLink(LinksForTestsEnum.AMAZON.getLink());
        amazonHeader
                .hoverAccountsAndListsAndClickLogin()
                .loginAmazon();
        assertTrue(amazonMainPage.isHelloTextRight("Test"));
    }

    @Test
    public void searchTest() {
        loginAmazonTest();
        amazonMainPage.searchProduct(productName);
        assertTrue(amazonProductsSearchPage.isProductNamesMatch(productName));
    }

    @Test
    public void addingToCartTest() {
        openLink(LinksForTestsEnum.AMAZON.getLink());

        amazonHeader
                .hoverAccountsAndListsAndClickLogin()
                .loginAmazon()
                .searchProduct(productName)
                .openPageWithFirstProduct()
                .addToCart();

        Assertions.assertAll(
                () -> assertTrue(amazonProductPage.isAddedToCartTextAppears()),
                () -> assertTrue(amazonProductPage.isGreenIconAppears()),
                () -> assertTrue(amazonHeader.isQuantityOfItemsIsOne())
        );
    }

    @Test
    public void cartCheck() {
        openLink(LinksForTestsEnum.AMAZON.getLink());

        amazonHeader
                .hoverAccountsAndListsAndClickLogin()
                .loginAmazon()
                .searchProduct(productName)
                .openPageWithFirstProduct()
                .addToCart()
                .openCart();

        Assertions.assertAll(
                () -> assertTrue(amazonCartPage.isCartOpened()),
                () -> assertTrue(amazonCartPage.isProductVisible())
        );
    }
}
