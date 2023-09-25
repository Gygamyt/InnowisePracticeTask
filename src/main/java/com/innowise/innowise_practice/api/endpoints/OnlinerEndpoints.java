package com.innowise.innowise_practice.api.endpoints;

import com.innowise.innowise_practice.PropReader;

public class OnlinerEndpoints {

    public static String getSushiProductsEndpoint() {
        return PropReader.getProperty("sushi_products");
    }

    public static String getSushiFilterRollsProductsEndpoint() {
        return PropReader.getProperty("sushi_filter_rolls_products");
    }
}
