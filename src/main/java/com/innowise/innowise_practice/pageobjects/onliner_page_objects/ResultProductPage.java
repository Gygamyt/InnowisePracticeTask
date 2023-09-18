package com.innowise.innowise_practice.pageobjects.onliner_page_objects;

import com.innowise.innowise_practice.driver.Driver;
import com.innowise.innowise_practice.pageobjects.BasePage;
import com.innowise.innowise_practice.pageobjects.onliner_page_objects.util.ProductPOJO;
import com.innowise.innowise_practice.utils.NumbersParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
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

    private final String offersForMapping = "//div[contains(@class, \"product-aside__offers-item\")]";


    private WebElement selectOfferWithLowestPrice() {

        Map<Double, WebElement> elementHashMap = new HashMap<>();

        List<WebElement> offersForMappingElements = findElementsByStringXPath(offersForMapping);

        ArrayList<ProductPOJO> productPOJOArrayList = new ArrayList<>();

        offersForMappingElements
                .forEach(element -> productPOJOArrayList
                        .add(new ProductPOJO(element.findElement(By.xpath("//a[contains(text(), 'В корзину')]")),
                                NumbersParser.parseDouble(element.findElement(By.xpath("//a//span")).getText()))));

        return ;
    }

    public String getProductResultTittleText() {
        return getElementText(productResultTittle);
    }

    public String getSelectedItemText() {
        return getElementText(selectedItemXPath);
    }
}
