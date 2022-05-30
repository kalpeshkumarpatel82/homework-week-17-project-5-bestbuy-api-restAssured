package com.localhost.models;
/* 
 Created by Kalpesh Patel
 */

import com.localhost.model.ProductsPoJo;
import com.localhost.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ProductsTest extends TestBase {
    @Test
    public void findProducts() {
        Response response = given()
                .queryParams("$limit", 5)
                .queryParams("$skip", 2)
                .when()
                .get("/products");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void findProductByID() {
        Response response = given()
                .pathParam("id", 43900)
                .when()
                .get("/products/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void createProduct() {
        ProductsPoJo productsPoJo = new ProductsPoJo();
        productsPoJo.setName("Samsung A70 64GB");
        productsPoJo.setType("Smartphone");
        productsPoJo.setPrice(1249);
        productsPoJo.setShipping(29);
        productsPoJo.setUpc("012562165141");
        productsPoJo.setDescription("Compatible in EU / NA ");
        productsPoJo.setManufacturer("Samsung");
        productsPoJo.setModel("A71 64GB EU");
        productsPoJo.setUrl("https://www.samsung.com/");
        productsPoJo.setImage("https://www.samsung.com/images/samsung-a71.png");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(productsPoJo)
                .when()
                .post("/products");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void updateProduct() {
        ProductsPoJo productsPoJo = new ProductsPoJo();
        productsPoJo.setName("Samsung A70 64GB");
        productsPoJo.setType("Smartphone");
        productsPoJo.setPrice(1200);
        productsPoJo.setShipping(20);
        productsPoJo.setUpc("012562165141");
        productsPoJo.setDescription("Compatible in EU / NA / ASIA");
        productsPoJo.setManufacturer("Samsung");
        productsPoJo.setModel("A71 64GB EU");
        productsPoJo.setUrl("https://www.samsung.com/");
        productsPoJo.setImage("https://www.samsung.com/images/samsung-a71.png");

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(productsPoJo)
                .pathParam("id", 9999685)
                .when()
                .patch("/products/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void deleteProduct() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("id", 9999685)
                .when()
                .delete("/products/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }
}
