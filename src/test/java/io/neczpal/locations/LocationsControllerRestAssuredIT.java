package io.neczpal.locations;

import io.neczpal.locations.dtos.CreateLocationCommand;
import io.neczpal.locations.services.LocationsService;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.with;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationsControllerRestAssuredIT {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocationsService locationsService;

    @BeforeEach
    void init() {
        RestAssuredMockMvc.mockMvc(mockMvc);
        RestAssuredMockMvc.requestSpecification = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON);

        locationsService.deleteAllLocations();
    }

    @Test
    void testListLocations() {
        with()
                .body(new CreateLocationCommand("Budapest", 15, 77))
                .post("/api/locations")
                .then()
                .statusCode(201)
                .body("name", equalTo("Budapest"))
                .log();

        with()
                .get("/api/locations")
                .then()
                .statusCode(200)
                .body("size()", equalTo(1));
    }
    @Test
    void validate() {
        with()
                .body(new CreateLocationCommand("Budapest", 15.3, 16.5))
                .post("/api/locations")
                .then()
                .body(matchesJsonSchemaInClasspath("location-dto.json"));
    }
}
