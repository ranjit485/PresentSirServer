package com.ranjit.ps.service;

import com.ranjit.ps.model.Bus;
import com.ranjit.ps.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BusService {

    @Autowired
    private BusRepository busRepository;

    public Bus saveBus(Bus bus) {
        return busRepository.save(bus);
    }

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Optional<Bus> getBusById(Long busId) {
        return busRepository.findById(busId);
    }

    public Bus updateBus(Long busId, Bus busDetails) {
        return busRepository.findById(busId).map(bus -> {
            bus.setRouteName(busDetails.getRouteName());
            return busRepository.save(bus);
        }).orElseThrow(() -> new RuntimeException("Bus not found with id " + busId));
    }

    public void deleteBus(Long busId) {
        busRepository.deleteById(busId);
    }
}
