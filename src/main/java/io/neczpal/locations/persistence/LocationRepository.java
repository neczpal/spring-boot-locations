package io.neczpal.locations.persistence;

import io.neczpal.locations.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
