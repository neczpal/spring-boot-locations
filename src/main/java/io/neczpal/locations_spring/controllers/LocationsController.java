package io.neczpal.locations_spring.controllers;

import io.neczpal.locations_spring.services.LocationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationsController {
    private final LocationService locationService;

    public LocationsController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/locations")
    public String getLocations () {
        StringBuilder stringBuilder = new StringBuilder();
        locationService.getLocations().forEach(location -> {
            stringBuilder.append(location.getName());
            stringBuilder.append(", ");
        });
        return stringBuilder.toString();
    }
}
