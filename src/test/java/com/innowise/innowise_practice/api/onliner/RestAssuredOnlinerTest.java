package com.innowise.innowise_practice.api.onliner;

import com.innowise.innowise_practice.api.services.ProductService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.innowise.innowise_practice.api.endpoints.OnlinerEndpoints.getSushiFilterRollsProductsEndpoint;
import static com.innowise.innowise_practice.api.endpoints.OnlinerEndpoints.getSushiProductsEndpoint;
import static io.restassured.RestAssured.given;


public class RestAssuredOnlinerTest {

    @Test
    public void checkRequest() {
        given()
                .when()
                .get(getSushiProductsEndpoint())
                .then()
                .statusCode(200);

        given()
                .when()
                .get(getSushiFilterRollsProductsEndpoint())
                .then()
                .statusCode(200);
    }

    @Test
    public void checkProductsServiceTest() {
        new ProductService()
                .getSushiProductsList()
                .forEach(product -> Assertions.assertNotNull(product.getName()));
    }

    @Test
    public void checkNamePrefixTest() {
        new ProductService()
                .getSushiNamePrefixesList()
                .forEach(prefixes -> Assertions.assertEquals("Роллы", prefixes.getNamePrefix()));
    }
}
