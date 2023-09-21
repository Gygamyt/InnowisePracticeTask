package com.innowise.innowise_practice.pageobjects.onliner_page_objects;

import com.innowise.innowise_practice.driver.Driver;
import com.innowise.innowise_practice.pageobjects.BasePage;
import com.innowise.innowise_practice.ui.utils.NumbersParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultProductPage extends BasePage {
    public ResultProductPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[contains(@class, \"catalog-masthead__title\")]")
    private WebElement productResultTittle;

    @FindBy(xpath = "//li[@class=\"item selected\"]//span")
    private WebElement selectedItemXPath;

    @FindBy(xpath = "//div[@class=\"product-aside__offers-list\"]")
    private WebElement offersList;

    @FindBy(xpath = "//div[@class=\"product-recommended__subheader\"]")
    private WebElement productIsAddedTextPlace;

    @FindBy(xpath = "//div[contains(@class, \"product-recommended__control\")]/a[contains(@class, \"helpers\")]")
    private WebElement continueButton;

    @FindBy(xpath = "//div[contains(@class, \"product-recommended__control\")]/a[contains(@href, \"cart\")]")
    private WebElement goToCartButton;

    private final String offersForMapping = "//div[contains(@class, \"product-aside__offers-item\")]";


    private WebElement selectOfferWithLowestPrice() {

        Map<WebElement, Double> elementHashMap = new HashMap<>();

        List<WebElement> offersForMappingElements = findElementsByStringXPath(offersForMapping);

        offersForMappingElements
                .forEach(element -> elementHashMap.put(Driver.getDriver().findElement(By.xpath(offersForMapping + "//a[contains(text(), 'В корзину')]")),
                        NumbersParser.parseDouble(Driver.getDriver().findElement(By.xpath(offersForMapping + "//a//span")).getText())));

        return elementHashMap.keySet().stream().min(Comparator.comparing(elementHashMap::get)).orElse(null);
    }

    public ResultProductPage addProductWithLowestPriceToCart() {
        clickElement(selectOfferWithLowestPrice());
        return this;
    }

    public String getProductResultTittleText() {
        return getElementText(productResultTittle);
    }

    public String getSelectedItemText() {
        return getElementText(selectedItemXPath);
    }

    public boolean isAllOfRightButtonsAppears() {
        return isElementVisible(productIsAddedTextPlace) && isElementVisible(continueButton) && isElementVisible(goToCartButton);
    }

    public CartPage clickOnGoToCartButton() {
        clickElement(goToCartButton);
        return new CartPage(Driver.getDriver());
    }
}
