package io.neczpal.locations;

import io.neczpal.locations.controllers.LocationsController;
import io.neczpal.locations.dtos.LocationDto;
import io.neczpal.locations.services.LocationsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = LocationsController.class)
public class LocationsControllerMockMvcIT {

    @MockBean
    LocationsService locationsService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void testListLocations() throws Exception{
        when(locationsService.getLocations())
                .thenReturn(List.of(
                        new LocationDto(1, "Budapest", 17, 40),
                        new LocationDto(1, "Sopron", 13, 38)
                ));

        mockMvc.perform(get("/api/locations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.locationDtoList[0].name", equalTo("Budapest")));
    }

}
