package io.neczpal.locations_spring.services;

import io.neczpal.locations_spring.model.Location;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class LocationService {
    List<Location> locationList = List.of(Location.BUDAPEST, Location.SOPRON, Location.SZEGED);


    public List<Location> getLocations() {
        return new ArrayList<>(locationList);
    }
}
