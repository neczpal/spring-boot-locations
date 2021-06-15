package io.neczpal.locations_spring.services;

import io.neczpal.locations_spring.dtos.LocationDto;
import io.neczpal.locations_spring.entities.Location;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationsService {
    private List<Location> locationList = List.of(Location.BUDAPEST, Location.SOPRON, Location.SZEGED);

    private ModelMapper modelMapper;

    public LocationsService(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public List<LocationDto> getLocations() {
        Type type = new TypeToken<List<LocationDto>>(){}.getType();
        return modelMapper.map(locationList, type);
    }
}
