package io.neczpal.locations_spring.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {
    @Schema(description = "name of the location", example = "Budapest")
    private String name;

    @Schema(description = "latitude of the location", example = "47.49801")
    private double lat;

    @Schema(description = "longitude of the location", example = "19.03991")
    private double lon;
}
