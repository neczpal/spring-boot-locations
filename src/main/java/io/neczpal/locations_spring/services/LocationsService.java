package io.neczpal.locations_spring.services;

import io.neczpal.locations_spring.dtos.CreateLocationCommand;
import io.neczpal.locations_spring.dtos.LocationDto;
import io.neczpal.locations_spring.dtos.UpdateLocationCommand;
import io.neczpal.locations_spring.entities.Location;
import io.neczpal.locations_spring.exceptions.LocationNotFoundException;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LocationsService {
    private List<Location> locationList = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(1, "Budapest", 42.1, 17.8),
            new Location(2, "Szeged", 37.1, 15.8)
    )));
    private AtomicLong atomicLong = new AtomicLong(3);
    private ModelMapper modelMapper;

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations() {
        Type type = new TypeToken<List<LocationDto>>() {
        }.getType();
        return modelMapper.map(locationList, type);
    }

    public LocationDto findLocationById(long id) {
        return modelMapper.map(locationList.stream()
                .filter(location -> location.getId() == id)
                .findAny()
                .orElseThrow(() -> new LocationNotFoundException("Not valid ID: " + id)), LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand createLocationCommand) {
        Location location = new Location(atomicLong.getAndIncrement(),
                createLocationCommand.getName(),
                createLocationCommand.getLon(),
                createLocationCommand.getLat());

        locationList.add(location);
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand updateLocationCommand) {
        Location location = locationList.stream()
                .filter(loc -> loc.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Not valid ID: " + id));

        location.setName(updateLocationCommand.getName());
        location.setLon(updateLocationCommand.getLon());
        location.setLat(updateLocationCommand.getLat());

        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = locationList.stream()
                .filter(loc -> loc.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Not valid ID: " + id));

        locationList.remove(location);
    }
}
