package io.neczpal.locations_spring.controllers;

import io.neczpal.locations_spring.dtos.LocationDto;
import io.neczpal.locations_spring.entities.Location;
import io.neczpal.locations_spring.services.LocationsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LocationsControllerTest {

    @Mock
    LocationsService locationsService;

    @InjectMocks
    LocationsController locationsController;

    @Test
    void getLocations() {
        when(locationsService.getLocations()).thenReturn(List.of(new LocationDto(-1, "Budapest", 12, 15)));

        assertThat(locationsController.getLocations(Optional.empty(),
                Optional.empty(),
                Optional.empty(),
                Optional.empty()).get(0).getName()).startsWith("Budapest");
    }
}