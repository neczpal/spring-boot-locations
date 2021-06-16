package io.neczpal.locations_spring.services;

import io.neczpal.locations_spring.dtos.LocationDto;
import io.neczpal.locations_spring.persistence.LocationDao;
import io.neczpal.locations_spring.properties.LocationServiceProperties;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class LocationsServiceTest {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    LocationDao locationDao;

    @Test
    void getLocations() {
        LocationServiceProperties properties = new LocationServiceProperties();
        LocationsService locationsService = new LocationsService(modelMapper, properties, locationDao);
        LocationDto location = locationsService.getLocations().get(0);
        assertThat(location.getName()).isEqualTo("Budapest");
    }
}