package com.localhost.models;
/* 
 Created by Kalpesh Patel
 */

import com.localhost.model.CategoriesPoJo;
import com.localhost.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CategoriesTest extends TestBase {
    @Test
    public void getAllServices() {
        Response response = given()
                .queryParams("$limit",5)
                .queryParams("$skip",2)
                .when()
                .get("/categories");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getAServiceOfID() {
        Response response = given()
                .pathParam("id","abcat0020002")
                .when()
                .get("/categories/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void createANewService() {
        CategoriesPoJo categoriesPoJo = new CategoriesPoJo();
        categoriesPoJo.setName("Gift Cards");
        categoriesPoJo.setId("abcat0010001");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(categoriesPoJo)
                .post("/categories");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void updateANewService() {
        CategoriesPoJo categoriesPoJo = new CategoriesPoJo();
        categoriesPoJo.setName("Gift Cards - 10 Euros");
        categoriesPoJo.setId("abcat0010011");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("id","abcat0010001")
                .when()
                .body(categoriesPoJo)
                .patch("/categories/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void deleteAService() {
        CategoriesPoJo categoriesPoJo = new CategoriesPoJo();
        Response response = given()
                .pathParam("id","abcat0010001")
                .when()
                .delete("/categories/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

}
