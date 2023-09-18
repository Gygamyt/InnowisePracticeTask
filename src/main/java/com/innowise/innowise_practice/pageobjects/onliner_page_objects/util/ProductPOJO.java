package com.innowise.innowise_practice.pageobjects.onliner_page_objects.util;

import org.openqa.selenium.WebElement;

public class ProductPOJO {
    private Double price;
    private WebElement product;

    public ProductPOJO(Double price) {
        this.price = price;
    }

    public ProductPOJO(WebElement product) {
        this.product = product;
    }


    public ProductPOJO(WebElement element, Double aDouble) {
    }

    public WebElement getProduct() {
        return product;
    }

    public void setProduct(WebElement product) {
        this.product = product;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
