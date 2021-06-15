package io.neczpal.locations_spring.controllers;

import io.neczpal.locations_spring.services.LocationsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationsController {
    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping("/locations")
    public String getLocations () {
        StringBuilder stringBuilder = new StringBuilder();
        locationsService.getLocations().forEach(location -> {
            stringBuilder.append(location.getName());
            stringBuilder.append(", ");
        });
        return stringBuilder.toString();
    }
}
