package uk.codersparks.homeassistant.denonservice.ssdp.discovery;

import io.resourcepool.ssdp.model.DiscoveryListener;
import io.resourcepool.ssdp.model.SsdpService;
import io.resourcepool.ssdp.model.SsdpServiceAnnouncement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import uk.codersparks.homeassistant.denonservice.model.DenonAVRBox;
import uk.codersparks.homeassistant.denonservice.service.DenonAVRBoxService;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Discovery Listener implementation to process message
 */
public class DenonAVRDiscoveryListener implements DiscoveryListener {

    private static final Logger logger = LoggerFactory.getLogger(DenonAVRDiscoveryListener.class);



    private final DenonAVRBoxService denonAVRBoxService;

    public DenonAVRDiscoveryListener(DenonAVRBoxService denonAVRBoxService) {
        this.denonAVRBoxService = denonAVRBoxService;
    }

    @Override
    public void onServiceDiscovered(SsdpService service) {
        try {
            logger.debug("Service detected: " + service);
            updateBoxDetails(service.getSerialNumber(), service.getLocation());

        } catch (MalformedURLException e) {
            logger.error("Malformed service location", e);
        }
    }

    @Override
    public void onServiceAnnouncement(SsdpServiceAnnouncement announcement) {
        try {
            logger.debug("Service Announcement detected: " + announcement);
            updateBoxDetails(announcement.getSerialNumber(), announcement.getLocation());
        } catch (MalformedURLException e) {
            logger.error("Malformed announcement location", e);
        }
    }

    @Override
    public void onFailed(Exception ex) {
        logger.error("Discovery listener failed", ex);

    }

    private void updateBoxDetails(String serialNumber, String location) throws MalformedURLException {
        String id = serialNumber.split(":")[1];

        DenonAVRBox box = this.denonAVRBoxService.getDenonAVRBoxBlocking(id).orElse(new DenonAVRBox(id, null, null));
        logger.debug("Retried box: " + box);
        String ipAddress = new URL(location).getHost();
        logger.debug("IP Address from ssdp: " + ipAddress);
        if (!ipAddress.equals(box.getIpAddress())) {

            logger.info("Found new ip address: " + ipAddress + " for box: " + box.getId());
            box.setIpAddress(ipAddress);
            this.denonAVRBoxService.saveDenonAVRBoxBlocking(box);
        } else {
            logger.debug("IP Address is the same for box: " + box.getId());
        }
    }
}
