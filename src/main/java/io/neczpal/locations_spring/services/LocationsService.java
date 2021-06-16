package io.neczpal.locations_spring.services;

import io.neczpal.locations_spring.dtos.CreateLocationCommand;
import io.neczpal.locations_spring.dtos.LocationDto;
import io.neczpal.locations_spring.dtos.UpdateLocationCommand;
import io.neczpal.locations_spring.entities.Location;
import io.neczpal.locations_spring.exceptions.LocationNotFoundException;
import io.neczpal.locations_spring.properties.LocationServiceProperties;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
@EnableConfigurationProperties(LocationServiceProperties.class)
public class LocationsService {
    private List<Location> locationList = Collections.synchronizedList(new ArrayList<>(List.of(
            new Location(1, "Budapest", 42.1, 17.8),
            new Location(2, "Szeged", 37.1, 15.8)
    )));
    private AtomicLong atomicLong = new AtomicLong(3);

    private ModelMapper modelMapper;
    private LocationServiceProperties locationServiceProperties;

    public LocationsService(ModelMapper modelMapper, LocationServiceProperties locationServiceProperties) {
        this.modelMapper = modelMapper;
        this.locationServiceProperties = locationServiceProperties;
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

        if(locationServiceProperties.isAutoCapitalize()) {
            String old = location.getName();
            location.setName(old.substring(0, 1).toUpperCase() + old.substring(1));
        }

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

        if(locationServiceProperties.isAutoCapitalize()) {
            String old = location.getName();
            location.setName(old.substring(0, 1).toUpperCase() + old.substring(1));
        }

        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        Location location = locationList.stream()
                .filter(loc -> loc.getId() == id)
                .findFirst()
                .orElseThrow(() -> new LocationNotFoundException("Not valid ID: " + id));

        locationList.remove(location);
    }

    public void deleteAllLocations() {
        locationList.clear();
        atomicLong = new AtomicLong(0);
    }
}
