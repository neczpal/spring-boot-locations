package io.neczpal.locations_spring.dtos;

import io.neczpal.locations_spring.annotations.Coordinate;
import io.neczpal.locations_spring.annotations.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocationCommand {
    @Schema(description = "name of the location", example = "Budapest")
    @NotBlank
    @NotNull
    private String name;

    @Schema(description = "latitude of the location", example = "47.49801")
    @Coordinate(type = Type.LAT)
    private double lat;

    @Schema(description = "longitude of the location", example = "19.03991")
    @Coordinate(type = Type.LON)
    private double lon;
}
