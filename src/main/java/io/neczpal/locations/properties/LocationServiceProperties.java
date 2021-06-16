package io.neczpal.locations.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "location")
public class LocationServiceProperties {

    private boolean autoCapitalize;

}
