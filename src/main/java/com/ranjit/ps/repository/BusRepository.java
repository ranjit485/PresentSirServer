package com.ranjit.ps.repository;

import com.ranjit.ps.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
    // Custom query methods can be added here if needed
}
