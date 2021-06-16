package io.neczpal.locations.dtos;

import io.neczpal.locations.annotations.Coordinate;
import io.neczpal.locations.annotations.Type;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class LocationDto {

    @Schema(description = "id of the location", example = "1")
    private long id;

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
