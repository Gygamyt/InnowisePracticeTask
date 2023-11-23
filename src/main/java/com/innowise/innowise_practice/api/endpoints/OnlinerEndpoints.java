package com.innowise.innowise_practice.api.endpoints;

public class OnlinerEndpoints {

    private static final String onlinerApiAddress = "https://catalog.onliner.by/sdapi";

    private static final String searchOnlinerParam = "/catalog.api/search";

    private static final String sushiVeslaSearchParam = "/sushivesla";

    private static final String filteredProductsParams = "?sushi_typ[0]=roll&sushi_typ[operation]=union";

    public static String getSushiProductsEndpoint() {
        return onlinerApiAddress + searchOnlinerParam + sushiVeslaSearchParam;
    }

    public static String getSushiFilterRollsProductsEndpoint() {
        return onlinerApiAddress + searchOnlinerParam + sushiVeslaSearchParam + filteredProductsParams;
    }
}
