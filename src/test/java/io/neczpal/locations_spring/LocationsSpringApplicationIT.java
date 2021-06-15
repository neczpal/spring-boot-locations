package io.neczpal.locations_spring;

import io.neczpal.locations_spring.controllers.LocationsController;
import io.neczpal.locations_spring.services.LocationsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationsSpringApplicationIT {

    @Autowired
    private LocationsService locationsService;

    @Autowired
    private LocationsController locationController;

    @Test
    void contextLoads() {
    }

    @Test
    void testLocations() {
        assertThat(locationController.getLocations(Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty())
            .getLocationDtoList().get(0).getName()).startsWith("Budapest");
    }

}
