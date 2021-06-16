package io.neczpal.locations_spring.persistence;

import io.neczpal.locations_spring.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
