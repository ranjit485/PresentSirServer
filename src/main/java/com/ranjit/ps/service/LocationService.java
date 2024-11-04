package com.ranjit.ps.service;

import com.ranjit.ps.model.BusQ;
import com.ranjit.ps.model.Location;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LocationService {
    private final Map<String, BusQ> buses = new HashMap<>();

    // Creates and adds a new bus to the service
    public BusQ createBusInQ(String busId) {
        BusQ busQ = new BusQ(busId);
        buses.put(busId, busQ);
        System.out.println("Created bus with ID: " + busId);
        return busQ;
    }

    // Retrieves a bus by ID
    public BusQ getBus(String busId) {
        return buses.get(busId);
    }

    // Adds a location to a specific bus by ID
    public void addLocationToBus(String busId, double latitude, double longitude) {
        BusQ busQ = buses.get(busId);
        if (busQ != null) {
            busQ.addLocation(latitude, longitude);
        } else {
            System.out.println("Bus with ID " + busId + " not found.");
        }
    }

    // GET EXACT BUS LOCATION
    public Location getLocation(String busId){
        BusQ busQ = buses.get(busId);
        Location location = new Location();
        location.setLatitude(busQ.getLocation().getLatitude());
        location.setLongitude(busQ.getLocation().getLongitude());
        return location;
    }

    // Processes all locations for each bus
    public void processAllBusLocations() {
        for (BusQ busQ : buses.values()) {
            busQ.processLocations();
        }
    }
}
