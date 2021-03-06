package uk.codersparks.homeassistant.denonservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * A Class to provide information about a dennon AVR box
 */
@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DenonAVRBox {

    @Id
    private String id;

    private String friendlyName;

    private String ipAddress;

}
