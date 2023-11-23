package com.innowise.innowise_practice.api.services;

import com.google.common.collect.ImmutableMap;
import com.innowise.innowise_practice.api.models.SushiPageModel;

import java.util.List;
import java.util.Map;

import static com.innowise.innowise_practice.api.endpoints.OnlinerEndpoints.getSushiFilterRollsProductsEndpoint;
import static com.innowise.innowise_practice.api.endpoints.OnlinerEndpoints.getSushiProductsEndpoint;
import static com.innowise.innowise_practice.api.utils.GetRequestUtils.makeGetRequestAndGetBody;


public class ProductService {

    public List<SushiPageModel> getSushiProductsList() {
        return makeGetRequestAndGetBody(getSushiProductsEndpoint(), configureHeaders(), null)
                .jsonPath()
                .getList("products", SushiPageModel.class);
    }

    public List<SushiPageModel> getSushiNamePrefixesList() {
        return makeGetRequestAndGetBody(getSushiFilterRollsProductsEndpoint(), configureHeaders(), null)
                .jsonPath()
                .getList("products", SushiPageModel.class);
    }

    private static Map<String, Object> configureHeaders() {
        return ImmutableMap.of("Host", "catalog.onliner.by");
    }
}
