package io.neczpal.locations_spring.controllers;

import io.neczpal.locations_spring.dtos.CreateLocationCommand;
import io.neczpal.locations_spring.dtos.LocationDto;
import io.neczpal.locations_spring.dtos.UpdateLocationCommand;
import io.neczpal.locations_spring.services.LocationsService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocationDto createLocation(@RequestBody CreateLocationCommand createLocationCommand) {
        return locationsService.createLocation(createLocationCommand);
    }

    @PutMapping("/{id}")
    public LocationDto updateLocation(@PathVariable long id, @RequestBody UpdateLocationCommand updateLocationCommand) {
        return locationsService.updateLocation(id, updateLocationCommand);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable long id) {
        locationsService.deleteLocation(id);
    }
}
