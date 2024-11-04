package com.ranjit.ps.model;

import java.util.LinkedList;
import java.util.Queue;

public class BusQ {
    private final String busId;

    private final Queue<Location> locationQueue;

    public BusQ(String busId) {
        this.busId = busId;
        this.locationQueue = new LinkedList<>();
    }

    public String getBusId() {
        return busId;
    }

    // Adds a new location to this bus's queue
    public void addLocation(double latitude, double longitude) {
        locationQueue.add(new Location(latitude, longitude));
        System.out.println("Added location to bus " + busId + ": " + latitude + ", " + longitude);
    }

    public Location getLocation(){
       return locationQueue.peek();// TODO FIX THIS
    }

//    TODO what is does actually
    // Processes and removes each location in this bus's queue
    public void processLocations() {
        System.out.println("Processing locations for bus " + busId);
        while (!locationQueue.isEmpty()) {
            Location location = locationQueue.poll();
            System.out.println("Processed location: " + location);
        }
    }
}
