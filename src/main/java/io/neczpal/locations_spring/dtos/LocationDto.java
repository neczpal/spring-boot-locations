package io.neczpal.locations_spring.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
public class LocationDto {

    @Schema(description = "id of the location", example = "1")
    private long id;

    @Schema(description = "name of the location", example = "Budapest")
    private String name;

    @Schema(description = "latitude of the location", example = "47.49801")
    private double lat;

    @Schema(description = "longitude of the location", example = "19.03991")
    private double lon;
}
