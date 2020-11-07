package uk.codersparks.homeassistant.denonservice.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uk.codersparks.homeassistant.denonservice.model.DenonAVRBox;
import uk.codersparks.homeassistant.denonservice.repository.DenonAVRBoxRepository;

import java.util.Optional;

/**
 * Provides a service as an abstraction between repository and controller
 */
@Service
public class DenonAVRBoxService {

    private static final Logger logger = LoggerFactory.getLogger(DenonAVRBoxService.class);

    private final DenonAVRBoxRepository repository;

    public DenonAVRBoxService(DenonAVRBoxRepository repository) {
        this.repository = repository;
    }


    public Optional<DenonAVRBox> getDenonAVRBoxBlocking(String id) {

        logger.debug("Getting box with id: " + id);
        return this.repository.findById(id).blockOptional();
    }

    public DenonAVRBox saveDenonAVRBoxBlocking(DenonAVRBox box) {
        logger.debug("Attempting to save box: " + box);
        return this.repository.save(box).block();
    }
}
