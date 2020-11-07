package uk.codersparks.homeassistant.denonservice.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import uk.codersparks.homeassistant.denonservice.model.DenonAVRBox;

/**
 * Spring repository interface for saving details to mongo
 */
public interface DenonAVRBoxRepository extends ReactiveMongoRepository<DenonAVRBox, String> {
}
