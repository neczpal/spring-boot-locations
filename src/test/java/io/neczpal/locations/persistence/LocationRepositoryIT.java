package io.neczpal.locations.persistence;

import io.neczpal.locations.entities.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class LocationRepositoryIT {

    @Autowired
    LocationRepository locationRepository;

    @Test
    void testPersist() {
        Location location = new Location("Budapest", 15, 61);
        locationRepository.save(location);

        List<Location> locationList = locationRepository.findAll();

        assertThat(locationList).extracting(Location::getName)
                .containsExactly("Budapest");
    }
}
