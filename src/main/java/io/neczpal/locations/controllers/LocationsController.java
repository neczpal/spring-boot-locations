package io.neczpal.locations.controllers;

import io.neczpal.locations.dtos.CreateLocationCommand;
import io.neczpal.locations.dtos.LocationDto;
import io.neczpal.locations.dtos.LocationsDto;
import io.neczpal.locations.dtos.UpdateLocationCommand;
import io.neczpal.locations.exceptions.LocationNotFoundException;
import io.neczpal.locations.services.LocationsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/locations")
@Tag(name = "Operations on locations")
public class LocationsController {
    private final LocationsService locationsService;

    public LocationsController(LocationsService locationsService) {
        this.locationsService = locationsService;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "lists all locations")
    @ApiResponse(responseCode = "200", description = "locations has been listed")
    public LocationsDto getLocations(@RequestParam Optional<Double> minLat,
                                     @RequestParam Optional<Double> maxLat,
                                     @RequestParam Optional<Double> minLon,
                                     @RequestParam Optional<Double> maxLon) {
        return new LocationsDto(locationsService.getLocations().stream().filter(
                locationDto -> (minLat.isEmpty() || minLat.get() <= locationDto.getLat()) &&
                        (maxLat.isEmpty() || maxLat.get()  >= locationDto.getLat()) &&
                        (minLon.isEmpty() || minLon.get()  <= locationDto.getLat()) &&
                        (maxLon.isEmpty() || maxLon.get()  >= locationDto.getLat()))
                .collect(Collectors.toList()));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "get a locations by its id")
    @ApiResponse(responseCode = "200", description = "location returned")
    public LocationDto getLocationById(@PathVariable long id) {
        return locationsService.findLocationById(id);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "create a location")
    @ApiResponse(responseCode = "201", description = "location created")
    public LocationDto createLocation(@Valid @RequestBody CreateLocationCommand createLocationCommand) {
        return locationsService.createLocation(createLocationCommand);
    }

    @PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @Operation(summary = "edit a location")
    @ApiResponse(responseCode = "200", description = "location edited")
    public LocationDto updateLocation(@PathVariable long id, @Valid @RequestBody UpdateLocationCommand updateLocationCommand) {
        return locationsService.updateLocation(id, updateLocationCommand);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    @Operation(summary = "delete a location")
    @ApiResponse(responseCode = "204", description = "location deleted")
    public void deleteLocation(@PathVariable long id) {
        locationsService.deleteLocation(id);
    }


    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Problem> handleNotFoundException(LocationNotFoundException exception) {
        Problem problem =
                Problem.builder()
                        .withType(URI.create("locations/not-found"))
                        .withTitle("Not found")
                        .withStatus(Status.NOT_FOUND)
                        .withDetail(exception.getMessage())
                        .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
