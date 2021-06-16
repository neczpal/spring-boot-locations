package io.neczpal.locations.services;

import io.neczpal.locations.dtos.CreateLocationCommand;
import io.neczpal.locations.dtos.LocationDto;
import io.neczpal.locations.dtos.UpdateLocationCommand;
import io.neczpal.locations.entities.Location;
import io.neczpal.locations.exceptions.LocationNotFoundException;
import io.neczpal.locations.persistence.LocationDao;
import io.neczpal.locations.persistence.LocationRepository;
import io.neczpal.locations.properties.LocationServiceProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@EnableConfigurationProperties(LocationServiceProperties.class)
@Slf4j
public class LocationsService {

    private ModelMapper modelMapper;
    private LocationServiceProperties locationServiceProperties;
    @Deprecated
    private LocationDao locationDao;
    private LocationRepository locationRepository;


    public List<LocationDto> getLocations() {
        return locationRepository
                .findAll()
                .stream()
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());
    }

    public LocationDto findLocationById(long id) {
        return modelMapper.map(locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException("location not found")), LocationDto.class);
    }

    public LocationDto createLocation(CreateLocationCommand createLocationCommand) {
        Location location = new Location(
                createLocationCommand.getName(),
                createLocationCommand.getLon(),
                createLocationCommand.getLat());

        if (locationServiceProperties.isAutoCapitalize()) {
            String old = location.getName();
            location.setName(old.substring(0, 1).toUpperCase() + old.substring(1));
        }

        locationRepository.save(location);

        log.info("Employee has been created: " + location.getId());
        return modelMapper.map(location, LocationDto.class);
    }

    @Transactional
    public LocationDto updateLocation(long id, UpdateLocationCommand updateLocationCommand) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("employee not found"));

        if (locationServiceProperties.isAutoCapitalize()) {
            String old = location.getName();
            location.setName(old.substring(0, 1).toUpperCase() + old.substring(1));
        }

        location.setName(updateLocationCommand.getName());
        location.setLon(updateLocationCommand.getLon());
        location.setLat(updateLocationCommand.getLat());

        log.info("Employee has been updated: " + location.getId());
        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        locationRepository.deleteById(id);
        log.info("Employee has been deleted: " + id);
    }

    public void deleteAllLocations() {
        locationRepository.deleteAll();
    }
}
