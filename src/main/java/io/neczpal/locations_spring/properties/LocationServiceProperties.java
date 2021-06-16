package io.neczpal.locations_spring.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "location")
public class LocationServiceProperties {

    private boolean autoCapitalize;

}
