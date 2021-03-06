package io.neczpal.locations;

import io.neczpal.locations.dtos.CreateLocationCommand;
import io.neczpal.locations.dtos.LocationDto;
import io.neczpal.locations.dtos.LocationsDto;
import io.neczpal.locations.services.LocationsService;
import org.junit.jupiter.api.RepeatedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from locations")
public class LocationsControllerRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Autowired
    LocationsService locationsService;

    @RepeatedTest(4)
    void testListLocations() {
        LocationDto locationDto =
                template.postForObject("/api/locations", new CreateLocationCommand("Budapest", 15,15), LocationDto.class);

        assertEquals("Budapest", locationDto.getName());

        template.postForObject("/api/locations", new CreateLocationCommand("Sopron", 17,15), LocationDto.class);

        LocationsDto locations = template.exchange("/api/locations",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<LocationsDto>() {
                })
                .getBody();

        assert locations != null;
        assertThat(locations.getLocationDtoList())
                .extracting(LocationDto::getName)
                .containsExactly("Budapest", "Sopron");
    }

}
