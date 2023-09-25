package com.innowise.innowise_practice.api.utils;

import io.restassured.response.ResponseBody;
import org.apache.commons.collections4.MapUtils;

import java.util.Map;

import static io.restassured.RestAssured.*;

public class GetRequestUtils {

    public static ResponseBody makeGetRequestAndGetBody(String endpoint, Map<String, Object> header, Map<String, Object> params) {

        return given()
                .headers(MapUtils.emptyIfNull(header))
                .params(MapUtils.emptyIfNull(params))
                .when()
                .get(endpoint)
                .then()
                .statusCode(200)
                .extract()
                .response()
                .getBody();
    }
}
