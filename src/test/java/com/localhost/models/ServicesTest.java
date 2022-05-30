package com.localhost.models;
/* 
 Created by Kalpesh Patel
 */

import com.localhost.model.ServicesPojo;
import com.localhost.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class ServicesTest extends TestBase {
    @Test
    public void getAllServices() {
        Response response = given()
                .when()
                .get("/services");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getAServiceOfID() {
        Response response = given()
                .pathParam("id",9)
                .when()
                .get("/services/{id}");
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void createANewService() {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName("QA tester");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .when()
                .body(servicesPojo)
                .post("/services");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void updateANewService() {
        ServicesPojo servicesPojo = new ServicesPojo();
        servicesPojo.setName("Scrum Master");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("id",25)
                .when()
                .body(servicesPojo)
                .patch("/services/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }
    @Test
    public void deleteAService() {
        ServicesPojo servicesPojo = new ServicesPojo();
        Response response = given()
                .pathParam("id",25)
                .when()
                .delete("/services/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

}
