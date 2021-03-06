package uk.codersparks.homeassistant.denonservice.config;

import io.resourcepool.ssdp.client.SsdpClient;
import io.resourcepool.ssdp.model.DiscoveryListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import uk.codersparks.homeassistant.denonservice.service.DenonAVRBoxService;
import uk.codersparks.homeassistant.denonservice.ssdp.discovery.DenonAVRDiscoveryListener;
import uk.codersparks.homeassistant.ssdp.config.HomeAssistantSSDPProperties;

/**
 * Loads beans for use within service
 */
@Configuration
public class BeanConfig {

    @Bean
    public SsdpClient ssdpClient() {
        return SsdpClient.create();
    }

    @Bean
    public DiscoveryListener discoveryListener(DenonAVRBoxService service, HomeAssistantSSDPProperties properties) {
        return new DenonAVRDiscoveryListener(service, properties);
    }

}
