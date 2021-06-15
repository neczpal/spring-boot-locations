package io.neczpal.locations_spring.services;

import io.neczpal.locations_spring.dtos.LocationDto;
import io.neczpal.locations_spring.entities.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationsServiceTest {

    @Autowired
    ModelMapper modelMapper;

    @Test
    void getLocations() {
        LocationsService locationsService = new LocationsService(modelMapper);
        LocationDto location = locationsService.getLocations().get(0);
        assertThat(location.getName()).isEqualTo("Budapest");
    }
}