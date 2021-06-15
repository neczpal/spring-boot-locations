package io.neczpal.locations_spring.controllers;

import io.neczpal.locations_spring.dtos.LocationDto;
import io.neczpal.locations_spring.services.LocationsService;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
public class LocationsController {
    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping
    public List<LocationDto> getLocations(@RequestParam Optional<Double> minLat,
                                          @RequestParam Optional<Double> maxLat,
                                          @RequestParam Optional<Double> minLon,
                                          @RequestParam Optional<Double> maxLon) {
        return locationsService.getLocations().stream().filter(
                locationDto -> (minLat.isEmpty() || minLat.get() <= locationDto.getLat()) &&
                        (maxLat.isEmpty() || maxLat.get()  >= locationDto.getLat()) &&
                        (minLon.isEmpty() || minLon.get()  <= locationDto.getLat()) &&
                        (maxLon.isEmpty() || maxLon.get()  >= locationDto.getLat()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LocationDto getLocationById(@PathVariable long id) {
        return locationsService.findLocationById(id);
    }
}
