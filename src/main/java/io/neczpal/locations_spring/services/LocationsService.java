package io.neczpal.locations_spring.services;

import io.neczpal.locations_spring.dtos.CreateLocationCommand;
import io.neczpal.locations_spring.dtos.LocationDto;
import io.neczpal.locations_spring.dtos.UpdateLocationCommand;
import io.neczpal.locations_spring.entities.Location;
import io.neczpal.locations_spring.persistence.LocationDao;
import io.neczpal.locations_spring.properties.LocationServiceProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@EnableConfigurationProperties(LocationServiceProperties.class)
@Slf4j
public class LocationsService {

    private ModelMapper modelMapper;
    private LocationServiceProperties locationServiceProperties;
    private LocationDao locationDao;


    public List<LocationDto> getLocations() {
        return locationDao
                .findAll()
                .stream()
                .map(location -> modelMapper.map(location, LocationDto.class))
                .collect(Collectors.toList());
    }

    public LocationDto findLocationById(long id) {
        return modelMapper.map(locationDao.findById(id), LocationDto.class);
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

        locationDao.save(location);

        log.info("Employee has been created: " + location.getId());
        return modelMapper.map(location, LocationDto.class);
    }

    public LocationDto updateLocation(long id, UpdateLocationCommand updateLocationCommand) {
        Location location = new Location(
                id,
                updateLocationCommand.getName(),
                updateLocationCommand.getLon(),
                updateLocationCommand.getLat());

        if (locationServiceProperties.isAutoCapitalize()) {
            String old = location.getName();
            location.setName(old.substring(0, 1).toUpperCase() + old.substring(1));
        }

        locationDao.update(location);

        log.info("Employee has been updated: " + location.getId());
        return modelMapper.map(location, LocationDto.class);
    }

    public void deleteLocation(long id) {
        locationDao.deleteById(id);
        log.info("Employee has been deleted: " + id);
    }

    public void deleteAllLocations() {
        locationDao.deleteAll();
    }
}
