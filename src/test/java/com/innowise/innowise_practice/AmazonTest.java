package com.innowise.innowise_practice;

import com.innowise.innowise_practice.driver.Driver;
import com.innowise.innowise_practice.pageobjects.amazon.*;
import com.innowise.innowise_practice.utils.LinksForTestsEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

import static com.innowise.innowise_practice.driver.Driver.openLink;
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
    public void cartCheck() throws InterruptedException {
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
        Thread.sleep(5000);
    }
}