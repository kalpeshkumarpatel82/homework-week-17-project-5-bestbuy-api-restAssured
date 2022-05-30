package com.localhost.models;
/* 
 Created by Kalpesh Patel
 */

import com.localhost.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UtilitiesTest extends TestBase {
    @Test
    public void getVersion() {
        Response response = given()
                .when()
                .get("/version");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getHealthCheck() {
        Response response = given()
                .when()
                .get("/healthcheck");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
