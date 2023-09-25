package com.innowise.innowise_practice.api.services;

import com.google.common.collect.ImmutableMap;
import com.innowise.innowise_practice.api.endpoints.OnlinerEndpoints;
import com.innowise.innowise_practice.api.models.SushiPageModel;
import com.innowise.innowise_practice.api.utils.GetRequestUtils;

import java.util.List;
import java.util.Map;


public class ProductService {

    public List<SushiPageModel> getSushiProductsList() {
        return GetRequestUtils.makeGetRequestAndGetBody(OnlinerEndpoints.getSushiProductsEndpoint(), null, null)
                .jsonPath()
                .getList("products", SushiPageModel.class);
    }

    public List<SushiPageModel> getSushiNamePrefixesList() {
        return GetRequestUtils.makeGetRequestAndGetBody(OnlinerEndpoints.getSushiFilterRollsProductsEndpoint(), null, null)
                .jsonPath()
                .getList("products", SushiPageModel.class);
    }

    private static Map<String, Object> configureHeaders() {
        return ImmutableMap.of("Host", "catalog.onliner.by");
    }
}
