package com.localhost.models;
/* 
 Created by Kalpesh Patel
 */

import com.localhost.model.StoresPojo;
import com.localhost.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresTest extends TestBase {
    @Test
    public void getAllStores() {
        Response response = given()
                .queryParams("$limit", 10)
                .queryParams("$skip", 2)
                .when()
                .get("/stores");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void getAllStoresWithExtract() {
        Response response = given()
                .when()
                .get("/stores");
        response.then().statusCode(200);

        System.out.println("---------------------------- Response ---------------------------------");
        int limit = response.then().extract().path("limit");
        System.out.println("1. Limits                           :" + limit);

        int total = response.then().extract().path("total");
        System.out.println("2. Total                            :" + total);

        String nameOfFifthStore = response.then().extract().path("data[4].name");
        System.out.println("3. Name of Fifth Store              :" + nameOfFifthStore);

        List<String> nameOfAllStores = response.then().extract().path("data.name");
        System.out.println("4. Name of All Stores               :" + nameOfAllStores);

        List<?> storeIds = response.then().extract().path("data.id");
        System.out.println("5. Name of All Stores IDs           :" + storeIds);

        List<?> data = response.then().extract().path("data");
        System.out.println("6. Data size                        :" + data.size());

        List<?> valuesOfStCloud = response.then().extract().path("data.findAll{it.name=='St Cloud'}");
        System.out.println("7. All values of St. Cloud          :" + valuesOfStCloud);

        List<?> addressOfRoseville = response.then().extract().path("data.findAll{it.name=='Rochester'}.address");
        System.out.println("8. Address of Rochester             :" + addressOfRoseville);

        List<?> allServicesOfEightStore = response.then().extract().path("data.findAll{it.id==8}.services");
        System.out.println("9. Services of 8th Store            :" + allServicesOfEightStore);

        List<?> storeServiceWindows = response.then().extract().path("data.find{it.services}.services.findAll{it.name=='Windows Store'}.storeservices");
        System.out.println("10. StoreServices of Windows Store  :" + storeServiceWindows);

        List<?> storeId = response.then().extract().path("data.id");
        System.out.println("11. StoreID of All Store            :" + storeId);

        List<?> id = response.then().extract().path("data.services.id");
        System.out.println("12. ID of All Store                 :" + id);

        List<?> namesND = response.then().extract().path("data.findAll{it.state=='ND'}.name");
        System.out.println("13. Store name of State ND          :" + namesND);

        List<?> servicesOfRochester = response.then().extract().path("data.findAll{it.name=='Rochester'}.services.find{it.id}");
        System.out.println("14. Nr. of Services                 :" + servicesOfRochester.size());

        List<?> createdAt = response.then().extract().path("data.find{it.services}.services.findAll{it.name='Windows Store'}.storeservices.createdAt");
        System.out.println("15. Created At of Windows Store     :" + createdAt);

        List<?> fargoStore = response.then().extract().path("data.findAll{it.name=='Fargo'}.services.find{it.names}");
        System.out.println("16. All Services of Fargo Store     :" + fargoStore);

        List<?> zipcode = response.then().extract().path("data.zip");
        System.out.println("17. Zip of all Store                :" + zipcode);

        List<?> zipCodeRoseville = response.then().extract().path("data.findAll{it.name=='Roseville'}.zip");
        System.out.println("18. All Services of Fargo Store     :" + zipCodeRoseville);

        List<?> storeServiceMagnolia = response.then().extract().path("data.find{it.services}.services.findAll{it.name='Magnolia Home Theater'}");
        System.out.println("19. All Services of Magnolia Store  :" + storeServiceMagnolia);

        List<?> lat = response.then().extract().path("data.lat");
        System.out.println("20. lat of all stores               :" + lat);

        System.out.println("------------------------ End of Response ------------------------------");
    }

    @Test
    public void getOneStoreDetails() {
        Response response = given()
                .pathParam("id", 4)
                .when()
                .get("/stores/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void createANewStore() {
        HashMap<Object, Object> servicesData = new HashMap<>();
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("New York");
        storesPojo.setType("SmallBox");
        storesPojo.setAddress("111 Old Road");
        storesPojo.setAddress2("Behind new road");
        storesPojo.setCity("New York");
        storesPojo.setState("AZ");
        storesPojo.setZip("44551");
        storesPojo.setLat(45);
        storesPojo.setLng(44);
        storesPojo.setHours("Mon: 10-9; Tue: 10-9; Wed: 10-9; Thurs: 10-9; Fri: 10-9; Sat: 10-9; Sun: 10-8");
        storesPojo.setServices(servicesData);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .body(storesPojo)
                .when()
                .post("/stores");
        response.prettyPrint();
        response.then().statusCode(201);
    }

    @Test
    public void updateAStore() {
        HashMap<Object, Object> servicesData = new HashMap<>();
        StoresPojo storesPojo = new StoresPojo();
        storesPojo.setName("New York");
        storesPojo.setType("A New SmallBox");
        storesPojo.setAddress("113 Old Road");
        storesPojo.setAddress2("Behind new road");
        storesPojo.setCity("New York");
        storesPojo.setState("AZ");
        storesPojo.setZip("44551");
        storesPojo.setLat(54);
        storesPojo.setLng(46);
        storesPojo.setHours("Mon: 10-8; Tue: 10-8; Wed: 10-8; Thurs: 10-8; Fri: 10-8; Sat: 10-8; Sun: 10-6");
        storesPojo.setServices(servicesData);

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("id", 8922)
                .body(storesPojo)
                .when()
                .patch("/stores/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }

    @Test
    public void deleteAStore() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .pathParam("id", 8922)
                .when()
                .delete("/stores/{id}");
        response.prettyPrint();
        response.then().statusCode(200);
    }


}
