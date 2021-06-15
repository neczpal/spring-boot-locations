package io.neczpal.locations_spring.services;

import io.neczpal.locations_spring.model.Location;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LocationsServiceTest {

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService();
        Location location = locationsService.getLocations().get(0);
        assertThat(location).isEqualTo(Location.BUDAPEST);
    }
}